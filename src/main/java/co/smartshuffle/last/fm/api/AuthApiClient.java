package co.smartshuffle.last.fm.api;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.smartshuffle.last.fm.api.interfaces.ApiKey;
import co.smartshuffle.last.fm.api.interfaces.AuthApi;
import co.smartshuffle.last.fm.api.responses.ArtistGetInfo;
import co.smartshuffle.last.fm.api.responses.GetSessionResponse;
import co.smartshuffle.last.fm.api.responses.GetTokenResponse;
import co.smartshuffle.last.fm.common.Constants;
import co.smartshuffle.last.fm.entities.auth.Session;

public class AuthApiClient implements AuthApi {

    private final ApiKey apiKey;
    private final RestTemplate restTemplate;

    public AuthApiClient(ApiKey key) {
        this.apiKey = key;
        this.restTemplate = ClientUtils.getRestTemplate();
    }

    public AuthApiClient(ApiKey apiKey, RestTemplate restTemplate) {
        super();
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public String getToken() {
        Map<String, String> params = new HashMap<String, String>(1);
        params.put("apiKey", apiKey.getApiKey());
        String url = Constants.ROOT_URL + "method=artist.getinfo&format=json&api_key={apiKey}";
        GetTokenResponse token = restTemplate.getForObject(url, GetTokenResponse.class, params);
        return token.getToken();
    }

    public Session getSession(String authToken) {
        
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("token", authToken);
        parameters.put("api_key", apiKey.getApiKey());
        parameters.put("method", "auth.getSession");
        String signature = getSignature(parameters);
        parameters.put("api_sig", signature);
        
        String url = Constants.SECURE_ROOT_URL + "method={method}&format=json&token={token}&api_key={api_key}&api_sig={api_sig}";
        
        ResponseEntity<GetSessionResponse> response = restTemplate.postForEntity(url, null, GetSessionResponse.class, parameters);
        
        return response.getBody().getSession();
        
    }

    public Session getMobileSession(String userName, String password) {
        MessageDigest digestor;
        Map<String, String> parameters = new HashMap<String, String>();

        // Initialize digestor
        try {
            digestor = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

        String authToken;
        
        //get authToken
        try {
            byte[] passworMd5 = digestor.digest(password.getBytes("UTF-8"));

            String authTokenString = userName + Hex.encodeHexString(passworMd5);
            byte[] tokenMD5 = digestor.digest(authTokenString.getBytes("UTF-8"));
            authToken = new String(Hex.encodeHexString(tokenMD5));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        parameters.put("authToken", authToken);
        parameters.put("method", "auth.getMobileSession");
        parameters.put("api_key", apiKey.getApiKey());
        parameters.put("username", userName);

        String signature = getSignature(parameters);
        parameters.put("api_sig", signature);

        String url = Constants.SECURE_ROOT_URL + "method={method}&format=json&authToken={authToken}&api_key={api_key}&username={username}&api_sig={api_sig}";
        
        ResponseEntity<GetSessionResponse> response = restTemplate.postForEntity(url, null, GetSessionResponse.class, parameters);
        
        return response.getBody().getSession();

    }

    private String getSignature(Map<String, String> parameters) {
        MessageDigest digestor;
        StringBuilder builder = new StringBuilder();

        // Initialize digestor
        try {
            digestor = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

        List<String> keys = new ArrayList<String>(parameters.keySet());
        Collections.sort(keys);

        //Build string <paramName><paramValue>
        for (String key : keys) {
            builder.append(key);
            builder.append(parameters.get(key));
        }

        // Append Secret key
        builder.append(this.apiKey.getSecretKey());

        byte[] signature;
        try {
            signature = builder.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        byte[] md5 = digestor.digest(signature);
        return new String(Hex.encodeHexString(md5));
    }

}
