package moteefeObj; 

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sizes1 {
	@JsonProperty("units")
	private Units[] units;
	 
	@JsonProperty("code")
	private String code;

	 
	public Units[] getUnits() {
		return units;
	}

	public void setUnits(Units[] units) {
		this.units = units;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Sizes [units=" + Arrays.toString(units) + ", code=" + code + "]";
	}

	 
}
