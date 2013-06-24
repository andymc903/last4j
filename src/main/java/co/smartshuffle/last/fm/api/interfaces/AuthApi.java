package co.smartshuffle.last.fm.api.interfaces;

import co.smartshuffle.last.fm.entities.auth.Session;

public interface AuthApi {
    public String getToken();    
    public Session getSession(String authToken);
    public Session getMobileSession(String userName, String password);
}
