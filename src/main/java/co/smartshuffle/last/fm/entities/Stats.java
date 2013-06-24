package co.smartshuffle.last.fm.entities;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect
public class Stats {
	private Long listeners, playCount;

	@JsonIgnore
	public Stats(Long listeners, Long playCount) {
		super();
		this.listeners = listeners;
		this.playCount = playCount;
	}

	public Stats() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("listeners")
	public Long getListeners() {
		return listeners;
	}
	
	@JsonProperty("listeners")
	public void setListeners(Long listeners) {
		this.listeners = listeners;
	}
	
	@JsonProperty("playcount")
	public Long getPlayCount() {
		return playCount;
	}
	
	@JsonProperty("playcount")
	public void setPlayCount(Long playCount) {
		this.playCount = playCount;
	}

	@Override
	@JsonIgnore
	public String toString() {
		return "Stats [listeners=" + listeners + ", playCount=" + playCount
				+ "]";
	}

	@Override
	@JsonIgnore
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listeners == null) ? 0 : listeners.hashCode());
		result = prime * result
				+ ((playCount == null) ? 0 : playCount.hashCode());
		return result;
	}

	@Override
	@JsonIgnore
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stats other = (Stats) obj;
		if (listeners == null) {
			if (other.listeners != null)
				return false;
		} else if (!listeners.equals(other.listeners))
			return false;
		if (playCount == null) {
			if (other.playCount != null)
				return false;
		} else if (!playCount.equals(other.playCount))
			return false;
		return true;
	}
	
	
}
