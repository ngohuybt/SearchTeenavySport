package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign_products {
	@JsonProperty("id")
	private String id;
	@JsonProperty("first_color_name")
	private String first_color_name;
	@JsonProperty("color_ids")
	private String[] color_ids;
	@JsonProperty("price")
	private String price;
	@JsonProperty("product_id")
	private String product_id;
	@JsonProperty("campaign_name")
	private String campaign_name;
	@JsonProperty("campaign_id")
	private String campaign_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_color_name() {
		return first_color_name;
	}

	public void setFirst_color_name(String first_color_name) {
		this.first_color_name = first_color_name;
	}

	public String[] getColor_ids() {
		return color_ids;
	}

	public void setColor_ids(String[] color_ids) {
		this.color_ids = color_ids;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", first_color_name = " + first_color_name + ", color_ids = " + color_ids
				+ ", price = " + price + ", product_id = " + product_id + ", campaign_name = " + campaign_name
				+ ", campaign_id = " + campaign_id + "]";
	}
}
