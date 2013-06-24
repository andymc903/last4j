package co.smartshuffle.last.fm;

import java.util.List;

import co.smartshuffle.last.fm.api.ArtistApiClient;
import co.smartshuffle.last.fm.api.interfaces.ApiKey;
import co.smartshuffle.last.fm.api.interfaces.ArtistAPI;
import co.smartshuffle.last.fm.common.DefaultApiKey;
import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Tag;
import co.smartshuffle.last.fm.entities.artist.TopAlbum;
import co.smartshuffle.last.fm.entities.artist.TopTrack;
import co.smartshuffle.last.fm.entities.events.Event;

public class Example {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApiKey myApi = new DefaultApiKey("63d6d1ad32141af7b9d494e38876eb40", "59a8718095e51b82fcfcd62b352264cc");
        ArtistAPI artistClient = new ArtistApiClient(myApi);        
        List<TopTrack> trackList = artistClient.getTopTracks("Brand new");
        System.out.print(trackList);
    }

}
