package co.smartshuffle.last.fm.common;

import java.util.regex.Pattern;



public class Constants {
	public static final Pattern MBID_REG_EX = Pattern.compile("^[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}$", Pattern.CASE_INSENSITIVE);
	
	public static final String ROOT_URL ="http://ws.audioscrobbler.com/2.0/?";
	
	public static final String SECURE_ROOT_URL ="https://ws.audioscrobbler.com/2.0/?";

}
