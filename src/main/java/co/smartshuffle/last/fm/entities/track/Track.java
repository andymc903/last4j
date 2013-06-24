package co.smartshuffle.last.fm.entities.track;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Biography;
import co.smartshuffle.last.fm.entities.MusicEntity;
import co.smartshuffle.last.fm.entities.Stats;
import co.smartshuffle.last.fm.entities.Tag;
import co.smartshuffle.last.fm.entities.interfaces.Streamable;

public class Track extends MusicEntity implements Streamable {
	
	private TrackAlbum album;
	private BaseMusicEntity artist;
	private boolean streamable;
	private Stats stats;
	private long duration;
	
	

	public Track() {
		super();
	}



	public Track(String name, URL url, String mbid, boolean streamable,
			Stats stats, List<Tag> tags, Biography wiki, TrackAlbum album, BaseMusicEntity artst, long duration) {
		super(name, url, mbid, tags, wiki);
		setStreamable(streamable);
		setStats(stats);
		setDuration(duration);
		setArtist(artst);
		setAlbum(album);
	}	

	public TrackAlbum getAlbum() {
		return album;
	}



	public void setAlbum(TrackAlbum album) {
		this.album = album;
	}



	public BaseMusicEntity getArtist() {
		return artist;
	}



	public void setArtist(BaseMusicEntity artist) {
		this.artist = artist;
	}


	public void setStreamable(boolean streamable) {
		this.streamable = streamable;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}


	public Stats getStats() {
		return stats;
	}


	public boolean isStreamable() {
		return streamable;
	}



	public long getDuration() {
		return duration;
	}



	public void setDuration(long duration) {
		this.duration = duration;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + (streamable ? 1231 : 1237);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (duration != other.duration)
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (streamable != other.streamable)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Track [album=" + album + ", artist=" + artist + ", streamable="
				+ streamable + ", stats=" + stats + ", duration=" + duration
				+ "]";
	}
	
	@JsonCreator
	public static Track createTrack(String json)	{
		if(StringUtils.isBlank(json))	{
			throw new NullArgumentException("json");
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(json).get("track");
			
			//get primitive values
			int streamable = root.get("streamable").get("fulltrack").getIntValue();
			long listeners = root.get("listeners").getLongValue();
			long playcount = root.get("playcount").getLongValue();
			long duration = root.get("duration").getLongValue();
			String name = root.get("name").getTextValue();
			String mbid = root.get("mbid").getTextValue();
			String url = root.get("url").getTextValue();
			
			//Find Complex Values
			JsonNode artistNode = root.get("artist");
			JsonNode albumNode = root.get("album");
			JsonNode tagListNode = root.get("toptags").get("tag");
			JsonNode bioNode = root.get("wiki");
			
			//Create Complex Objects
			MusicEntity artist = mapper.readValue(artistNode, MusicEntity.class);
			TrackAlbum album = mapper.readValue(albumNode, TrackAlbum.class);
			List<Tag> topTags = mapper.readValue(tagListNode, new TypeReference<List<Tag>>() {} );
			Biography wiki = mapper.readValue(bioNode, Biography.class);
			
			//Create new track and return
			Track track = new Track();
			track.setAlbum(album);
			track.setArtist(artist);
			track.setDuration(duration);
			track.setMbid(mbid);
			track.setName(name);
			track.setStats(new Stats(listeners, playcount));
			track.setStreamable(BooleanUtils.toBoolean(streamable));
			track.setTags(topTags);
			track.setUrl(url);
			track.setWiki(wiki);
			
			return track;
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
}
