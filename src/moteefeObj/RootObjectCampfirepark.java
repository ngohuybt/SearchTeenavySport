package moteefeObj;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootObjectCampfirepark {
	@JsonProperty("@id")
	private String id;
	@JsonProperty("variants")
	ArrayList <VariantCampfirepark> variants = new ArrayList <VariantCampfirepark> ();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<VariantCampfirepark> getVariants() {
		return variants;
	}

	public void setVariants(ArrayList<VariantCampfirepark> variants) {
		this.variants = variants;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + "variants=" + variants + "]";
	}
}
