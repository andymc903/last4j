package co.smartshuffle.last.fm.entities.events;

import java.net.URL;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

@JsonIgnoreProperties("id")
public class Venue implements ImagedItem {
    private String name, phone;
    private URL url, website;
    private Location location;
    private List<Image> images;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    @JsonProperty("phonenumber")
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public URL getUrl() {
        return url;
    }
    public void setUrl(URL url) {
        this.url = url;
    }
    public URL getWebsite() {
        return website;
    }
    public void setWebsite(URL website) {
        this.website = website;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    @JsonProperty("image")
    public List<Image> getImages() {
        return images;
    }
    
    @JsonProperty("image")
    public void setImages(List<Image> images) {
        this.images = images;
    }
    
    
    
    public Venue(String name, String phone, URL url, URL website, Location location, List<Image> images) {
        super();
        setName(name);
        setPhone(phone);
        setUrl(url);
        setWebsite(url);
        setLocation(location);
        setImages(images);
    }
    
    public Venue() {
        super();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((website == null) ? 0 : website.hashCode());
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
        Venue other = (Venue) obj;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        if (website == null) {
            if (other.website != null)
                return false;
        } else if (!website.equals(other.website))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Venue [name=" + name + ", phone=" + phone + ", url=" + url + ", website=" + website + ", location=" + location + ", images=" + images + "]";
    }
    
    
    
}
