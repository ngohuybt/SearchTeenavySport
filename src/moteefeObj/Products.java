package moteefeObj;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {
	@JsonProperty("sizes")
	private Sizes[] sizes;
	@JsonProperty("id")
    private String id;
	@JsonProperty("category")
    private String category;
	@JsonProperty("details")
    private String details;
	@JsonProperty("colors")
    private Colors[] colors;
	@JsonProperty("name")
    private String name;
	@JsonProperty("tracking_name")
    private String tracking_name;
	@JsonProperty("size_guide_image")
    private Size_guide_image size_guide_image;
	@JsonProperty("size_guidelines")
    private Size_guidelines[] size_guidelines;
	@JsonProperty("colors_sizes")
    private String[][] colors_sizes;
	@JsonProperty("supercategory")
    private String supercategory;

    public Sizes[] getSizes ()
    {
        return sizes;
    }

    public void setSizes (Sizes[] sizes)
    {
        this.sizes = sizes;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getDetails ()
    {
        return details;
    }

    public void setDetails (String details)
    {
        this.details = details;
    }

    public Colors[] getColors ()
    {
        return colors;
    }

    public void setColors (Colors[] colors)
    {
        this.colors = colors;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTracking_name ()
    {
        return tracking_name;
    }

    public void setTracking_name (String tracking_name)
    {
        this.tracking_name = tracking_name;
    }

    public Size_guide_image getSize_guide_image ()
    {
        return size_guide_image;
    }

    public void setSize_guide_image (Size_guide_image size_guide_image)
    {
        this.size_guide_image = size_guide_image;
    }

    public Size_guidelines[] getSize_guidelines ()
    {
        return size_guidelines;
    }

    public void setSize_guidelines (Size_guidelines[] size_guidelines)
    {
        this.size_guidelines = size_guidelines;
    }

    public String[][] getColors_sizes ()
    {
        return colors_sizes;
    }

    public void setColors_sizes (String[][] colors_sizes)
    {
        this.colors_sizes = colors_sizes;
    }

    public String getSupercategory ()
    {
        return supercategory;
    }

    public void setSupercategory (String supercategory)
    {
        this.supercategory = supercategory;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sizes = "+sizes+", id = "+id+", category = "+category+", details = "+details+", colors = "+colors+", name = "+name+", tracking_name = "+tracking_name+", size_guide_image = "+size_guide_image+", size_guidelines = "+size_guidelines+", colors_sizes = "+colors_sizes+", supercategory = "+supercategory+"]";
    }
}
