package co.smartshuffle.last.fm.api.responses;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.smartshuffle.last.fm.entities.artist.ComparableSimilarArtist;
import co.smartshuffle.last.fm.entities.artist.SimilarArtistList;

public class SimilarArtist {
    List<ComparableSimilarArtist> artists;
    
    @JsonIgnore
    public List<ComparableSimilarArtist> getArtists()  {
        return artists;
    }
    
    @JsonProperty("similarartists")
    public void setSimilarArtist(JsonNode json) {
        ObjectMapper mapper = new ObjectMapper();
        List<ComparableSimilarArtist> artists;
        try {
            artists = mapper.readValue(json.get("artist"), SimilarArtistList.class);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
     }

}
