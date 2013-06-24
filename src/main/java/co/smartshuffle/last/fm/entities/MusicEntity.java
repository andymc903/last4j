package co.smartshuffle.last.fm.entities;

import java.net.URL;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class MusicEntity extends BaseMusicEntity {
	protected List<Tag> tags;
	protected Biography wiki;
	
	
	public MusicEntity(String name, URL url, String mbid, List<Tag> tags, Biography wiki) {
		super();
		setName(name);
		setMbid(mbid);
		setTags(tags);
		setUrl(url);
		setWiki(wiki);
	}
	
	public MusicEntity()	{
		//null object
	}
	
	@JsonProperty("toptags")
	public List<Tag> getTags() {
		return tags;
	}

	@JsonProperty("toptags")
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	

	@JsonProperty("wiki")
	public Biography getWiki() {
		return wiki;
	}

	@JsonProperty("wiki")
	public void setWiki(Biography wiki) {
		this.wiki = wiki;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		MusicEntity other = (MusicEntity) obj;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (wiki == null) {
			if (other.wiki != null)
				return false;
		} else if (!wiki.equals(other.wiki))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MusicEntity [tags=" + tags + ", wiki=" + wiki + ", getName()="
				+ getName() + ", getUrl()=" + getUrl() + ", getMbid()="
				+ getMbid() + "]";
	}	
	
}
