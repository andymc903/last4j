import static org.junit.Assert.*;

import org.junit.Test;

import co.smartshuffle.last.fm.api.responses.GetTopTracks;


public class TopTracksTest extends JsonTest {

    public TopTracksTest()  {
        super("src/test/resources/top-tracks.json", GetTopTracks.class);
    }

}
