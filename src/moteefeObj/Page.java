package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
	@JsonProperty("can_buy")
	private String can_buy;
	@JsonProperty("end_type")
    private String end_type;
	@JsonProperty("can_download")
    private String can_download;
	@JsonProperty("analytics_collections")
    private String analytics_collections;
	@JsonProperty("campaign_mockups")
    private Campaign_mockups[] campaign_mockups;
	@JsonProperty("campaign_products")
    private Campaign_products[] campaign_products;
	@JsonProperty("campaign")
    private Campaign campaign;
	@JsonProperty("products")
    private Products[] products;

    public String getCan_buy ()
    {
        return can_buy;
    }

    public void setCan_buy (String can_buy)
    {
        this.can_buy = can_buy;
    }

    public String getEnd_type ()
    {
        return end_type;
    }

    public void setEnd_type (String end_type)
    {
        this.end_type = end_type;
    }

    public String getCan_download ()
    {
        return can_download;
    }

    public void setCan_download (String can_download)
    {
        this.can_download = can_download;
    }

    public String getAnalytics_collections ()
    {
        return analytics_collections;
    }

    public void setAnalytics_collections (String analytics_collections)
    {
        this.analytics_collections = analytics_collections;
    }

    public Campaign_mockups[] getCampaign_mockups ()
    {
        return campaign_mockups;
    }

    public void setCampaign_mockups (Campaign_mockups[] campaign_mockups)
    {
        this.campaign_mockups = campaign_mockups;
    }

    public Campaign_products[] getCampaign_products ()
    {
        return campaign_products;
    }

    public void setCampaign_products (Campaign_products[] campaign_products)
    {
        this.campaign_products = campaign_products;
    }

    public Campaign getCampaign ()
    {
        return campaign;
    }

    public void setCampaign (Campaign campaign)
    {
        this.campaign = campaign;
    }

    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [can_buy = "+can_buy+", end_type = "+end_type+", can_download = "+can_download+", analytics_collections = "+analytics_collections+", campaign_mockups = "+campaign_mockups+", campaign_products = "+campaign_products+", campaign = "+campaign+", products = "+products+"]";
    }
}