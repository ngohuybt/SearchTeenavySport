package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaigns {
	@JsonProperty("currency")
	private String id;
	@JsonProperty("facebook_pixel")
    private String facebook_pixel;
	@JsonProperty("name")
    private String name;
	@JsonProperty("campaign_mockups")
    private Campaign_mockups[] campaign_mockups;
	@JsonProperty("user_id")
    private String user_id;
	@JsonProperty("slug")
    private String slug;
	@JsonProperty("campaign_products")
    private Campaign_products[] campaign_products;
	@JsonProperty("currency_code")
    private String currency_code;
 

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getFacebook_pixel ()
    {
        return facebook_pixel;
    }

    public void setFacebook_pixel (String facebook_pixel)
    {
        this.facebook_pixel = facebook_pixel;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Campaign_mockups[] getCampaign_mockups ()
    {
        return campaign_mockups;
    }

    public void setCampaign_mockups (Campaign_mockups[] campaign_mockups)
    {
        this.campaign_mockups = campaign_mockups;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    public Campaign_products[] getCampaign_products ()
    {
        return campaign_products;
    }

    public void setCampaign_products (Campaign_products[] campaign_products)
    {
        this.campaign_products = campaign_products;
    }

    public String getCurrency_code ()
    {
        return currency_code;
    }

    public void setCurrency_code (String currency_code)
    {
        this.currency_code = currency_code;
    }

     
    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", facebook_pixel = "+facebook_pixel+", name = "+name+", campaign_mockups = "+campaign_mockups+", user_id = "+user_id+", slug = "+slug+", campaign_products = "+campaign_products+", currency_code = "+currency_code +"]";
    }
}
