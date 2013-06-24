import static org.junit.Assert.*;

import org.junit.Test;

import co.smartshuffle.last.fm.api.responses.ArtistCorrection;


public class ArtistCorrectionTest extends JsonTest {

    public ArtistCorrectionTest()   {
        super("src/test/resources/artist-correction.json", ArtistCorrection.class);
    }

}
