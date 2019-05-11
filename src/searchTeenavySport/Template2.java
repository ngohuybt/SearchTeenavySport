package searchTeenavySport;

import java.util.Comparator;
import java.util.List;





public class Template2{
	 
	String id=null;
	String tshirt_title;
	String tshirt_description;
	String tshirt_sku	;
	String tshirt_sale_price;
	String tshirt_designer="New Design";
	String tshirt_source_url	;
	String tshirt_source_url_image;
	String tshirt_color;
	List<CatalogTerms> catalog_terms	;//Size
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTshirt_title() {
		return tshirt_title;
	}
	public void setTshirt_title(String tshirt_title) {
		this.tshirt_title = tshirt_title;
	}
	public String getTshirt_description() {
		return tshirt_description;
	}
	public void setTshirt_description(String tshirt_description) {
		this.tshirt_description = tshirt_description;
	}
	public String getTshirt_sku() {
		return tshirt_sku;
	}
	public void setTshirt_sku(String tshirt_sku) {
		this.tshirt_sku = tshirt_sku;
	}
	public String getTshirt_sale_price() {
		return tshirt_sale_price;
	}
	public void setTshirt_sale_price(String tshirt_sale_price) {
		this.tshirt_sale_price = tshirt_sale_price;
	}
	public String getTshirt_designer() {
		return tshirt_designer;
	}
	public void setTshirt_designer(String tshirt_designer) {
		this.tshirt_designer = tshirt_designer;
	}
	public String getTshirt_source_url() {
		return tshirt_source_url;
	}
	public void setTshirt_source_url(String tshirt_source_url) {
		this.tshirt_source_url = tshirt_source_url;
	}
	public String getTshirt_source_url_image() {
		return tshirt_source_url_image;
	}
	public void setTshirt_source_url_image(String tshirt_source_url_image) {
		this.tshirt_source_url_image = tshirt_source_url_image;
	}
	public List<CatalogTerms> getCatalog_terms() {
		return catalog_terms;
	}
	public void setCatalog_terms(List<CatalogTerms> catalog_terms) {
		this.catalog_terms = catalog_terms;
	}
	public String getTshirt_color() {
		return tshirt_color;
	}
	public void setTshirt_color(String tshirt_color) {
		this.tshirt_color = tshirt_color;
	}
 
	 
	
	
	 
}


