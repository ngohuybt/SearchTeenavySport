package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyPojo
{ 
	@JsonProperty("state")
    private State state;
	@JsonProperty("is_mobile")
    private String is_mobile;

   

    public State getState ()
    {
        return state;
    }

    public void setState (State state)
    {
        this.state = state;
    }

    public String getIs_mobile ()
    {
        return is_mobile;
    }

    public void setIs_mobile (String is_mobile)
    {
        this.is_mobile = is_mobile;
    }

	@Override
	public String toString() {
		return "MyPojo [state=" + state + ", is_mobile=" + is_mobile + "]";
	}

    
}
