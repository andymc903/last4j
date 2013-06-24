package co.smartshuffle.last.fm.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({"links","formationlist"})
public class Biography {
	
	private final DateFormat PUBLISHED_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	private final DateFormat YEAR_FORMED_FORMAT = new SimpleDateFormat("yyyy");
	private Date published, yearFormed;
	private String summary, content;
	
	public Biography(Date published, String summary, String content) {
		super();
		this.published = published;
		this.summary = summary;
		this.content = content;
	}
	
	public Biography()	{
		super();
	}

	public Date getPublished() {
		return published;
	}

	@JsonIgnore
	public void setPublished(Date published) {
		this.published = published;
	}
	
	@JsonProperty
	public void setPublished(String published) {
		try {
			this.published = PUBLISHED_FORMAT.parse(published);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@JsonProperty("published")
	public String getPublishedString() {
		return PUBLISHED_FORMAT.format(published);
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonIgnore
	public boolean hasYearFormed()	{
		return yearFormed != null;
	}

	@JsonIgnore
	public Date getYearFormed() {
		return yearFormed;
	}

	@JsonIgnore
	public void setYearFormed(Date yearFormed) {
		this.yearFormed = yearFormed;
	}
	
	@JsonProperty("yearformed")
	public void setYearFormed(String yearFormed) {
		try {
			this.yearFormed = YEAR_FORMED_FORMAT.parse(yearFormed);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@JsonProperty("yearformed")
	public String getYearFormedString() {
		return YEAR_FORMED_FORMAT.format(yearFormed);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((published == null) ? 0 : published.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result
				+ ((yearFormed == null) ? 0 : yearFormed.hashCode());
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
		Biography other = (Biography) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (published == null) {
			if (other.published != null)
				return false;
		} else if (!published.equals(other.published))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (yearFormed == null) {
			if (other.yearFormed != null)
				return false;
		} else if (!yearFormed.equals(other.yearFormed))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Biography [published=" + published + ", yearFormed="
				+ yearFormed + ", summary=" + summary + ", content=" + content
				+ "]";
	}
	
	
}
