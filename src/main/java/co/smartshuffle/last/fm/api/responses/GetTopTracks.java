package co.smartshuffle.last.fm.api.responses;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import co.smartshuffle.last.fm.entities.artist.TopTrack;

@JsonIgnoreProperties("@attr")
public class GetTopTracks {

    private List<TopTrack> topTracks;

    public List<TopTrack> getTopTracks() {
        return topTracks;
    }

    @JsonProperty("toptracks")
    public void setTopTracks(JsonNode root) {
        JsonNode trackListNode = root.get("track");
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<TopTrack> trackList = mapper.readValue(trackListNode, new TypeReference<List<TopTrack>>() {});
            this.topTracks = trackList;
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    

}
