import co.smartshuffle.last.fm.api.responses.TopArtistTags;


public class TopArtistTagsTest extends JsonTest {

    public TopArtistTagsTest()  {
        super("src/test/resources/top-artist-tags.json", TopArtistTags.class);
    }

}
