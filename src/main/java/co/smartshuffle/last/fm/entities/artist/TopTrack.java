package co.smartshuffle.last.fm.entities.artist;

import java.net.URL;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.Stats;
import co.smartshuffle.last.fm.entities.album.AlbumTrack;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

public class TopTrack extends AlbumTrack implements ImagedItem {
    private List<Image> images;
    private Stats stats;

    public TopTrack() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TopTrack(String name, URL url, String mbid, long duration, BaseMusicEntity artist, boolean streamable, List<Image> images, Stats listeningStats) {
        super(name, url, mbid, duration, artist, streamable);
        setImages(images);
        setStats(listeningStats);
    }

    @JsonProperty("image")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("image")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonUnwrapped
    public Stats getStats() {
        return stats;
    }

    @JsonIgnore
    public void setStats(Stats stats) {
        this.stats = stats;
    }
    
    @JsonProperty("playcount")
    public void setPlayCount(long playcount) {
        if(this.stats == null)  {
            this.stats = new Stats();
        }
        
        this.stats.setPlayCount(Long.valueOf(playcount));
    }
    
    @JsonProperty("listeners")
    public void setListeners(long listeners) {
        if(this.stats == null)  {
            this.stats = new Stats();
        }
        
        this.stats.setListeners(Long.valueOf(listeners));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((stats == null) ? 0 : stats.hashCode());
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
        TopTrack other = (TopTrack) obj;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (stats == null) {
            if (other.stats != null)
                return false;
        } else if (!stats.equals(other.stats))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TopTrack [images=" + images + ", stats=" + stats + "]";
    }
    
}
