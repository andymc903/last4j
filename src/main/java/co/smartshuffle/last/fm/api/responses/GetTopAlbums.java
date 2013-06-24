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

import co.smartshuffle.last.fm.entities.artist.TopAlbum;

@JsonIgnoreProperties("@attr")
public class GetTopAlbums {

    private List<TopAlbum> albums;

    public List<TopAlbum> getAlbums() {
        return albums;
    }
    
    @JsonProperty("topalbums")
    public void setTopAlbums(JsonNode node) {
        JsonNode albumList = node.get("album");
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<TopAlbum> albums = mapper.readValue(albumList, new TypeReference<List<TopAlbum>>(){});
            this.albums = albums;
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    

}
