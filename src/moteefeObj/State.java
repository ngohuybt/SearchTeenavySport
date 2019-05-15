package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {
	@JsonProperty("referrer_store")
	private Referrer_store referrer_store;
	@JsonProperty("page")
	private Page page;
	@JsonProperty("support_email")
	private String support_email;
	@JsonProperty("store")
	private Store store;
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Referrer_store getReferrer_store() {
		return referrer_store;
	}

	public void setReferrer_store(Referrer_store referrer_store) {
		this.referrer_store = referrer_store;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getSupport_email() {
		return support_email;
	}

	public void setSupport_email(String support_email) {
		this.support_email = support_email;
	}

	@Override
	public String toString() {
		return "ClassPojo [referrer_store = " + referrer_store + ", page = " + page + ", support_email = "
				+ support_email + "]";
	}
}