import static org.junit.Assert.*;

import org.junit.Test;

import co.smartshuffle.last.fm.api.responses.GetTopAlbums;


public class TopAlbumsTest extends JsonTest {
    
    public TopAlbumsTest()  {
        super("src/test/resources/top-albums.json", GetTopAlbums.class);
    }

}
