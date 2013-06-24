package co.smartshuffle.last.fm.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.web.client.RestTemplate;

import co.smartshuffle.last.fm.api.interfaces.ApiKey;
import co.smartshuffle.last.fm.api.interfaces.ArtistAPI;
import co.smartshuffle.last.fm.api.responses.ArtistCorrection;
import co.smartshuffle.last.fm.api.responses.ArtistGetInfo;
import co.smartshuffle.last.fm.api.responses.GetArtistEvents;
import co.smartshuffle.last.fm.api.responses.GetTopAlbums;
import co.smartshuffle.last.fm.api.responses.GetTopTracks;
import co.smartshuffle.last.fm.api.responses.SimilarArtist;
import co.smartshuffle.last.fm.api.responses.TopArtistTags;
import co.smartshuffle.last.fm.common.Constants;
import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Tag;
import co.smartshuffle.last.fm.entities.artist.Artist;
import co.smartshuffle.last.fm.entities.artist.ComparableSimilarArtist;
import co.smartshuffle.last.fm.entities.artist.TopAlbum;
import co.smartshuffle.last.fm.entities.artist.TopTrack;
import co.smartshuffle.last.fm.entities.events.Event;

public class ArtistApiClient implements ArtistAPI {
    
    private final ApiKey apiKey;
    private final RestTemplate restTemplate;

    public ArtistApiClient(ApiKey apiKey) {
        super();
        this.apiKey = apiKey;
        restTemplate = ClientUtils.getRestTemplate();
    }

    public ArtistApiClient(ApiKey apiKey, RestTemplate restTemplate) {
        super();
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public BaseMusicEntity getCorrection(String name) {
        Map<String, String> params = new HashMap<String, String>(4);
        params.put("method", "artist.getcorrection");
        params.put("format", "json");
        
        params.put("apiKey", apiKey.getApiKey());
        params.put("artist", name);
        
        String url = Constants.ROOT_URL + "method={method}&format={format}&api_key={apiKey}&artist={artist}";
        ArtistCorrection response = restTemplate.getForObject(url, ArtistCorrection.class, params);
        
        return response.getArtist();
    }

    public Artist getInfo(String artistNameorMbid) {
        return getInfo(artistNameorMbid, true);
    }
    
    public Artist getInfo(String artistNameorMbid, boolean autoCorrect) {
        Map<String, String> params = new HashMap<String, String>(3);
        String correct = autoCorrect ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        String url = Constants.ROOT_URL + "method=artist.getinfo&format=json&api_key={apiKey}&autocorrect={auto}";        
        if(Constants.MBID_REG_EX.matcher(artistNameorMbid).matches())   {
            params.put("mbid", artistNameorMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", artistNameorMbid);
            url += "&artist={artist}";
        }
        ArtistGetInfo artistInfo = restTemplate.getForObject(url, ArtistGetInfo.class, params);
        return artistInfo.getArtist();
    }

    public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid) {
        return getSimilarArtists(artistOrMbid, true);
    }
    
    public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid, boolean autoCorrect) {
        return getSimilarArtists(artistOrMbid, 10, autoCorrect);
    }   
    

    public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid, int limit) {
        return getSimilarArtists(artistOrMbid, limit, true);
    }

    public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid, int limit, boolean autoCorrect) {
        Map<String, String> params = new HashMap<String, String>(4);
        String correct = autoCorrect ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        params.put("limit", Integer.valueOf(limit).toString());
        String url = Constants.ROOT_URL + "method=artist.getsimilar&format=json&api_key={apiKey}&autocorrect={auto}&limit={limit}";
        
        //add mbid or artist name to request
        if(Constants.MBID_REG_EX.matcher(artistOrMbid).matches())   {
            params.put("mbid", artistOrMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", artistOrMbid);
            url += "&artist={artist}";
        }
        
        SimilarArtist response = restTemplate.getForObject(url, SimilarArtist.class, params);
        return response.getArtists();
    }

    public List<Tag> getTopTags(String nameOrMbid) {
        return getTopTags(nameOrMbid, true);
    }

    public List<Tag> getTopTags(String nameOrMbid, boolean autoCorrect) {
        Map<String, String> params = new HashMap<String, String>(3);
        String correct = autoCorrect ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        String url = Constants.ROOT_URL + "method=artist.gettoptags&format=json&api_key={apiKey}&autocorrect={auto}";
        //add mbid or artist name to request
        if(Constants.MBID_REG_EX.matcher(nameOrMbid).matches())   {
            params.put("mbid", nameOrMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", nameOrMbid);
            url += "&artist={artist}";
        }
        TopArtistTags response = restTemplate.getForObject(url, TopArtistTags.class, params);
        return response.getTopTags();
    }

    public List<Event> getUpcomingEvents(String nameOrMbid) {
        return getUpcomingEvents(nameOrMbid, true);
    }

    public List<Event> getUpcomingEvents(String nameOrMbid, boolean autoCorrect) {
        return getUpcomingEvents(nameOrMbid, 50, 0, autoCorrect);
    }

    public List<Event> getUpcomingEvents(String nameOrMbid, int limit, int page) {
        return getUpcomingEvents(nameOrMbid, limit, page, true);
    }

    public List<Event> getUpcomingEvents(String nameOrMbid, int limit, int page, boolean autoCorrect) {
        return getEvents(nameOrMbid, limit, page, autoCorrect, false);
    }
    
    private List<Event> getEvents(String nameOrMbid, int limit, int page, boolean autoCorrect, boolean festivalsOnly) {
        Map<String, String> params = new HashMap<String, String>(6);
        String correct = autoCorrect ? "1" : "0";
        String festivals = festivalsOnly ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        params.put("limit", Integer.valueOf(limit).toString());
        params.put("page", Integer.valueOf(page).toString());
        params.put("festivals", festivals);
        String url = Constants.ROOT_URL + "method=artist.getevents&format=json&api_key={apiKey}&autocorrect={auto}&limit={limit}&page={page}&festivalsonly={festivals}";
        
        //add mbid or artist name to request
        if(Constants.MBID_REG_EX.matcher(nameOrMbid).matches())   {
            params.put("mbid", nameOrMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", nameOrMbid);
            url += "&artist={artist}";
        }
        
        GetArtistEvents events = restTemplate.getForObject(url, GetArtistEvents.class, params);
        return events.getEvents();
    }

    public List<Event> getFestivals(String nameOrMbid) {
        return getFestivals(nameOrMbid, true);
    }

    public List<Event> getFestivals(String nameOrMbid, boolean autoCorrect) {
        return getFestivals(nameOrMbid, 50, 0, autoCorrect);
    }

    public List<Event> getFestivals(String nameOrMbid, int limit, int page) {
        return getFestivals(nameOrMbid, limit, page, true);
    }

    public List<Event> getFestivals(String nameOrMbid, int limit, int page, boolean autoCorrect) {
        return getEvents(nameOrMbid, limit, page, autoCorrect, true);
    }

    public List<Event> getPastEvents(String nameOrMbid) {
        return getPastEvents(nameOrMbid, true);
    }

    public List<Event> getPastEvents(String nameOrMbid, boolean autoCorrect) {
        return getPastEvents(nameOrMbid, 50, 0, autoCorrect);
    }

    public List<Event> getPastEvents(String nameOrMbid, int limit, int page) {
        return getPastEvents(nameOrMbid, limit, page, true);
    }

    public List<Event> getPastEvents(String nameOrMbid, int limit, int page, boolean autoCorrect) {
        Map<String, String> params = new HashMap<String, String>(5);
        String correct = autoCorrect ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        params.put("limit", Integer.valueOf(limit).toString());
        params.put("page", Integer.valueOf(page).toString());
        String url = Constants.ROOT_URL + "method=artist.getpastevents&format=json&api_key={apiKey}&autocorrect={auto}&limit={limit}&page={page}";
        
        //add mbid or artist name to request
        if(Constants.MBID_REG_EX.matcher(nameOrMbid).matches())   {
            params.put("mbid", nameOrMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", nameOrMbid);
            url += "&artist={artist}";
        }
        
        GetArtistEvents events = restTemplate.getForObject(url, GetArtistEvents.class, params);
        return events.getEvents();
    }

    public List<TopAlbum> getTopAlbums(String nameOrMbid) {
        return getTopAlbums(nameOrMbid, true);
    }

    public List<TopAlbum> getTopAlbums(String nameOrMbid, boolean autocorrect) {
        return getTopAlbums(nameOrMbid, 0, 50, autocorrect);
    }

    public List<TopAlbum> getTopAlbums(String nameOrMbid, int page, int limit) {
        return getTopAlbums(nameOrMbid, page, limit, true);
    }

    public List<TopAlbum> getTopAlbums(String nameOrMbid, int page, int limit, boolean autocorrect) {
        Map<String, String> params = new HashMap<String, String>(5);
        String correct = autocorrect ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        params.put("limit", Integer.valueOf(limit).toString());
        params.put("page", Integer.valueOf(page).toString());
        String url = Constants.ROOT_URL + "method=artist.gettopalbums&format=json&api_key={apiKey}&autocorrect={auto}&limit={limit}&page={page}";
        
        //add mbid or artist name to request
        if(Constants.MBID_REG_EX.matcher(nameOrMbid).matches())   {
            params.put("mbid", nameOrMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", nameOrMbid);
            url += "&artist={artist}";
        }
        
        GetTopAlbums topAlbums = restTemplate.getForObject(url, GetTopAlbums.class, params);
        return topAlbums.getAlbums();
    }

    public List<TopTrack> getTopTracks(String nameOrMbid) {
        return getTopTracks(nameOrMbid, true);
    }

    public List<TopTrack> getTopTracks(String nameOrMbid, boolean autocorrect) {
        return getTopTracks(nameOrMbid, 50, 0, autocorrect);
    }

    public List<TopTrack> getTopTracks(String nameOrMbid, int limit, int page) {
        return getTopTracks(nameOrMbid, limit, page, true);
    }

    public List<TopTrack> getTopTracks(String nameOrMbid, int limit, int page, boolean autocorrect) {
        Map<String, String> params = new HashMap<String, String>(5);
        String correct = autocorrect ? "1" : "0";
        params.put("apiKey", apiKey.getApiKey());        
        params.put("auto", correct);
        params.put("limit", Integer.valueOf(limit).toString());
        params.put("page", Integer.valueOf(page).toString());
        String url = Constants.ROOT_URL + "method=artist.gettoptracks&format=json&api_key={apiKey}&autocorrect={auto}&limit={limit}&page={page}";
        
        //add mbid or artist name to request
        if(Constants.MBID_REG_EX.matcher(nameOrMbid).matches())   {
            params.put("mbid", nameOrMbid);
            url += "&mbid={mbid}";
        }else {
            params.put("artist", nameOrMbid);
            url += "&artist={artist}";
        }
        
        GetTopTracks topTracks = restTemplate.getForObject(url, GetTopTracks.class, params);
        return topTracks.getTopTracks();
    }

    

}
