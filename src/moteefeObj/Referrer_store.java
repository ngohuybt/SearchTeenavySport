package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Referrer_store {
	@JsonProperty("is_whitelabel")
	private String is_whitelabel;
	@JsonProperty("name")
	private String name;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("logo_image")
	private String logo_image;

	public String getIs_whitelabel() {
		return is_whitelabel;
	}

	public void setIs_whitelabel(String is_whitelabel) {
		this.is_whitelabel = is_whitelabel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getLogo_image() {
		return logo_image;
	}

	public void setLogo_image(String logo_image) {
		this.logo_image = logo_image;
	}

	@Override
	public String toString() {
		return "Referrer_store [is_whitelabel=" + is_whitelabel + ", name=" + name + ", slug=" + slug + ", logo_image="
				+ logo_image + "]";
	}

}