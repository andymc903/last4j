package co.smartshuffle.last.fm.entities;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;

@JsonAutoDetect
public class Tag implements Comparable<Tag> {
		
	protected String name, url;
	protected int count;
	
	public Tag()	{
		//null object
	}
	
	@JsonIgnore
	public Tag(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(Tag o) {
        Integer other = Integer.valueOf(o.count);
        Integer mine = Integer.valueOf(this.count);
        return mine.compareTo(other);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
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
        Tag other = (Tag) obj;
        if (count != other.count)
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
        return "Tag [name=" + name + ", url=" + url + ", count=" + count + "]";
    }
		
	
		
}
