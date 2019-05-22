package moteefeObj;

public class LinkTitle
{ 
    private String buyProductLink;
    private String title;
	public String getBuyProductLink() {
		return buyProductLink;
	}
	public void setBuyProductLink(String buyProductLink) {
		this.buyProductLink = buyProductLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LinkTitle(String buyProductLink, String title) {
		super();
		this.buyProductLink = buyProductLink;
		this.title = title;
	}
    
    
}
