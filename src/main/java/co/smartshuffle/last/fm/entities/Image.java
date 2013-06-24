package co.smartshuffle.last.fm.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Image {
	
	public enum ImageSize {
		SMALL("small"), MEDIUM("medium"), LARGE("large"), EXTRA_LARGE("extralarge"), MEGA("mega");
		
		private final String display;
		
		ImageSize(String display)	{
			this.display = display;
		}
		
		public String toString()	{
			return this.display;
		}
	}
	
	private BufferedImage bufferedImage;
	private URL url;
	private ImageSize size;
	
	public Image(String url, ImageSize size) {
		this();
		this.size = size;
		setUrl(url);
	}
	
	public Image()	{
		super();
	}

	@JsonProperty("#text")
	public String getUrl() {
		return url.toString();
	}
	
	@JsonProperty("#text")
	public void setUrl(String url) {
	    if(StringUtils.isNotBlank(url))    {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(url);
		}
	    } else {
            this.url = null;
        }
	}
		
	public ImageSize getSize() {
		return size;
	}	
	
	@JsonProperty("size")
	public void setSize(String size){
		if(size.equals(ImageSize.EXTRA_LARGE.toString()))	{
			this.size = ImageSize.EXTRA_LARGE;
		} else if(size.equals(ImageSize.LARGE.toString()))	{
			this.size = ImageSize.LARGE;
		} else if(size.equals(ImageSize.MEDIUM.toString()))	{
			this.size = ImageSize.MEDIUM;
		} else if (size.equals(ImageSize.MEGA.toString())) {
			this.size = ImageSize.MEGA;
		} else if(size.equals(ImageSize.SMALL.toString()))	{
			this.size = ImageSize.SMALL;
		} else {
			throw new IllegalArgumentException(String.format("The string %s does not mapp to an ImageSize enum", size));
		}
	}

	@JsonIgnore
	public void setSize(ImageSize size) {
		this.size = size;
	}
	
	@JsonIgnore
	public synchronized BufferedImage getBufferedImage() throws IOException {
		//Lazy Load
		if(bufferedImage == null)	{
			try	{
				bufferedImage = ImageIO.read(url);
			} catch(MalformedURLException e)	{
				// should not happen we check state
				// before setting member variable
				throw new IllegalStateException(e);
			}
		}
		return bufferedImage;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        Image other = (Image) obj;
        if (size != other.size)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Image [url=" + url + ", size=" + size + "]";
    }
	
	
	
	
}
