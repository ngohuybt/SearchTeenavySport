package searchTeenavySport;

import java.util.Comparator;

public class Template{
	String Handle;
	String Title;
	String BodyHTML;
	String Vendor="Sunfrog"	;
	String Type="Apparel";
	String Tags	;
	String Published="TRUE"	;
	String Option1Name="Size"	;
	String Option1Value	;//Size
	String Option2Name="Color"	;//Color
	String Option2Value	;//Color
	String Option3Name="Pattern"	;//Pattern
	String Option3Value	;//Style
	String VariantSKU	;
	String VariantGrams="0";	
	String VariantInventoryTracker	;
	String VariantInventoryQty="1";
	String VariantInventoryPolicy="deny"	;
	String VariantFulfillmentService	="manual";
	String Variantprice	;//Price
	String VariantCompareAtPrice	;
	String VariantRequiresShipping	="TRUE";
	String VariantTaxable="TRUE";
	String VariantBarcode	;
	String Imagesrc	;//	StringLinkImg
	String ImageAltText	;
	String GiftCard	="FALSE";
	String GoogleShoppingMPN	;
	String GoogleShoppingAgeGroup="Adult"	;
	String GoogleShoppingGender="Unisex"	;
	String GoogleShoppingGoogleProductCategory="Apparel & Accessories > Clothing > Shirts & Tops";	
	String SEOTitle	;
	String SEODescription	;
	String GoogleShoppingAdWordsGrouping	="T-Shirt";
	String GoogleShoppingAdWordsLabels	="T-Shirt";
	String GoogleShoppingCondition="New";
	String GoogleShoppingCustomProduct	="TRUE";
	String GoogleShoppingCustomLabel0	;
	String GoogleShoppingCustomLabel1	;
	String GoogleShoppingCustomLabel2	;
	String GoogleShoppingCustomLabel3	;
	String GoogleShoppingCustomLabel4	;
	String VariantImage;//	StringLinkImg
	String VariantWeightUnit="lb";
	String VariantTaxCode;
	String SourceURL;
	
	public String getSourceURL() {
		return SourceURL;
	}
	public void setSourceURL(String sourceURL) {
		SourceURL = sourceURL;
	}
	public String getHandle() {
		return Handle;
	}
	public void setHandle(String handle) {
		Handle = handle;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getBodyHTML() {
		return BodyHTML;
	}
	public void setBodyHTML(String bodyHTML) {
		BodyHTML = bodyHTML;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public String getPublished() {
		return Published;
	}
	public void setPublished(String published) {
		Published = published;
	}
	public String getOption1Name() {
		return Option1Name;
	}
	public void setOption1Name(String option1Name) {
		Option1Name = option1Name;
	}
	public String getOption1Value() {
		return Option1Value;
	}
	public void setOption1Value(String option1Value) {
		Option1Value = option1Value;
	}
	public String getOption2Name() {
		return Option2Name;
	}
	public void setOption2Name(String option2Name) {
		Option2Name = option2Name;
	}
	public String getOption2Value() {
		return Option2Value;
	}
	public void setOption2Value(String option2Value) {
		Option2Value = option2Value;
	}
	public String getOption3Name() {
		return Option3Name;
	}
	public void setOption3Name(String option3Name) {
		Option3Name = option3Name;
	}
	public String getOption3Value() {
		return Option3Value;
	}
	public void setOption3Value(String option3Value) {
		Option3Value = option3Value;
	}
	public String getVariantSKU() {
		return VariantSKU;
	}
	public void setVariantSKU(String variantSKU) {
		VariantSKU = variantSKU;
	}
	public String getVariantGrams() {
		return VariantGrams;
	}
	public void setVariantGrams(String variantGrams) {
		VariantGrams = variantGrams;
	}
	public String getVariantInventoryTracker() {
		return VariantInventoryTracker;
	}
	public void setVariantInventoryTracker(String variantInventoryTracker) {
		VariantInventoryTracker = variantInventoryTracker;
	}
	public String getVariantInventoryQty() {
		return VariantInventoryQty;
	}
	public void setVariantInventoryQty(String variantInventoryQty) {
		VariantInventoryQty = variantInventoryQty;
	}
	public String getVariantInventoryPolicy() {
		return VariantInventoryPolicy;
	}
	public void setVariantInventoryPolicy(String variantInventoryPolicy) {
		VariantInventoryPolicy = variantInventoryPolicy;
	}
	public String getVariantFulfillmentService() {
		return VariantFulfillmentService;
	}
	public void setVariantFulfillmentService(String variantFulfillmentService) {
		VariantFulfillmentService = variantFulfillmentService;
	}
	public String getVariantprice() {
		return Variantprice;
	}
	public void setVariantprice(String variantprice) {
		Variantprice = variantprice;
	}
	public String getVariantCompareAtPrice() {
		return VariantCompareAtPrice;
	}
	public void setVariantCompareAtPrice(String variantCompareAtPrice) {
		VariantCompareAtPrice = variantCompareAtPrice;
	}
	public String getVariantRequiresShipping() {
		return VariantRequiresShipping;
	}
	public void setVariantRequiresShipping(String variantRequiresShipping) {
		VariantRequiresShipping = variantRequiresShipping;
	}
	public String getVariantTaxable() {
		return VariantTaxable;
	}
	public void setVariantTaxable(String variantTaxable) {
		VariantTaxable = variantTaxable;
	}
	public String getVariantBarcode() {
		return VariantBarcode;
	}
	public void setVariantBarcode(String variantBarcode) {
		VariantBarcode = variantBarcode;
	}
	public String getImagesrc() {
		return Imagesrc;
	}
	public void setImagesrc(String imagesrc) {
		Imagesrc = imagesrc;
	}
	public String getImageAltText() {
		return ImageAltText;
	}
	public void setImageAltText(String imageAltText) {
		ImageAltText = imageAltText;
	}
	public String getGiftCard() {
		return GiftCard;
	}
	public void setGiftCard(String giftCard) {
		GiftCard = giftCard;
	}
	public String getGoogleShoppingMPN() {
		return GoogleShoppingMPN;
	}
	public void setGoogleShoppingMPN(String googleShoppingMPN) {
		GoogleShoppingMPN = googleShoppingMPN;
	}
	public String getGoogleShoppingAgeGroup() {
		return GoogleShoppingAgeGroup;
	}
	public void setGoogleShoppingAgeGroup(String googleShoppingAgeGroup) {
		GoogleShoppingAgeGroup = googleShoppingAgeGroup;
	}
	public String getGoogleShoppingGender() {
		return GoogleShoppingGender;
	}
	public void setGoogleShoppingGender(String googleShoppingGender) {
		GoogleShoppingGender = googleShoppingGender;
	}
	public String getGoogleShoppingGoogleProductCategory() {
		return GoogleShoppingGoogleProductCategory;
	}
	public void setGoogleShoppingGoogleProductCategory(String googleShoppingGoogleProductCategory) {
		GoogleShoppingGoogleProductCategory = googleShoppingGoogleProductCategory;
	}
	public String getSEOTitle() {
		return SEOTitle;
	}
	public void setSEOTitle(String sEOTitle) {
		SEOTitle = sEOTitle;
	}
	public String getSEODescription() {
		return SEODescription;
	}
	public void setSEODescription(String sEODescription) {
		SEODescription = sEODescription;
	}
	public String getGoogleShoppingAdWordsGrouping() {
		return GoogleShoppingAdWordsGrouping;
	}
	public void setGoogleShoppingAdWordsGrouping(String googleShoppingAdWordsGrouping) {
		GoogleShoppingAdWordsGrouping = googleShoppingAdWordsGrouping;
	}
	public String getGoogleShoppingAdWordsLabels() {
		return GoogleShoppingAdWordsLabels;
	}
	public void setGoogleShoppingAdWordsLabels(String googleShoppingAdWordsLabels) {
		GoogleShoppingAdWordsLabels = googleShoppingAdWordsLabels;
	}
	public String getGoogleShoppingCondition() {
		return GoogleShoppingCondition;
	}
	public void setGoogleShoppingCondition(String googleShoppingCondition) {
		GoogleShoppingCondition = googleShoppingCondition;
	}
	public String getGoogleShoppingCustomProduct() {
		return GoogleShoppingCustomProduct;
	}
	public void setGoogleShoppingCustomProduct(String googleShoppingCustomProduct) {
		GoogleShoppingCustomProduct = googleShoppingCustomProduct;
	}
	public String getGoogleShoppingCustomLabel0() {
		return GoogleShoppingCustomLabel0;
	}
	public void setGoogleShoppingCustomLabel0(String googleShoppingCustomLabel0) {
		GoogleShoppingCustomLabel0 = googleShoppingCustomLabel0;
	}
	public String getGoogleShoppingCustomLabel1() {
		return GoogleShoppingCustomLabel1;
	}
	public void setGoogleShoppingCustomLabel1(String googleShoppingCustomLabel1) {
		GoogleShoppingCustomLabel1 = googleShoppingCustomLabel1;
	}
	public String getGoogleShoppingCustomLabel2() {
		return GoogleShoppingCustomLabel2;
	}
	public void setGoogleShoppingCustomLabel2(String googleShoppingCustomLabel2) {
		GoogleShoppingCustomLabel2 = googleShoppingCustomLabel2;
	}
	public String getGoogleShoppingCustomLabel3() {
		return GoogleShoppingCustomLabel3;
	}
	public void setGoogleShoppingCustomLabel3(String googleShoppingCustomLabel3) {
		GoogleShoppingCustomLabel3 = googleShoppingCustomLabel3;
	}
	public String getGoogleShoppingCustomLabel4() {
		return GoogleShoppingCustomLabel4;
	}
	public void setGoogleShoppingCustomLabel4(String googleShoppingCustomLabel4) {
		GoogleShoppingCustomLabel4 = googleShoppingCustomLabel4;
	}
	public String getVariantImage() {
		return VariantImage;
	}
	public void setVariantImage(String variantImage) {
		VariantImage = variantImage;
	}
	public String getVariantWeightUnit() {
		return VariantWeightUnit;
	}
	public void setVariantWeightUnit(String variantWeightUnit) {
		VariantWeightUnit = variantWeightUnit;
	}
	public String getVariantTaxCode() {
		return VariantTaxCode;
	}
	public void setVariantTaxCode(String variantTaxCode) {
		VariantTaxCode = variantTaxCode;
	}
	

	
	
	public static class CompTitle implements Comparator<Template>{
	
			@Override
			public int compare(Template o1,Template o2){
				return o1.getTitle().compareToIgnoreCase(o2.getTitle());
			}
	}
}
