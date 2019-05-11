package searchTeenavySport;

public class CatalogTerms{
	  
	String term_id=null;
	String name;
	String slug=null;
	String term_group=null;
	String term_taxonomy_id=null;
	String taxonomy	="tshirt_cat";
	String description=null;
	String parent=null;
	int count=0;//Size
	public String getTerm_id() {
		return term_id;
	}
	public void setTerm_id(String term_id) {
		this.term_id = term_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getTerm_group() {
		return term_group;
	}
	public void setTerm_group(String term_group) {
		this.term_group = term_group;
	}
	public String getTerm_taxonomy_id() {
		return term_taxonomy_id;
	}
	public void setTerm_taxonomy_id(String term_taxonomy_id) {
		this.term_taxonomy_id = term_taxonomy_id;
	}
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
 
	
	 
}