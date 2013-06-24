package co.smartshuffle.last.fm.entities.artist;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

public class BaseSimilarArtist implements ImagedItem {
	
	private List<Image> images;
	private URL url;
	private String name;
	
	public BaseSimilarArtist(List<Image> images, URL url, String name) {
		super();
		this.images = images;
		this.url = url;
		this.name = name;
	}
	
	public BaseSimilarArtist()	{
		//empty object
	}

	@JsonProperty("image")
	public List<Image> getImages() {
		return images;
	}

	@JsonProperty("image")
	public void setImages(List<Image> images) {
		this.images = images;
	}

	@JsonIgnore
	public URL getUrl() {
		return url;
	}
	
	@JsonProperty("url")
	public String getUrlString() {
		return url.toString();
	}

	@JsonIgnore
	public void setUrl(URL url) {
		this.url = url;
	}
	
	@JsonProperty("url")
	public void setUrl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseSimilarArtist other = (BaseSimilarArtist) obj;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimilarArtist [images=" + images + ", url=" + url + ", name="
				+ name + "]";
	}


	

}
