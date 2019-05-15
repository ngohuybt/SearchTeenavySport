package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Size_guide_image {
	@JsonProperty("height")
	private String height;
	@JsonProperty("width")
	private String width;
	@JsonProperty("image")
	private String image;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ClassPojo [height = " + height + ", width = " + width + ", image = " + image + "]";
	}
}
