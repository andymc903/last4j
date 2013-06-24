package co.smartshuffle.last.fm.entities;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import co.smartshuffle.last.fm.common.Constants;

public class BaseMusicEntity {

	protected String name;
	protected URL url;
	protected String mbid;
	
	

	public BaseMusicEntity(String name, URL url, String mbid) {
		super();
		setMbid(mbid);
		setName(name);
		setUrl(url);
	}



	public BaseMusicEntity() {
		super();
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@JsonIgnore
	public URL getUrl() {
		return url;
	}



	public void setUrl(URL url) {
		this.url = url;
	}



	public String getMbid() {
		return mbid;
	}



	public void setMbid(String mbid) {
		this.mbid = mbid;
	}
	
	@JsonProperty("url")
	public String getUrlString() {
		return url.toString();
	}


	@JsonProperty("url")
	public void setUrl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mbid == null) ? 0 : mbid.hashCode());
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
		BaseMusicEntity other = (BaseMusicEntity) obj;
		if (mbid == null) {
			if (other.mbid != null)
				return false;
		} else if (!mbid.equals(other.mbid))
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
        return "BaseMusicEntity [name=" + name + ", url=" + url + ", mbid=" + mbid + "]";
    }
	
	
	
	

}