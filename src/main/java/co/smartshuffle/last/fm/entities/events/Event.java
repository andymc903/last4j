package co.smartshuffle.last.fm.entities.events;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import co.smartshuffle.last.fm.entities.Image;
import co.smartshuffle.last.fm.entities.interfaces.ImagedItem;

@JsonIgnoreProperties({"tag", "id"})
public class Event implements ImagedItem{
    private String headLiner, title, description;
    private List<String> artists, tags;
    private Venue venue;
    private Date startDate, endDate; 
    private int attendance, reviews;
    private URL website, tickets, url;
    private List<Image> images;
    private boolean canceled;
    private final  DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
    
    public Event() {
        super();
    }

    public String getHeadLiner() {
        return headLiner;
    }

    @JsonIgnore
    public void setHeadLiner(String headLiner) {
        this.headLiner = headLiner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getArtists() {
        return artists;
    }

    @JsonIgnore
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
    
    @JsonProperty("artists")
    @JsonRawValue
    public void setArtistInfo(JsonNode root)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String headLiner = root.get("headliner").getTextValue();
            JsonNode artistListNode = root.get("artist");
            List<String> artistList;
            try {
                
                //Try Get list
                artistList = mapper.readValue(artistListNode, new TypeReference<List<String>>() {});
                
            }catch(JsonMappingException e)  {
                
                //Most likely there is only one artist playing this event
                String artist = mapper.readValue(artistListNode, String.class);
                artistList = new ArrayList<String>();
                artistList.add(artist);
                
            }
            
            this.headLiner = headLiner;
            this.artists = artistList;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> getTags() {
        return tags;
    }

    @JsonIgnore
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    @JsonProperty("tags")
    @JsonRawValue
    private void setTags(JsonNode json) {
        JsonNode root = json.get("tag");
        
        String rawJson = root.toString();
        List<String> newTagList = new ArrayList<String>();
        String[] tags = rawJson.split(",");
        for (String tag : tags) {
            newTagList.add(StringUtils.trimToEmpty(tag));
        }            
        setTags(newTagList);
        
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Date getStartDate() {
        return startDate;
    }

    @JsonIgnore
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public void setStartDate(String startDate) {
        try {
            formatter.parse(startDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public URL getWebsite() {
        return website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }

    public URL getTickets() {
        return tickets;
    }

    public void setTickets(URL tickets) {
        this.tickets = tickets;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("image")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    public boolean isCanceled() {
        return canceled;
    }

    @JsonIgnore
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    
    @JsonProperty("cancelled")
    public void setCanceled(int canceled) {
        this.canceled = BooleanUtils.toBoolean(canceled);
    }

    public Date getEndDate() {
        return endDate;
    }
    
    @JsonIgnore
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }    
    
    public void setEndDate(String endDate) {
        if(StringUtils.isNotBlank(endDate)) {
            try {
                Date newEnd = formatter.parse(endDate);
                this.endDate = newEnd;
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.endDate = null;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
        result = prime * result + attendance;
        result = prime * result + (canceled ? 1231 : 1237);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((headLiner == null) ? 0 : headLiner.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + reviews;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((tickets == null) ? 0 : tickets.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((venue == null) ? 0 : venue.hashCode());
        result = prime * result + ((website == null) ? 0 : website.hashCode());
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
        Event other = (Event) obj;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        if (attendance != other.attendance)
            return false;
        if (canceled != other.canceled)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (headLiner == null) {
            if (other.headLiner != null)
                return false;
        } else if (!headLiner.equals(other.headLiner))
            return false;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (reviews != other.reviews)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        if (tickets == null) {
            if (other.tickets != null)
                return false;
        } else if (!tickets.equals(other.tickets))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        if (venue == null) {
            if (other.venue != null)
                return false;
        } else if (!venue.equals(other.venue))
            return false;
        if (website == null) {
            if (other.website != null)
                return false;
        } else if (!website.equals(other.website))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [headLiner=" + headLiner + ", title=" + title + ", description=" + description + ", artists=" + artists + ", tags=" + tags + ", venue=" + venue
                + ", startDate=" + startDate + ", attendance=" + attendance + ", reviews=" + reviews + ", website=" + website + ", tickets=" + tickets + ", url=" + url
                + ", images=" + images + ", canceled=" + canceled + "]";
    }
    
    

    
}
