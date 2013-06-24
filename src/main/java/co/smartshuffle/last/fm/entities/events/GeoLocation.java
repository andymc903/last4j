package co.smartshuffle.last.fm.entities.events;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoLocation {
    private double latitude, longitude;

    public GeoLocation() {
        super();
    }

    public GeoLocation(double latitude, double longitude) {
        super();
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    @JsonProperty("geo:lat")
    public void setLatitude(double latitude) {
        if(latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException(String.format("Given latitude value [%s] was incorrect. Should be between -90 & 90 degrees", latitude));
        }
        this.latitude = latitude;
    }

    @JsonProperty("geo:long")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if(latitude > 180 || latitude < -180) {
            throw new IllegalArgumentException(String.format("Given latitude value [%s] was incorrect. Should be between -180 & 180 degrees", latitude));
        }
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        GeoLocation other = (GeoLocation) obj;
        if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GeoLocation [latitude=" + latitude + ", longitude=" + longitude + "]";
    }
    
    
    
    
}
