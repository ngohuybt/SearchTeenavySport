package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Size_guidelines {
	@JsonProperty("sizes")
	private Sizes[] sizes;
	@JsonProperty("measure")
	private String measure;

	public Sizes[] getSizes() {
		return sizes;
	}

	public void setSizes(Sizes[] sizes) {
		this.sizes = sizes;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	@Override
	public String toString() {
		return "ClassPojo [sizes = " + sizes + ", measure = " + measure + "]";
	}
}
