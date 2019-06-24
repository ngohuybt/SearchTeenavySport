package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorCampfirepark {
	@JsonProperty("@id")
	private String id;
	@JsonProperty("imageFeatured")
	private String imageFeatured;
	@JsonProperty("hex")
	private String hex;
	@JsonProperty("name")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageFeatured() {
		return imageFeatured;
	}

	public void setImageFeatured(String imageFeatured) {
		this.imageFeatured = imageFeatured;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + "imageFeatured=" + imageFeatured + ",hex=" + hex + ",name=" + name + "]";
	}
}
