package co.smartshuffle.last.fm.common;

import co.smartshuffle.last.fm.api.interfaces.ApiKey;

public class DefaultApiKey implements ApiKey {

	private final String apiKey, secretKey;
	
	public DefaultApiKey(String apiKey, String secretKey)	{
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}
	
	public String getApiKey() {
		return this.apiKey;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

}
