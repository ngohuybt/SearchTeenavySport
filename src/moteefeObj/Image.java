package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	@JsonProperty("preview")
	private String preview;
	@JsonProperty("big")
    private String big;
	@JsonProperty("thumb")
    private String thumb;

    public String getPreview ()
    {
        return preview;
    }

    public void setPreview (String preview)
    {
        this.preview = preview;
    }

    public String getBig ()
    {
        return big;
    }

    public void setBig (String big)
    {
        this.big = big;
    }

    public String getThumb ()
    {
        return thumb;
    }

    public void setThumb (String thumb)
    {
        this.thumb = thumb;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [preview = "+preview+", big = "+big+", thumb = "+thumb+"]";
    }
}
