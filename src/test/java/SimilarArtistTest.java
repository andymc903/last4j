import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import co.smartshuffle.last.fm.api.responses.SimilarArtist;
import co.smartshuffle.last.fm.entities.artist.ComparableSimilarArtist;
import co.smartshuffle.last.fm.entities.artist.SimilarArtistList;


public class SimilarArtistTest extends JsonTest {
	
	public SimilarArtistTest() {
        super("src/test/resources/similar-artist.json", SimilarArtist.class);
    }

	@Test
	public void build_Similar_Artist_List_Test() {
		try	{
			List<ComparableSimilarArtist> list = SimilarArtistList.createSimilarArtistList(json);
			assertEquals("Expected list size was 100", 100, list.size());
		}catch(Exception e)	{
			e.printStackTrace();
			fail("should not throw exception");
		}
	}

}
