package searchTeenavySport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.helper.StringUtil;

import moteefeObj.LinkTitle;
import moteefeObj.TemplatePestra;
import searchTeenavySport.CSVUtils;
import searchTeenavySport.Template;
 
public class CommondV2 {
	static SimpleDateFormat ft = new SimpleDateFormat("dd_MM_YYYY_hh_mmss");
	static SimpleDateFormat ft2 = new SimpleDateFormat("dd_MM_YYYY");
	static public boolean saveCSV(ArrayList<String> a,String namefile) {

		 //
			if (namefile.isEmpty()) {
				namefile = "Export";
			}
			FileWriter writer;
			Date date = new Date();
			try {
				String csvFile = namefile + "_" + ft.format(date) + ".csv";
				writer = new FileWriter(csvFile);
				String[] arrayLink = {};
				String link1 = "";
				String title1 = "";
				CSVUtils.writeLine(writer, Arrays.asList("URL", "Title"), ',', '"');
				Template temp1 = new Template();
				for (int i = 0; i < a.size(); i++) {

					link1 = a.get(i);
					List<String> list = new ArrayList<>();
					list.add(link1);
					if (!title1.isEmpty())
						list.add(title1);

					CSVUtils.writeLine(writer, list, ',', ' ');
				}
				writer.flush();
				writer.close(); 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return true;
		}
	
	static public boolean saveLinkTitleCSV(ArrayList<LinkTitle> a,String namefile) {

		if (namefile.isEmpty()) {
				namefile = "Export";
			}
			FileWriter writer;
			Date date = new Date();
			try {
				String csvFile = namefile + "_" + ft.format(date) + ".csv";
				writer = new FileWriter(csvFile);
				String[] arrayLink = {};
				String link1 = "";
				String title1 = "";
				CSVUtils.writeLine(writer, Arrays.asList("URL", "Title"), ',', '"');
				Template temp1 = new Template();
				for (int i = 0; i < a.size(); i++) {

					link1 = a.get(i).getBuyProductLink();
					title1 = a.get(i).getTitle();
					List<String> list = new ArrayList<>();
					list.add(link1);
					list.add(title1);

					CSVUtils.writeLine(writer, list, ',', ' ');
				}
				writer.flush();
				writer.close(); 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return true;
		}
	
	static StringBuffer getContentURL(String urlLink) throws IOException {
		URL url;
		StringBuffer doc = new StringBuffer();
		try {
			url = new URL(urlLink);
			// Get the input stream through URL Connection
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";

			// read each line and write to //System.out
			while ((line = br.readLine()) != null) {
				doc.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}

	static String getValueSort(String sort) {
		String result = "";
		switch (sort) {
		case "Sort by Newest":
			result = "new";
			break;
		case "Sort by Best Sellers":
			result = "sales";
			break;
		case "Sort by Most Popular":
			result = "popular";
			break;
		case "Sort by Most Relevant":
			result = "relevance";
			break;
		default:
			break;
		}

		return result;
	}

	static String getValueProductType(String product) {
		product = product.trim();
		String result = "";
		switch (product) {
		case "T-Shirts":
			result = "tshirt";
			break;
		case "V-Necks":
			result = "vneck";
			break;
		case "Tank Tops":
			result = "tanktop";
			break;
		case "Long Sleeve Tees":
			result = "longsleeve";
			break;
		case "Crew Sweatshirts":
			result = "sweatshirt";
			break;
		case "Hoodie":
			result = "hoodie";
			break;
		case "Leggings":
			result = "leggings";
			break;
		case "Mugs":
			result = "Mug";
			break;
		case "Posters":
			result = "posters";
			break;
		case "Canvas":
			result = "canvas";
			break;
		default:
			break;
		}

		return result;
	}

	static int getValueCatogeries(String catogeries) {
		int result = 0;
		switch (catogeries) {
		case "Fitness":
			result = 61;
			break;
		case "Faith":
			result = 26;
			break;
		case "Drinking":
			result = 78;
			break;
		case "Automotive":
			result = 52;
			break;
		case "LifeStyle":
			result = 43;
			break;
		case "Jobs":
			result = 79;
			break;
		case "Hobby":
			result = 82;
			break;
		case "Holidays":
			result = 35;
			break;

		case "Geek-Tech":
			result = 24;
			break;
		case "Gamer":
			result = 13;
			break;
		case "Funny":
			result = 19;
			break;
		case "Zombies":
			result = 11;
			break;
		case "TV Shows":
			result = 34;
			break;
		case "States":
			result = 77;
			break;
		case "Sports":
			result = 27;
			break;
		case "Political":
			result = 17;
			break;
		case "Pets":
			result = 62;
			break;
		case "Outdoor":
			result = 81;
			break;
		case "Names":
			result = 75;
			break;
		case "Music":
			result = 71;
			break;
		case "Movies":
			result = 12;
			break;
		default:
			break;
		}

		return result;
	}

	static List<String> dateRanK(int month1, int month2, int year1, int year2) {
		List<String> listReturn = new ArrayList<String>();

		return listReturn;
	}

	static boolean saveFile(List<String> listObj, String exfile, String nameFile) {
		Date date = new Date();
		boolean returnFlag = true;
		String csvFile = nameFile + "_" + ft.format(date) + "." + exfile;

		try {
			FileWriter writer = new FileWriter(csvFile);

			for (int i = 0; i < listObj.size(); i++) {
				List<String> list = new ArrayList<>();
				list.add(listObj.get(i).toString());

				CSVUtils.writeLine(writer, list);
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnFlag = false;
		}
		return returnFlag;
	}
	static boolean saveFileTXT( String  listObj, String exfile, String nameFile) {
		Date date = new Date();
		boolean returnFlag = true;
		String csvFile = nameFile + "_" + ft.format(date) + "." + exfile;
		FileWriter fw;
		try {
			//Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
			File inFile = new File(csvFile);
		    
            if (!inFile.isFile()) {
            	  fw = new FileWriter(inFile);
            }
			  BufferedReader br = new BufferedReader(new FileReader(csvFile));
		 
		     fw = new FileWriter(inFile,true);

		     //Bước 2: Ghi dữ liệu
		     fw.write(listObj);

		     //Bước 3: �?óng luồng
		     fw.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnFlag = false;
		}
		return returnFlag;
	}
	static boolean saveFileTXT22( String  listObj, String exfile, String nameFile) {
		Date date = new Date();
		boolean returnFlag = true;
		String csvFile = nameFile + "_" + ft2.format(date) + "." + exfile;
		FileWriter fw;
		try {
			//Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
			File inFile = new File(csvFile);
		    
            if (!inFile.isFile()) {
            	  fw = new FileWriter(inFile);
            }
			  BufferedReader br = new BufferedReader(new FileReader(csvFile));
		 
		     fw = new FileWriter(inFile,true);

		     //Bước 2: Ghi dữ liệu
		     fw.write(listObj);

		     //Bước 3: �?óng luồng
		     fw.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnFlag = false;
		}
		return returnFlag;
	}
	static boolean saveFileString(String string, String exfile, String nameFile, String folder) {
		Date date = new Date();
		boolean returnFlag = true;
		String csvFile = nameFile + "_" + ft.format(date) + "." + exfile;
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(nameFile + date.getDate() + date.getMonth() + date.getYear() + "_"
					+ date.getHours() + date.getMinutes() + date.getSeconds() + "." + exfile));
			writer.write(string);

		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
		return returnFlag;
	}

	static List<Template2> convertObj(List<Template> temp) {
		List<Template2> list = new ArrayList<Template2>();
		for (Template temp1 : temp) {
			Template2 tempReturn;
			tempReturn = new Template2();
			tempReturn.setTshirt_title(temp1.getTitle());
			tempReturn.setTshirt_description(temp1.getSEODescription());
			tempReturn.setTshirt_sku(temp1.getVariantSKU());
			tempReturn.setTshirt_sale_price(temp1.getVariantprice());
			tempReturn.setTshirt_source_url(temp1.getSourceURL());
			tempReturn.setTshirt_source_url_image(temp1.getImagesrc());
			tempReturn.setTshirt_color(temp1.getOption2Value());

			List<CatalogTerms> lstCa = new ArrayList<CatalogTerms>();
			CatalogTerms ca = new CatalogTerms();
			ca.setName(temp1.getTags());
			lstCa.add(ca);
			tempReturn.setCatalog_terms(lstCa);
			list.add(tempReturn);
		}

		return list;
	}

	public static void exportCSVTemplate3(ArrayList<Template3> listObj, String name ) {
		Date date = new Date();
		String nameFile="";
		if(!StringUtil.isBlank(name))
			nameFile=name;
		else
			nameFile="Ger_";
		String csvFile = nameFile + "_" + ft.format(date) + ".csv";
		
		try {
			FileWriter writer = new FileWriter(csvFile);
			CSVUtils.writeLine(writer, Arrays.asList( "Title", "Name", "Keyword") );

			for (int i = 0; i < listObj.size(); i++) {
				List<String> list = new ArrayList<>(); 
				list.add(listObj.get(i).getFinal_URL());
				list.add(listObj.get(i).getTitle()); 
				list.add(listObj.get(i).getTags() ); 
				 
				CSVUtils.writeLine(writer, list, ',', '"');
			}
			writer.flush();
			writer.close();
			// JOptionPane.showMessageDialog(jFrame, "Excel written successful 			// ");
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//	public static void exportCSV(ArrayList<Template> listObj, String name ) {
//		Date date = new Date();
//		String nameFile="";
//		if(!StringUtil.isBlank(name))
//			nameFile=name;
//		else
//			nameFile="WORDPRESS";
//		String csvFile = nameFile + "_" + ft.format(date) + ".csv";
//		
//		try {
//			FileWriter writer = new FileWriter(csvFile);
//			CSVUtils.writeLine(writer, Arrays.asList( "Handle", "Title", "Body (HTML)", "Vendor", "Link", 
//					"Option1 Name", "Option1 Value", "Option2 Name", "Option2 Value",
//					"Option3 Name", "Option3 Value", "Variant SKU",   "Variant price",
//					"Variant Compare At Price",  
//					"Image src" ),
//					',', '"');
//
//			for (int i = 0; i < listObj.size(); i++) {
//				List<String> list = new ArrayList<>();
//				list.add(listObj.get(i).getHandle());
//				list.add(listObj.get(i).getTitle());
//				list.add(listObj.get(i).getBodyHTML());
//				list.add("Sunfog");
//				list.add(listObj.get(i).getSourceURL()); 
//				list.add(listObj.get(i).getOption1Name());
//				list.add(listObj.get(i).getOption1Value());// Size
//				list.add(listObj.get(i).getOption2Name());// Color
//				list.add(listObj.get(i).getOption2Value());// Color
//				list.add(listObj.get(i).getOption3Name());// Pattern
//				list.add(listObj.get(i).getOption3Value());// Style
//				list.add(listObj.get(i).getVariantSKU()); 
//				list.add(listObj.get(i).getVariantprice());// Price
//				list.add(listObj.get(i).getVariantCompareAtPrice()); 
//				list.add(listObj.get(i).getImagesrc());// StringLinkImg 
//				CSVUtils.writeLine(writer, list, ',', '"');
//			}
//			writer.flush();
//			writer.close();
//			// JOptionPane.showMessageDialog(jFrame, "Excel written successful
//			// ");
//		} catch (
//
//		IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	
	public static String exportCSV(ArrayList<Template> listObj, String name ) {
		Date date = new Date();
		String nameFile="";
		if(!StringUtil.isBlank(name))
			nameFile=name;
		else
			nameFile="WORDPRESS";
		String csvFile = nameFile + "_" + ft.format(date) +  ".csv";
		
		try {
			FileWriter writer = new FileWriter(csvFile);
			CSVUtils.writeLine(writer, Arrays.asList( "Link", "Title", "Image","Price","Categories","Tags" ),
					',', '"');

			for (int i = 0; i < listObj.size(); i++) {
				List<String> list = new ArrayList<>();
				list.add(listObj.get(i).getLink());
				list.add(listObj.get(i).getTitle());
				list.add(listObj.get(i).getImage());
				list.add(listObj.get(i).getPrice());
				list.add(listObj.get(i).getCategories());
				list.add(listObj.get(i).getTags());
				
				CSVUtils.writeLine(writer, list, ',', '"');
			}
			writer.flush();
			writer.close();
			// JOptionPane.showMessageDialog(jFrame, "Excel written successful
			// ");
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csvFile;
	}
	
	public static void exportXLSTemplatePestra(ArrayList<TemplatePestra> listObj) {
		Date date = new Date();
		SimpleDateFormat fort = new SimpleDateFormat("dd_MM_YYYY_hh_mm");
		String csvFile = "XLSPestra" + "_" + fort.format(date) + ".xls";
		ArrayList<String> listHeader = new ArrayList<String>();
		listHeader.add("Product ID");
		listHeader.add("Title");
		listHeader.add("Description");
		listHeader.add("Category");
		listHeader.add("Size");
		listHeader.add("Color");
		listHeader.add("Image");
		listHeader.add("Price");
		listHeader.add("SKU");
		listHeader.add("Quantity");
		listHeader.add("Condition");

		XSSFWorkbook workbook = new XSSFWorkbook();
		 
		XSSFSheet sheet = workbook.createSheet("Pestra");// creating a blank
															// sheet
		int rowNum = 0;
		// log.debug("Creating excel");
		Row header = sheet.createRow(rowNum);
		for (int i = 0; i < listHeader.size(); i++) {
			header.createCell(i).setCellValue(listHeader.get(i));
		}
		for (int i = 0; i < listObj.size(); i++) {
			rowNum++;
			Row dataRow = sheet.createRow(rowNum);
			Object template;

			dataRow.createCell(0).setCellValue(listObj.get(i).getProduct_ID());
			dataRow.createCell(1).setCellValue(listObj.get(i).getTitle());
			dataRow.createCell(2).setCellValue(listObj.get(i).getDescription());
			dataRow.createCell(3).setCellValue(listObj.get(i).getCategory());
			dataRow.createCell(4).setCellValue(listObj.get(i).getSize());
			dataRow.createCell(5).setCellValue(listObj.get(i).getColor());
			dataRow.createCell(6).setCellValue(listObj.get(i).getImage());
			dataRow.createCell(7).setCellValue(listObj.get(i).getPrice());
			dataRow.createCell(8).setCellValue(listObj.get(i).getSKU());
			dataRow.createCell(9).setCellValue(listObj.get(i).getQuantity());
			dataRow.createCell(10).setCellValue(1);
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(csvFile));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// log.debug("Done");

	}
	
	public static void exportCSVTemplateWP(ArrayList<TemplatePestra> listObj) {
		Date date = new Date();
		SimpleDateFormat fort = new SimpleDateFormat("dd_MM_YYYY_hh_mm");
		try {
			String csvFile = "CSV_WP" + "_" + fort.format(date) + ".csv";
			FileWriter writer = new FileWriter(csvFile);
			CSVUtils.writeLine(writer, Arrays.asList("Title", "Description","Tags", "Option1 Value", "Option2 Value",
					"Image src", "Price"), ',', '"');

			for (int i = 0; i < listObj.size(); i++) {
				List<String> list = new ArrayList<>();
 				list.add(listObj.get(i).getTitle());
				list.add(listObj.get(i).getDescription());
				list.add(listObj.get(i).getTag());
 				list.add(listObj.get(i).getSize());
				list.add(listObj.get(i).getColor());
				list.add(listObj.get(i).getImage());
				list.add(listObj.get(i).getPrice());
 				CSVUtils.writeLine(writer, list, ',', '"');
			}
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static String replaceString(ArrayList<String> arr, String MainString,String Newstring) {
	    for (int i = 0; i < arr.size(); i++) {
	        if (MainString.contains(arr.get(i))) {
	        	MainString=MainString.replace(arr.get(i), Newstring);
	            
	        }
	    }
		return MainString;
	}

}
