package moteefeObj;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("state")
	private String state;
	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("user_id")
	private String user_id;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("campaigns")
	ArrayList <Campaigns> campaigns = new ArrayList <Campaigns> ();
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	 
	public ArrayList<Campaigns> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(ArrayList<Campaigns> campaigns) {
		this.campaigns = campaigns;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", state=" + state + ", created_at=" + created_at + ", user_id="
				+ user_id + ", slug=" + slug + ", campaigns=" + campaigns + "]";
	}
 
}
