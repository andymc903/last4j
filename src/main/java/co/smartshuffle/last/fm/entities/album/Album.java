package co.smartshuffle.last.fm.entities.album;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import co.smartshuffle.last.fm.entities.MusicEntity;
import co.smartshuffle.last.fm.entities.Biography;
import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.Stats;
import co.smartshuffle.last.fm.entities.Tag;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

public class Album extends MusicEntity implements ImagedItem {

	private List<Image> images;
	private Date realeaseDate;
	private List<AlbumTrack> trackList;
	private Stats stats;

	public Album() {
		super();
	}

	public Album(String name, URL url, String mbid, List<Tag> tags,
			Biography wiki, List<Image> images, Date releaseDate,
			List<AlbumTrack> tracks, Stats stats) {
		super(name, url, mbid, tags, wiki);
		setImages(images);
		setRealeaseDate(releaseDate);
		setTrackList(tracks);
		setStats(stats);
	}

	public Date getRealeaseDate() {
		return realeaseDate;
	}

	public void setRealeaseDate(Date realeaseDate) {
		this.realeaseDate = realeaseDate;
	}

	public List<AlbumTrack> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<AlbumTrack> trackList) {
		this.trackList = trackList;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Image> getImages() {
		return images;
	}

	@Override
	public String toString() {
		return "Album [images=" + images + ", wiki=" + wiki + ", realeaseDate="
				+ realeaseDate + ", trackList=" + trackList + ", stats="
				+ stats + ", getTags()=" + getTags() + ", getName()="
				+ getName() + ", getUrl()=" + getUrl() + ", getMbid()="
				+ getMbid() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result
				+ ((realeaseDate == null) ? 0 : realeaseDate.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result
				+ ((trackList == null) ? 0 : trackList.hashCode());
		result = prime * result + ((wiki == null) ? 0 : wiki.hashCode());
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
		Album other = (Album) obj;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (realeaseDate == null) {
			if (other.realeaseDate != null)
				return false;
		} else if (!realeaseDate.equals(other.realeaseDate))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (trackList == null) {
			if (other.trackList != null)
				return false;
		} else if (!trackList.equals(other.trackList))
			return false;
		if (wiki == null) {
			if (other.wiki != null)
				return false;
		} else if (!wiki.equals(other.wiki))
			return false;
		return true;
	}
	
	@JsonCreator
	public static Album createAlbum(String json)	{
		if(StringUtils.isBlank(json))	{
			throw new NullArgumentException("json");
		}
		
		DateFormat releaseDateFormatter = new SimpleDateFormat("dd MMM yyyy, HH:mm");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode root = mapper.readTree(json);
			
			//Get primitive values
			String name = root.get("name").getTextValue();
			String artist = root.get("artist").getTextValue();
			String mbid = root.get("mbid").getTextValue();
			String url = root.get("url").getTextValue();
			String releaseDateString = root.get("releasedate").getTextValue().trim();
			long listeners = root.get("listeners").getLongValue();
			long playcount = root.get("playcount").getLongValue();
			
			//Create Date object
			Date releaseDate = releaseDateFormatter.parse(releaseDateString);
			
			//Get Complex Nodes
			JsonNode trackListNode = root.get("tracks").get("track");
			JsonNode imageListNode = root.get("image");
			JsonNode tagListNode = root.get("toptags").get("tag");
			JsonNode wikiNode = root.get("wiki");
			
			List<AlbumTrack> trackList = mapper.readValue(trackListNode, new TypeReference<List<AlbumTrack>>() {} );
			List<Image> images = mapper.readValue(imageListNode, new TypeReference<List<Image>>() {});
			List<Tag> tags = mapper.readValue(tagListNode, new TypeReference<List<Tag>>() {});
			Biography wiki = mapper.readValue(wikiNode, Biography.class);
			
			Album album = new Album(name, new URL(url), mbid, tags, wiki, images, releaseDate, trackList, new Stats(listeners, playcount));
			return album;
			
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
