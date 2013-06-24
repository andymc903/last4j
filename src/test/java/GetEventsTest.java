import static org.junit.Assert.*;

import org.junit.Test;

import co.smartshuffle.last.fm.api.responses.GetArtistEvents;


public class GetEventsTest extends JsonTest {
    
    public GetEventsTest()  {
        super("src/test/resources/get-events.json", GetArtistEvents.class);
    }

}
