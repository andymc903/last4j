package co.smartshuffle.last.fm.entities.auth;

import org.apache.commons.lang.BooleanUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Session {
    private String key, name;
    private boolean subscriber;
   
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @JsonIgnore
    public boolean isSubscriber() {
        return subscriber;
    }
    
    @JsonIgnore
    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }
    
    public void setSubscriber(int subscriber) {
        this.subscriber = BooleanUtils.toBoolean(subscriber);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (subscriber ? 1231 : 1237);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (subscriber != other.subscriber)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Session [key=" + key + ", name=" + name + ", subscriber=" + subscriber + "]";
    }
    
    
    
}
