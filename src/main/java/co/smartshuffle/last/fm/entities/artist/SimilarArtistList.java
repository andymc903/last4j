package co.smartshuffle.last.fm.entities.artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;


public class SimilarArtistList extends ArrayList<ComparableSimilarArtist> {

	public SimilarArtistList() {
		super();
	}

	public SimilarArtistList(Collection<? extends ComparableSimilarArtist> c) {
		super(c);
	}

	public SimilarArtistList(int initialCapacity) {
		super(initialCapacity);
	}
	
	@JsonCreator
	public static SimilarArtistList createSimilarArtistList(String json)	{
		if(StringUtils.isBlank(json))	{
			throw new NullArgumentException("json");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(json);
			JsonNode artistList = root.get("similarartists").get("artist");
			SimilarArtistList output = mapper.readValue(artistList, SimilarArtistList.class);
			return output;
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
	}
	
}
