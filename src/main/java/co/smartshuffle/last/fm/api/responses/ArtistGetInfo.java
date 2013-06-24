package co.smartshuffle.last.fm.api.responses;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonRawValue;

import co.smartshuffle.last.fm.entities.artist.Artist;

public class ArtistGetInfo {
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    @JsonIgnore
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    @JsonRawValue
    public void setArtist(String json) {
        this.artist = Artist.createArtist(json);
    }
}
