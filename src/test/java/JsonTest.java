import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

@Ignore
public class JsonTest {

	protected String json;
	private final String fileName;
	private Class<?> classUnderTest;
	protected ObjectMapper mapper = new ObjectMapper();
	private MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();

	public JsonTest(String filename, Class<?> classUnderTest) {
		super();
		this.fileName = filename;
		this.classUnderTest = classUnderTest;
	}

	@Before
	public void setUp() throws Exception {
		File f = new File(fileName);
		if(!f.exists())	{
			throw new InstantiationError(String.format("cannot initialize file with path %s", f.getAbsolutePath()));
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null)	{
			builder.append(line);
		}
		
		json = builder.toString();
	}
	
	@Test
	public void object_mapper_test()   {
	    try    {	        
	        mapper.readValue(json, classUnderTest);
	    }catch(Exception e)    {
	        e.printStackTrace();
	        fail(String.format("Object mapper threw exception when reading json. For class [%s]", classUnderTest));
	    }
	}
	
	@Test
	public void http_message_converter_test()  {
	    assertTrue(String.format("Cannot Deserialize Class [%s]", classUnderTest), converter.canRead(classUnderTest, MediaType.APPLICATION_JSON));
	}

}