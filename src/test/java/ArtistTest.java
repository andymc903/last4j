import static org.junit.Assert.*;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import co.smartshuffle.last.fm.api.responses.ArtistGetInfo;


public class ArtistTest extends JsonTest  {
	
	public ArtistTest()	{
		super("src/test/resources/artist.json", ArtistGetInfo.class);
	}

}
