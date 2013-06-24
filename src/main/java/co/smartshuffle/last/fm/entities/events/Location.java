package co.smartshuffle.last.fm.entities.events;

import org.codehaus.jackson.annotate.JsonProperty;

public class Location {
    private GeoLocation geo;
    private String city, country, street, postalcode;
    public Location() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Location(GeoLocation geo, String city, String country, String street, String postalcode) {
        super();
        setGeo(geo);
        setCity(city);
        setCountry(country);
        setStreet(street);
        setPostalcode(postalcode);
    }
    
    
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public GeoLocation getGeo() {
        return geo;
    }
    
    public String getPostalcode() {
        return postalcode;
    }
    public String getStreet() {
        return street;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @JsonProperty("geo:point")
    public void setGeo(GeoLocation geo) {
        this.geo = geo;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((geo == null) ? 0 : geo.hashCode());
        result = prime * result + ((postalcode == null) ? 0 : postalcode.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
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
        Location other = (Location) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (geo == null) {
            if (other.geo != null)
                return false;
        } else if (!geo.equals(other.geo))
            return false;
        if (postalcode == null) {
            if (other.postalcode != null)
                return false;
        } else if (!postalcode.equals(other.postalcode))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Location [geo=" + geo + ", city=" + city + ", country=" + country + ", street=" + street + ", postalcode=" + postalcode + "]";
    }
    
    
    
    
    
}
