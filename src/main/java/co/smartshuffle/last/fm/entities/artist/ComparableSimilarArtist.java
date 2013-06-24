package co.smartshuffle.last.fm.entities.artist;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.interfaces.Streamable;


public class ComparableSimilarArtist extends BaseSimilarArtist implements
		Comparable<ComparableSimilarArtist>, Streamable {
	
	private Double match;
	private String mbid;
	private boolean streamable;

	public ComparableSimilarArtist() {
		super();		
	}

	public ComparableSimilarArtist(List<Image> images, URL url, String name,
			Double match, String mbid) {
		super(images, url, name);
		this.match = match;
		this.mbid = mbid;
	}

	public Double getMatch() {
		return match;
	}

	public void setMatch(Double match) {
		this.match = match;
	}

	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}
		

	@Override
	public String toString() {
		return "ComparableSimilarArtist [match=" + match + ", mbid=" + mbid
				+ ", getImages()=" + getImages() + ", getUrl()=" + getUrl()
				+ ", getUrlString()=" + getUrlString() + ", getName()="
				+ getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((mbid == null) ? 0 : mbid.hashCode());
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
		ComparableSimilarArtist other = (ComparableSimilarArtist) obj;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (mbid == null) {
			if (other.mbid != null)
				return false;
		} else if (!mbid.equals(other.mbid))
			return false;
		return true;
	}

	@JsonIgnore
	public int compareTo(ComparableSimilarArtist o) {
		return this.match.compareTo(o.match);
	}

	@Override
	@JsonProperty("url")
	public void setUrl(String url) {
		if(StringUtils.isBlank(url))	{
			throw new NullArgumentException("url");
		}
		
		if(StringUtils.startsWith(url, "http://") || StringUtils.startsWith(url, "https://"))	{
			super.setUrl(url);
		} else {
			super.setUrl("http://" + url);
		}
	}
	
	@JsonIgnore
	public void setStreamable(boolean streamable) {
		this.streamable = streamable;
	}
	
	@JsonProperty("streamable")
	public void setStreamable(int streamable) {
		this.streamable = BooleanUtils.toBoolean(streamable);
	}
	
	@JsonProperty("streamable")
	public String getStreamable() {
		return streamable ? "1" : "0";
	}
	
	@JsonIgnore
	public boolean isStreamable() {
		return streamable;
	}

	

}
