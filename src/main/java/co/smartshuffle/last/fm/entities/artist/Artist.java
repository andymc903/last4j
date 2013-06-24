package co.smartshuffle.last.fm.entities.artist;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.MusicEntity;
import co.smartshuffle.last.fm.entities.Biography;
import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.Stats;
import co.smartshuffle.last.fm.entities.Tag;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;
import co.smartshuffle.last.fm.entities.interfaces.Streamable;

public class Artist extends MusicEntity implements ImagedItem, Streamable {	
	
	private Stats stats;
	private List<Image> images;
	private List<BaseSimilarArtist> similar;
	private boolean streamable;
	private boolean onTour;	
	
	
	public Artist() {
		super();
	}

	public Artist(String name, URL url, String mbid, boolean streamable,
			Stats stats, List<Tag> tags, Biography bio, List<Image> images, boolean onTour) {
		super(name, url, mbid, tags, bio);
		this.streamable = streamable;
		this.onTour = onTour;
		this.images = images;
		this.stats = stats;
		
	}
	
	
	
	@Override
	@JsonProperty("bio")
	public Biography getWiki() {
		// TODO Auto-generated method stub
		return super.getWiki();
	}

	@Override
	@JsonProperty("bio")
	public void setWiki(Biography wiki) {
		// TODO Auto-generated method stub
		super.setWiki(wiki);
	}

	public Stats getStats() {
		return stats;
	}
	
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public List<BaseSimilarArtist> getSimilar() {
		return similar;
	}
	
	public void setSimilar(List<BaseSimilarArtist> similar) {
		this.similar = similar;
	}
	
	public boolean isStreamable() {
		return streamable;
	}
	
	public void setStreamable(boolean streamable) {
		this.streamable = streamable;
	}
	
	public boolean isOnTour() {
		return onTour;
	}
	
	public void setOnTour(boolean onTour) {
		this.onTour = onTour;
	}
	
	@JsonCreator
	@JsonRawValue
	public static Artist createArtist(String json)	{
		if(StringUtils.isBlank(json))	{
			throw new IllegalArgumentException("Input json should not be blank");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Artist newArtist = new Artist();
			JsonNode root = mapper.readTree(json);
			
			//Easy ones first
			newArtist.mbid = root.get("mbid").asText();
			newArtist.name = root.get("name").asText();
			newArtist.setUrl(root.get("url").asText());
			newArtist.streamable = BooleanUtils.toBoolean(root.get("streamable").asInt());
			newArtist.onTour = BooleanUtils.toBoolean(root.get("ontour").asInt());
			
			//Get Complex Values
			JsonNode bioJson = root.get("bio");
			JsonNode tagListJson = root.get("tags").get("tag");
			JsonNode imageListJson = root.get("image");
			JsonNode statsJson = root.get("stats");
			JsonNode similarArtistListJson = root.get("similar").get("artist");
			
			//Create Complex Objects			
			Stats stats = mapper.readValue(statsJson, Stats.class);
			List<Tag> tagList = mapper.readValue(tagListJson, new TypeReference<List<Tag>>() {} );
			List<Image> imageList = mapper.readValue(imageListJson, new TypeReference<List<Image>>() {});
			List<BaseSimilarArtist> similarArtists = mapper.readValue(similarArtistListJson, new TypeReference<List<BaseSimilarArtist>>() {});
			Biography bio = mapper.readValue(bioJson, Biography.class);
			
			//Assign to new Artist
			newArtist.wiki = bio;
			newArtist.stats = stats;
			newArtist.images = imageList;
			newArtist.tags = tagList;
			newArtist.similar = similarArtists;
			
			return newArtist;
			
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);			
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
		
	}
	
	
	
}
