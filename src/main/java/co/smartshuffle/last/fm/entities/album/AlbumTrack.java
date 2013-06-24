package co.smartshuffle.last.fm.entities.album;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.ObjectMapper;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.interfaces.Streamable;

@JsonIgnoreProperties({"@attr"})
public class AlbumTrack extends BaseMusicEntity implements Streamable {
	private long duration;
	private BaseMusicEntity artist;
	private boolean streamable;
	
	public AlbumTrack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AlbumTrack(String name, URL url, String mbid, long duration, BaseMusicEntity artist, boolean streamable) {
		super(name, url, mbid);
	}
	
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public BaseMusicEntity getArtist() {
		return artist;
	}
	public void setArtist(BaseMusicEntity artist) {
		this.artist = artist;
	}
		
	public boolean isStreamable() {
		return streamable;
	}
	
	@JsonIgnore
	public void setStreamable(boolean streamable) {
		this.streamable = streamable;
	}
	
	@JsonRawValue
	public void setStreamable(String json) {
	    if(StringUtils.isBlank(json))  {
	        throw new NullArgumentException("json");
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    try {
            int streamable = mapper.readTree(json).get("fulltrack").getIntValue();
            this.streamable = BooleanUtils.toBoolean(streamable);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
	}
}
