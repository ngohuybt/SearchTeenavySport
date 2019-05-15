package moteefeObj;

import java.util.HashMap;
import java.util.Map;

public class TemplatePestra {

	String Product_ID;
	String Title;
	String Description;
	String Category;
	String Tag;
	String Size;
	String Color;
	String Image;
	String Price;
	String SKU;
	String Quantity;

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public void assignTemplateNoTag(String[] fields, String title, String ProductID, String quantity,  Map<String, String> hashColumn ) {
		Product_ID = ProductID.replace("\"", "");
		Title = title.replace("\"", "");
		Size = fields[Integer.valueOf(hashColumn.get("Option1 Value".toLowerCase()))].replace("\"", ""); 
		Color = fields[Integer.valueOf(hashColumn.get("Option2 Value".toLowerCase()))].replace("\"", "");
		Image = fields[Integer.valueOf(hashColumn.get("Image src".toLowerCase()))].replace("\"", "");
		Price = fields[Integer.valueOf(hashColumn.get("Variant Price".toLowerCase()))].replace("\"", "");
		//Tag = fields[Integer.valueOf(hashColumn.get("Tags".toLowerCase()))].replace("\"", "");
		SKU = fields[Integer.valueOf(hashColumn.get("Variant SKU".toLowerCase()))].replace("\"", ""); 
		Quantity = quantity.replace("\"", ""); 
	}
	public void assignTemplate(String[] fields, String title, String ProductID, String quantity,  Map<String, String> hashColumn,String Tags
			 ) {
					Product_ID = ProductID.replace("\"", "");
					Title = title.replace("\"", "");
					Size = fields[Integer.valueOf(hashColumn.get("Option1 Value".toLowerCase()))].replace("\"", ""); 
					Color = fields[Integer.valueOf(hashColumn.get("Option2 Value".toLowerCase()))].replace("\"", "");
					Image = fields[Integer.valueOf(hashColumn.get("Image src".toLowerCase()))].replace("\"", "");
					Price = fields[Integer.valueOf(hashColumn.get("Variant Price".toLowerCase()))].replace("\"", "");
					Tag = fields[Integer.valueOf(hashColumn.get("Tags".toLowerCase()))].replace("\"", "");
					SKU = fields[Integer.valueOf(hashColumn.get("Variant SKU".toLowerCase()))].replace("\"", ""); 
					Quantity = quantity.replace("\"", ""); 
				}
	public void assignFullTemplateNoTag(TemplatePestra temp,String Des,String category,Map<String, String> hashColumn) {
		Product_ID = temp.getProduct_ID();
		Title = temp.getTitle();
		Size = temp.getSize(); 
		Color = temp.getColor();
		Image = temp.getImage();
		Price = temp.getPrice();
 		SKU = temp.getSKU();
		Quantity = temp.getQuantity();
		Description ="Don’t hesitate, let’s buy "+temp.getTitle()+" now \n"+Des ;
		Category=category;
 
	 
	}
	public void assignFullTemplate(TemplatePestra temp,String Des,String category,Map<String, String> hashColumn) {
		Product_ID = temp.getProduct_ID();
		Title = temp.getTitle();
		Size = temp.getSize(); 
		Color = temp.getColor();
		Image = temp.getImage();
		Price = temp.getPrice();
		Tag = temp.getTag();
		SKU = temp.getSKU();
		Quantity = temp.getQuantity();
		Description ="Don’t hesitate, let’s buy "+temp.getTitle()+" now \n"+Des ;
		Category=category;
 
	 
	}
}
