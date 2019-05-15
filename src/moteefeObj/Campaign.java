package moteefeObj;

 
import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign {
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("id")
	private String id;
	@JsonProperty("core_mockup_id")
	private String core_mockup_id;
	@JsonProperty("duration")
	private String duration;
	@JsonProperty("facebook_pixel")
	private String facebook_pixel;
	@JsonProperty("video")
	 private String video;
	

	@JsonProperty("end_time")
	private String end_time;
	@JsonProperty("description")
	private String description;
	@JsonProperty("name")
	private String name;
	@JsonProperty("start_time")
	private String start_time;
	@JsonProperty("user_id")
	private String user_id;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("collections")
	private Collections[] collections;
	public String getFacebook_pixel() {
		return facebook_pixel;
	}

	public void setFacebook_pixel(String facebook_pixel) {
		this.facebook_pixel = facebook_pixel;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCore_mockup_id() {
		return core_mockup_id;
	}

	public void setCore_mockup_id(String core_mockup_id) {
		this.core_mockup_id = core_mockup_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
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

	public Collections[] getCollections() {
		return collections;
	}

	public void setCollections(Collections[] collections) {
		this.collections = collections;
	}

	@Override
	public String toString() {
		return "Campaign [currency=" + currency + ", id=" + id + ", core_mockup_id=" + core_mockup_id + ", duration="
				+ duration + ", facebook_pixel=" + facebook_pixel + ", video=" + video + ", end_time=" + end_time
				+ ", description=" + description + ", name=" + name + ", start_time=" + start_time + ", user_id="
				+ user_id + ", slug=" + slug + ", collections=" + Arrays.toString(collections) + "]";
	}

	 
}