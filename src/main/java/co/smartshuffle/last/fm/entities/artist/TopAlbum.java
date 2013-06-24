package co.smartshuffle.last.fm.entities.artist;

import java.net.URL;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

@JsonIgnoreProperties("@attr")
public class TopAlbum extends BaseMusicEntity implements ImagedItem {

    private List<Image> images;
    private BaseMusicEntity artist;
    private long playCount;
    
    public TopAlbum() {
        super();
        // TODO Auto-generated constructor stub
    }
    public TopAlbum(String name, URL url, String mbid, List<Image> images, BaseMusicEntity artist, long playcount) {
        super(name, url, mbid);
        setImages(images);
        setArtist(artist);
    }
    
    public List<Image> getImages() {
        return images;
    }
    
    @JsonProperty("image")
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public BaseMusicEntity getArtist() {
        return artist;
    }
    public void setArtist(BaseMusicEntity artist) {
        this.artist = artist;
    }    
    
    public long getPlayCount() {
        return playCount;
    }
    
    @JsonProperty("playcount")
    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + (int) (playCount ^ (playCount >>> 32));
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
        TopAlbum other = (TopAlbum) obj;
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
        if (playCount != other.playCount)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "TopAlbum [images=" + images + ", artist=" + artist + ", playCount=" + playCount + ", name=" + name + ", url=" + url + ", mbid=" + mbid + "]";
    }

    

}
