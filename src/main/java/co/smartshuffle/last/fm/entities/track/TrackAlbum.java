package co.smartshuffle.last.fm.entities.track;

import java.net.URL;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

@JsonIgnoreProperties("@attr")
public class TrackAlbum extends BaseMusicEntity implements ImagedItem {
	
	private List<Image> images;
	private String artist;
	
	public TrackAlbum() {
		super();
	}

	public TrackAlbum(String name, URL url, String mbid, List<Image> images, String artist) {
		super(name, url, mbid);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@JsonProperty("image")
	public void setImages(List<Image> images) {
		this.images = images;
	}

	@JsonProperty("image")
	public List<Image> getImages() {
		return images;
	}

	@Override
	@JsonProperty("title")
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}




	@Override
	@JsonProperty("title")
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
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
		TrackAlbum other = (TrackAlbum) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrackAlbum [images=" + images + ", artist=" + artist
				+ ", getUrl()=" + getUrl() + ", getMbid()=" + getMbid()
				+ ", getUrlString()=" + getUrlString() + "]";
	}
	
	

}
