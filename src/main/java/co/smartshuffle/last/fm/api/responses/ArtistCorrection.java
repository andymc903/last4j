package co.smartshuffle.last.fm.api.responses;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.map.ObjectMapper;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;

@JsonIgnoreProperties("@attr")
public class ArtistCorrection {
    private BaseMusicEntity artist;

    public BaseMusicEntity getArtist() {
        return artist;
    }

    @JsonIgnore
    public void setArtist(BaseMusicEntity artist) {
        this.artist = artist;
    }
    
    
    @JsonProperty("corrections")
    @JsonRawValue
    public void setCorrection(JsonNode json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode artistNode = json.get("correction").get("artist");            
            BaseMusicEntity newVal = mapper.readValue(artistNode, BaseMusicEntity.class);
            setArtist(newVal);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    
}
