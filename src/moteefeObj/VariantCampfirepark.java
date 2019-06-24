package moteefeObj;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantCampfirepark {
	@JsonProperty("@id")
	private String id;
	@JsonProperty("colors")
	ArrayList <ColorCampfirepark> colors = new ArrayList <ColorCampfirepark> ();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<ColorCampfirepark> getColors() {
		return colors;
	}

	public void setColors(ArrayList<ColorCampfirepark> colors) {
		this.colors = colors;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + "colors=" + colors + "]";
	}
}
