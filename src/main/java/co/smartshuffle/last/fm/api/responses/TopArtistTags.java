package co.smartshuffle.last.fm.api.responses;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import co.smartshuffle.last.fm.entities.Tag;

@JsonIgnoreProperties("@attr")
public class TopArtistTags {

    private List<Tag> topTags;

    @JsonIgnore
    public List<Tag> getTopTags() {
        return topTags;
    }
    
    
    @JsonProperty("toptags")
    public void setTopTags(JsonNode root)   {
        JsonNode node = root.get("tag");
        ObjectMapper mapper = new ObjectMapper();
        List<Tag> tagList;
        try {
            tagList = mapper.readValue(node, new TypeReference<List<Tag>>() {});
            this.topTags = tagList;
        } catch (JsonParseException e) {
           throw new IllegalArgumentException(e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
