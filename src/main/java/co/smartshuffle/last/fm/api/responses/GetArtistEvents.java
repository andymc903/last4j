package co.smartshuffle.last.fm.api.responses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import co.smartshuffle.last.fm.entities.events.Event;
@JsonIgnoreProperties("@attr")
public class GetArtistEvents {

    private List<Event> events;
    
    public List<Event> getEvents() {
        return events;
    }
    
    @JsonProperty("events")
    public void setEvents(JsonNode root)    {
        JsonNode eventNode = root.get("event");
        List<Event> eventList;
        ObjectMapper mapper = new ObjectMapper();
        try {
            eventList = mapper.readValue(eventNode, new TypeReference<List<Event>>() {});
        } catch (JsonParseException e) {
           throw new IllegalArgumentException(e);
        } catch (JsonMappingException e) {            
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (NullPointerException e) {
            eventList = new ArrayList<Event>();
        }
        
        this.events = eventList;
    }

}
