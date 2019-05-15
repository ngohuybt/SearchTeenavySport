package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign_mockups {
	@JsonProperty("id")
	private String id;
	@JsonProperty("product_id")
    private String product_id;
	@JsonProperty("side")
    private String side;
	@JsonProperty("color_id")
    private String color_id;
	@JsonProperty("alt")
    private String alt;
	@JsonProperty("image")
    private Image image;
	@JsonProperty("mockup_id")
    private String mockup_id;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public void setProduct_id (String product_id)
    {
        this.product_id = product_id;
    }

    public String getSide ()
    {
        return side;
    }

    public void setSide (String side)
    {
        this.side = side;
    }

    public String getColor_id ()
    {
        return color_id;
    }

    public void setColor_id (String color_id)
    {
        this.color_id = color_id;
    }

    public String getAlt ()
    {
        return alt;
    }

    public void setAlt (String alt)
    {
        this.alt = alt;
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getMockup_id ()
    {
        return mockup_id;
    }

    public void setMockup_id (String mockup_id)
    {
        this.mockup_id = mockup_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", product_id = "+product_id+", side = "+side+", color_id = "+color_id+", alt = "+alt+", image = "+image+", mockup_id = "+mockup_id+"]";
    }
}
