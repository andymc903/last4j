package co.smartshuffle.last.fm.api.interfaces;

import java.util.List;

import javax.naming.LimitExceededException;

import co.smartshuffle.last.fm.entities.BaseMusicEntity;
import co.smartshuffle.last.fm.entities.Tag;
import co.smartshuffle.last.fm.entities.artist.Artist;
import co.smartshuffle.last.fm.entities.artist.ComparableSimilarArtist;
import co.smartshuffle.last.fm.entities.artist.TopAlbum;
import co.smartshuffle.last.fm.entities.artist.TopTrack;
import co.smartshuffle.last.fm.entities.events.Event;

public interface ArtistAPI {
	
	/**
	 * Use the last.fm corrections data to check whether the supplied artist has a correction to a canonical artist
	 * @param name : The artist name to correct
	 * @return An artist correction object
	 */
	
	public BaseMusicEntity getCorrection(String name);
	
	public Artist getInfo(String artistNameorMbid);
	public Artist getInfo(String artistNameorMbid, boolean autoCorrect);
	
	public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid);
	public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid, boolean autoCorrect);
	public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid, int limit);
	public List<ComparableSimilarArtist> getSimilarArtists(String artistOrMbid, int limit, boolean autoCorrect);
	
	public List<Tag> getTopTags(String nameOrMbid);	
	public List<Tag> getTopTags(String nameOrMbid, boolean autoCorrect);
	
	public List<Event> getUpcomingEvents(String nameOrMbid);
	public List<Event> getUpcomingEvents(String nameOrMbid, boolean autoCorrect);
	public List<Event> getUpcomingEvents(String nameOrMbid, int limit, int page);
	public List<Event> getUpcomingEvents(String nameOrMbid, int limit, int page, boolean autoCorrect);
	
	public List<Event> getFestivals(String nameOrMbid);
    public List<Event> getFestivals(String nameOrMbid, boolean autoCorrect);
    public List<Event> getFestivals(String nameOrMbid, int limit, int page);
    public List<Event> getFestivals(String nameOrMbid, int limit, int page, boolean autoCorrect);
    
    public List<Event> getPastEvents(String nameOrMbid);
    public List<Event> getPastEvents(String nameOrMbid, boolean autoCorrect);
    public List<Event> getPastEvents(String nameOrMbid, int limit, int page);
    public List<Event> getPastEvents(String nameOrMbid, int limit, int page, boolean autoCorrect);
    
    public List<TopAlbum> getTopAlbums(String nameOrMbid);
    public List<TopAlbum> getTopAlbums(String nameOrMbid, boolean autocorrect);
    public List<TopAlbum> getTopAlbums(String nameOrMbid, int page, int limit);
    public List<TopAlbum> getTopAlbums(String nameOrMbid, int page, int limit, boolean autocorrect);
    
    public List<TopTrack> getTopTracks(String nameOrMbid);
    public List<TopTrack> getTopTracks(String nameOrMbid, boolean autocorrect);
    public List<TopTrack> getTopTracks(String nameOrMbid, int limit, int page);
    public List<TopTrack> getTopTracks(String nameOrMbid, int limit, int page, boolean autocorrect);	
	
}
