package searchTeenavySport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.UrlValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import moteefeObj.LinkTitle;

public class VinpremiumToExcel {

	protected Shell shlExportExcel;
	final JFrame jFrame = new JFrame("Simple hello Example");
	private String[] arraySize = { "S", "M", "L", "XL", "2X", "3X" };
	private List<String> arrayStyle = new ArrayList<String>();
	private List<String> arrayColor = new ArrayList<String>();
	private String csvFile = "";
	private String nameFileOutPut = "";
	private String sellerID = "";
	private String hostName = "";
	private static final Log log = LogFactory.getLog(VinpremiumToExcel.class);
	int[] styles = { SWT.SINGLE, SWT.MULTI };
	private Display display = new Display();
	private List<String> dataListView = new ArrayList<String>();
	private List<String> dataListLnk = new ArrayList<String>();
	HashMap<String, String> listColor;
	private Text textNameFileOut;
	Label labMessage;
	private CLabel lblSub;
	private Text sub;
	private CLabel lblPageend;
	private Text intpage;
	private Button button_1;
	ArrayList<String> listObjLink = new ArrayList<String>();
	ArrayList<LinkTitle> listLinkTitle = new ArrayList<LinkTitle>();
	private Button btnCheckNew;
	private Button btnCheckNewCach;
	private static Map<String, String> mapMenuId = new HashMap<String, String>();
	/**
	 * Launch the application. Sử dụng thread + Hàm con
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VinpremiumToExcel window = new VinpremiumToExcel();
			window.mapMenuId = getMapMenuId();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static int isValid(String url) {
    	URL u;
		try {
			u = new URL (url);
	    	HttpURLConnection huc;
			try {
				huc = ( HttpURLConnection )  u.openConnection ();
		    	huc.setRequestMethod ("GET"); //OR  huc.setRequestMethod ("HEAD"); 
		    	huc.connect () ; 
		    	int code = huc.getResponseCode() ;
		    	System.out.println(code);
		    	return code;
			} catch (IOException e) {
				 return 404; 
			} 
		} catch (MalformedURLException e1) {
			 return 404; 
		}
    } 
	
	public static Map<String, String> getMapMenuId() {
//		mapMenuId.put("BIRTHDAY", "li#menu-item-69408");
//		mapMenuId.put("FATHERS", "li#menu-item-78");
//		mapMenuId.put("MOTHERS", "li#menu-item-69428");
//		
//		mapMenuId.put("HALLOWEEN", "li#menu-item-79");
		mapMenuId.put("MOVIE", "li#menu-item-54840");
//		mapMenuId.put("CHRISTMAS", "li#menu-item-80");
//		mapMenuId.put("DOG", "li#menu-item-76");
//		
//		mapMenuId.put("GAMES", "li#menu-item-75");
//		mapMenuId.put("NURSE", "li#menu-item-54842");
//		mapMenuId.put("UNICORN", "li#menu-item-54843");
//		mapMenuId.put("TRUMP", "li#menu-item-54844");
		return mapMenuId;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlExportExcel.open();
		shlExportExcel.layout();
		while (!shlExportExcel.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shlExportExcel = new Shell();
		shlExportExcel.setSize(766, 674);
		shlExportExcel.setText("TeenaviToExcel");
		JFileChooser fileChooser = new JFileChooser();
		JButton saveBtn = new JButton("Save");
		JButton openBtn = new JButton("Open");

		FocusListener listener;
		/*
		 * comboCatogeries.addFocusListener(new ItemListener() { public void
		 * itemStateChanged(ItemEvent arg0) { //Do Something } });
		 */

		Group grrr = new Group(shlExportExcel, SWT.NONE);
		grrr.setBounds(10, 10, 731, 569);

		labMessage = new Label(grrr, SWT.NONE);
		labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		labMessage.setAlignment(SWT.RIGHT);
		labMessage.setBounds(38, 39, 242, 24);

		textNameFileOut = new Text(grrr, SWT.BORDER);
		textNameFileOut.setBounds(154, 93, 111, 25);

		CLabel label = new CLabel(grrr, SWT.NONE);
		label.setText("Name File Export");
		label.setBounds(25, 93, 123, 26);

		lblSub = new CLabel(grrr, SWT.NONE);
		lblSub.setText("Sub");
		lblSub.setBounds(25, 130, 123, 26);

		sub = new Text(grrr, SWT.BORDER);
		sub.setBounds(154, 130, 111, 25);

		lblPageend = new CLabel(grrr, SWT.NONE);
		lblPageend.setText("PageEnd");
		lblPageend.setBounds(25, 173, 123, 26);

		intpage = new Text(grrr, SWT.BORDER);
		intpage.setBounds(154, 173, 111, 25);

		button_1 = new Button(grrr, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				JFileChooser openFile = new JFileChooser();
				int returnVal = openFile.showOpenDialog(jFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getCurrentDirectory();
					java.io.File fileDir = openFile.getSelectedFile();

					csvFile = fileDir.getPath();
					// String extent=file.get
					try {
						FileReader fileReader = new FileReader(csvFile);
						BufferedReader br = new BufferedReader(fileReader);
						String stringRead = br.readLine();
						int i = 0;
						String linkFromFile = "";
						String[] strArray = {};
						while (stringRead != null) {
							boolean flag = true;

							strArray = stringRead.split(",");
							if (strArray.length > 0)

								linkFromFile = strArray[0].toString().trim();

							if (!linkFromFile.isEmpty()) {

								dataListLnk.add(linkFromFile);
							}
							// read the next line
							stringRead = br.readLine();
						}
						br.close();
					} catch (IOException ex) {

					}
				} else {
					System.out.println("File access cancelled by user.");
				}

			}
		});
		button_1.setText("Open File");
		button_1.setImage(SWTResourceManager.getImage(VinpremiumToExcel.class, "/png/001-folder.png"));
		button_1.setBounds(154, 222, 111, 30);
		Button button = new Button(grrr, SWT.NONE);
		button.setBounds(83, 337, 204, 37);
		button.setText("Export Excel");
		
		btnCheckNew = new Button(grrr, SWT.NONE);
		btnCheckNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				listLinkTitle = new ArrayList<LinkTitle>();
				String link = "https://ggshirts.net/product-sitemap.xml";
				StringBuffer content;
				String str;
				Document doc;
				try {
					System.setProperty("http.agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
					Document buyProduct;
					String linksitemap="";
					String fileName = "Sitemap";
					System.out.println("Find: " + link);
					content = Commond.getContentURL(link);
					str = content.toString();
					doc = Jsoup.parse(str, "", Parser.xmlParser());
					for (int i=0;i< doc.select("loc").size();i++) {
						linksitemap=doc.select("loc").get(i).text();
						System.out.println(linksitemap);
	    	        	buyProduct = Jsoup.parse(new URL(linksitemap), 100000);
			    		String buyProductLink = buyProduct.select("form.cart").attr("action") ;
			    		String title = buyProduct.select("div.product-info h1").get(0).text();
			    		LinkTitle linkTitle = new LinkTitle(buyProductLink, title);
			    		listLinkTitle.add(linkTitle) ;
					}
					Commond.saveLinkTitleCSV(listLinkTitle, "LinksVinpremium" + fileName);
					System.out.println("Save CSV successfully:" + "LinksVinpremium" + fileName);
					labMessage.setText("Save list data successfully");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		btnCheckNew.setText("Check New ");
		btnCheckNew.setBounds(358, 337, 204, 37);
		
		btnCheckNewCach = new Button(grrr, SWT.NONE);
		btnCheckNewCach.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				listObjLink = new ArrayList<String>();
				String link = "https://teenavi.com";
				String linkdetail;
				String linkTee;
				String Title;
				int submain = 0;
				String NameFileOut = "";
				int page = 0;
				String linkSunTotle;
				String Category="";
				StringBuffer contentChild;
				try {
					UrlValidator urlValidator = new UrlValidator();
					System.setProperty("http.agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");

					StringBuffer content = null;
					TemplateURLSitemap trm=new TemplateURLSitemap();
					String linkHref="";
					String str ="";
					Document document;
					Document doc ;
					Document docDetail ;
					String fileName = "";
					if (StringUtil.isBlank(link)) {
						System.out.println("NO link");
					} else 
					{
						if (!StringUtil.isBlank(intpage.getText())) {
							page = Integer.parseInt(intpage.getText());
						}
						if (!StringUtil.isBlank(textNameFileOut.getText())) {
							NameFileOut = textNameFileOut.getText();
						}
						doc = Jsoup.connect(link)
								.userAgent(
										"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
								.referrer("http://www.google.com").get();
						document = Jsoup.parse(doc.toString());
	
						//lay san pham tung menu
					    Set<String> set = mapMenuId.keySet();
//					    StringBuilder valueCSV = new StringBuilder();
					    for (String key : set) {
					    	fileName = fileName + "_" + key;
					    	System.out.println("Menu: " + key);
							String menuLink = document.select(mapMenuId.get(key)).select("a").first().attr("href") ;
							Document docPageForMenu = Jsoup.connect(menuLink).userAgent(
									"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
							.referrer("http://www.google.com").get();
							
					        int recordNumber = 0;
					        int pageNumber = 0;
							String number = docPageForMenu.select("p.woocommerce-result-count").first().text();
							recordNumber = Integer.parseInt(number.substring(number.indexOf("of") + 3, number.indexOf("results")).trim());
					        pageNumber = (int)recordNumber/12;
					        int mod = recordNumber % 12;
					        if(page > 0 && page < pageNumber) {
					        	pageNumber = page;
					        }
					        
					     	for (int i = 1; i <= pageNumber; i++) {
					     		System.out.println("Page: " + i);
					     		String urlValue = "";
					     		if(key.equals("FATHERS") || key.equals("MOTHERS")) {
					     			urlValue =  menuLink.substring(0,20) + "page/"+i+"/" + menuLink.substring(20);
					     		}else {
					     			urlValue = menuLink + "page/"+i+"/";
					     		}
					    		Document documentValue = Jsoup.connect(urlValue).userAgent(
										"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
								.referrer("http://www.google.com").get();
					    		Document buyProduct;
					    		for (int j = 0; j < 12; j++) {
					    			String productLink = documentValue.select("div.image-none a").get(j).attr("href") ;
//					    	        if (isValid(productLink) != 404) {  
//							    		buyProduct = Jsoup.connect(productLink).userAgent(
//												"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
//										.referrer("http://www.google.com").get();
			    	        	
							    		buyProduct = Jsoup.parse(new URL(productLink), 100000);
							    		String buyProductLink = buyProduct.select("form.cart").attr("action") ;
										if(!listObjLink.contains(buyProductLink) && !dataListLnk.contains(buyProductLink)){
											String title = buyProduct.select("div.product-info h1").get(0).text();
								    		LinkTitle linkTitle = new LinkTitle(buyProductLink, title);
								    		listLinkTitle.add(linkTitle) ;
							    			listObjLink.add(buyProductLink) ;
										}
//					    	        }else {
//					    	            System.out.println("No URL: " + productLink);   
//					    	        }
					    		}
					    	}
							
						}
						
						/*
						//---------------------------------------------
						//https://teenavi.com/product_cat-sitemap.xml
						link="https://teenavi.com/product_cat-sitemap.xml"; 
						System.out.println("Find https://teenavi.com/product_cat-sitemap.xml");
						content = Commond.getContentURL(link);
						str =content.toString();
						doc = Jsoup.parse(str, "", Parser.xmlParser());
						trm=new TemplateURLSitemap();
						for (int i=0;i< doc.select("loc").size();i++) {
							int pageEnd = 1;
							System.out.println("linkHref :"+doc.select("loc").get(i).text()); 
							linkHref=doc.select("loc").get(i).text();
							if(linkHref.equals("#")) continue;
							//Commond.saveFileTXT(linkHref + "\n", "txt", NameFileOut + "Submain");
							Document documentPage;
							Document docPage = Jsoup.connect(linkHref)
									.userAgent(
											"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
									.referrer("http://www.google.com").get();
							documentPage = Jsoup.parse(docPage.toString());
							if (documentPage.select("nav[class=woocommerce-pagination]").size() > 0) {
								int pageCount=documentPage.select("nav[class=woocommerce-pagination]").select("a[class=page-number]").size();
								if (pageCount > 0) {
									
									String strEnd = documentPage.select("nav[class=woocommerce-pagination]").select("a[class=page-number]").get(pageCount-1).text();
									if (!StringUtil.isBlank(strEnd)) { 
										strEnd=strEnd.replace(",","");
											pageEnd = Integer.parseInt(strEnd); 
									}
								} 

							}
							System.out.println( linkHref + "[pagination=" + String.valueOf(pageEnd) + "]");
//							for (int ii = page; ii <= pageEnd; ii++) {
							for (int ii = 126; ii <= pageEnd; ii++) {
								System.out.println("		 " + linkHref + "page/"
										+ String.valueOf(ii)+"/");
								Document document2;
								Document doc2 = Jsoup
										.connect(linkHref + "page/"
												+ String.valueOf(ii)+"/")
										.userAgent(
												"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(150000000) 
										.referrer("http://www.google.com").get();
								document2 = Jsoup.parse(doc2.toString());
								 Elements itemcategory = document2.select("p[class=name product-title]").select("a");
								
								if (itemcategory.size() <= 0)
									break;
							
								//Commond.saveFileTXT(linkHref + "page/"
										//+ String.valueOf(ii)+"/" + "\n", "txt", NameFileOut + "Submain");
								Document buyProduct;
								int x = 0;
								for (Element element2 : itemcategory) {
									linkdetail = element2.attr("href");
									x++;
						    		buyProduct = Jsoup.parse(new URL(linkdetail), 100000);
						    		String buyProductLink = buyProduct.select("form.cart").attr("action") ;
									if(!listObjLink.contains(buyProductLink) && !dataListLnk.contains(buyProductLink)){
						    			listObjLink.add(buyProductLink) ;
									}
									
//									if (linkdetail.contains("%"))
//										continue;
//						    		buyProduct = Jsoup.connect(linkdetail).userAgent(
//											"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
//									.referrer("http://www.google.com").get();
//						    		String buyProductLink = buyProduct.select("form.cart").attr("action") ;
//									if(!listObjLink.contains(buyProductLink) && !dataListLnk.contains(buyProductLink)){
//						    			listObjLink.add(buyProductLink) ;
//									}
								}
								// 5 page luu thanh 1 file
								int mod = ii % 5;
								if(mod == 0) {
									// Lưu mỗi link thành 1 file
									Commond.saveCSV(listObjLink, "LinksTeenavi_cat_sitemap_link" + i + "_page_" + ii);
									listObjLink = new ArrayList<String>();
									System.out.println("Save CSV :" + "LinksTeenavi_cat_sitemap_link" + i + "_page_" + ii);
								}
							}
							// Lưu mỗi link thành 1 file
//							Commond.saveCSV(listObjLink, "LinksTeenavi_cat-sitemap_" + i);
//							listObjLink = new ArrayList<String>();
//							System.out.println("Save CSV :" + "LinksTeenavi_cat-sitemap_" + i);
						}
						**/
						
						/*
						//https://teenavi.com/sitemap_index.xml
						String linksitemap="";
						link="https://teenavi.com/sitemap_index.xml";
						System.out.println("Find https://teenavi.com/sitemap_index.xml");
						content = Commond.getContentURL(link);
						str =content.toString();
						doc = Jsoup.parse(str, "", Parser.xmlParser());
						trm=new TemplateURLSitemap();
						for (int i=0;i< doc.select("loc").size();i++) {
							linksitemap=doc.select("loc").get(i).text();
							if(linksitemap.contains("/product-sitemap")){
								System.out.println(linksitemap);
								content = Commond.getContentURL(linksitemap);			 
								String strm =content.toString();
								docDetail = Jsoup.parse(strm, "", Parser.xmlParser());
								trm=new TemplateURLSitemap();
								Document buyProduct;
								for (int x=0;x< docDetail.select("loc").size();x++) {
									//System.out.println(doc.select("loc").get(x).text() );
									linkdetail =docDetail.select("loc").get(x).text() ;
									x++;
									if (linkdetail.contains("%")||!linkdetail.contains("/product/"))
										continue;
									
						    		buyProduct = Jsoup.connect(linkdetail).userAgent(
											"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
									.referrer("http://www.google.com").get();
						    		String buyProductLink = buyProduct.select("form.cart").attr("action") ;
									if(!listObjLink.contains(buyProductLink) && !dataListLnk.contains(buyProductLink)){
						    			listObjLink.add(buyProductLink) ;
									}
								}
							}
						}
					    **/
					}
//					Commond.saveCSV(listObjLink, "LinksTeenavi_sitemap_index");
					Commond.saveLinkTitleCSV(listLinkTitle, "LinksTeenavi" + fileName);
					System.out.println("Save CSV :" + "LinksTeenavi_sitemap_index_");
					//Commond.saveFile(listObjLink, "txt", "ListLinkDetail");
					labMessage.setText("Save list data successfully");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		
		btnCheckNewCach.setText("Check New cach 2 ");
		btnCheckNewCach.setBounds(83, 402, 204, 37);
		
				button.addSelectionListener(new SelectionAdapter() {
		
					@Override
					public void widgetSelected(SelectionEvent e) {
						listObjLink = new ArrayList<String>();
						String link = "https://teenavi.com";
						String linkdetail;
						String linkTee;
						String Title;
						int submain = 0;
						String NameFileOut = "";
						int page = 0;
						String linkSunTotle;
						String Category="";
						StringBuffer contentChild;
						try {
		
							UrlValidator urlValidator = new UrlValidator();
		
							System.setProperty("http.agent",
									"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
		
							StringBuffer content;
							if (StringUtil.isBlank(link)) {
		
								System.out.println("NO link");
							} else {
								if (!StringUtil.isBlank(sub.getText())) {
									submain = Integer.parseInt(sub.getText());
								}
								if (!StringUtil.isBlank(intpage.getText())) {
									page = Integer.parseInt(intpage.getText());
								}
								if (!StringUtil.isBlank(textNameFileOut.getText())) {
									NameFileOut = textNameFileOut.getText();
								}
		
								Document document;
								Document doc = Jsoup.connect(link)
										.userAgent(
												"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
										.referrer("http://www.google.com").get();
								document = Jsoup.parse(doc.toString());
		
								Elements subChild = document.select("div[class=nav header-nav header-bottom-nav nav-left  nav-size-medium nav-uppercase]").select("a") ;
								//System.out.println(subChild.toString());
								for (int u = submain; u < subChild.size(); u++) { 
									int pageEnd = 1;
									Element element = subChild.get(u);
									String linkHref = element.select("a").first().attr("href");
									Commond.saveFileTXT(linkHref + "\n", "txt", NameFileOut + "Submain");
									Document documentPage;
									Document docPage = Jsoup.connect(linkHref)
											.userAgent(
													"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
											.referrer("http://www.google.com").get();
									documentPage = Jsoup.parse(docPage.toString());
									if (documentPage.select("nav[class=woocommerce-pagination]").size() > 0) {
										int pageCount=documentPage.select("nav[class=woocommerce-pagination]").select("a[class=page-number]").size();
										if (pageCount > 0) {
											
											String strEnd = documentPage.select("nav[class=woocommerce-pagination]").select("a[class=page-number]").get(pageCount-1).text();
											if (!StringUtil.isBlank(strEnd)) { 
													pageEnd = Integer.parseInt(strEnd); 
											}
										} 
		
									}
									System.out.println( linkHref + "[pagination=" + String.valueOf(pageEnd) + "]");
									for (int i = page; i < pageEnd; i++) {
										System.out.println("		 " + linkHref + "page/"
												+ String.valueOf(i)+"/");
										Document document2;
										Document doc2 = Jsoup
												.connect(linkHref + "page/"
														+ String.valueOf(i)+"/")
												.userAgent(
														"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000)
												.referrer("http://www.google.com").get();
										document2 = Jsoup.parse(doc2.toString());
										 Elements itemcategory = document2.select("div[class=image-fade_in_back]").select("a");
										
										if (itemcategory.size() <= 0)
											break;
									
										Commond.saveFileTXT(linkHref + "page/"
												+ String.valueOf(i)+"/" + "\n", "txt", NameFileOut + "Submain");
										int x = 0;
										for (Element element2 : itemcategory) {
											linkdetail = element2.attr("href");
											x++;
											if (linkdetail.contains("%"))
												continue;
											if(!listObjLink.contains(linkdetail)){
												System.out.println("		         " + linkdetail);
												listObjLink.add(linkdetail) ;
											}
										}
									}
		
								}
		
							}
							Commond.saveCSV(listObjLink, "LinksTeenavi");
							//Commond.saveFile(listObjLink, "txt", "ListLinkDetail");
							labMessage.setText("Save list data successfully");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
		
				});

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(null);

			}
		});

		openBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
			}
		});

	}

	static <T> ArrayList<String> inspect(Class<T> klazz) {
		Field[] fields = klazz.getDeclaredFields();
		ArrayList<String> listReturn = new ArrayList<String>();
		System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			/*
			 * System.out.printf("%s %s %s%n",
			 * Modifier.toString(field.getModifiers()),
			 * field.getType().getSimpleName(),field.getName());
			 */
			listReturn.add(field.getName());

		}
		return listReturn;
	}

	/*
	 * private StringBuffer getContentURL(String urlLink) throws IOException {
	 * URL url; StringBuffer doc = new StringBuffer(); try { url = new
	 * URL(urlLink); // Get the input stream through URL Connection
	 * URLConnection con = url.openConnection(); InputStream is =
	 * con.getInputStream();
	 * 
	 * BufferedReader br = new BufferedReader(new InputStreamReader(is)); String
	 * line = "";
	 * 
	 * // read each line and write to System.out while ((line = br.readLine())
	 * != null) { doc.append(line); }
	 * 
	 * URL oracle = new URL(urlLink); URLConnection yc =
	 * oracle.openConnection(); BufferedReader in = new BufferedReader(new
	 * InputStreamReader( yc.getInputStream())); String inputLine; while
	 * ((inputLine = in.readLine()) != null) doc.append(inputLine); in.close();
	 * } catch (MalformedURLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return doc; }
	 */

	public ArrayList<Template> returnListTemplate(String linkdt, Elements divBtnGroup, String title,
			String selectCatagories, String style, String Price, String index) {
		Elements labelsColor = divBtnGroup.select("div[class=color]");
		ArrayList<Template> listObj = new ArrayList<Template>();
		/////////////////////////
		// Lấy màu đang ch�?n//////
		ArrayList<Template> listObjReturn = new ArrayList<Template>();
		String linkColor;
		String color = "";
		try {
			if (labelsColor.size() >= 1) {
				for (Element labelColor : labelsColor) {

					linkColor = labelColor.attr("data-reactid").toString();
					color = linkColor.split(":\\$")[1];
					if ("J Navy".equals(color))
						color = "Navy";
					System.out.println(color);
					if (arrayColor.contains(color)) {
						listObj = returnTemplate(linkdt, color, title, selectCatagories, style, Price, index);
						listObjReturn.addAll(listObj);
					}
				}
			}
			labelsColor = divBtnGroup.select("div[class=picked color]");
			if (labelsColor.size() >= 1) {
				for (Element labelColor : labelsColor) {

					linkColor = labelColor.attr("data-reactid").toString();
					color = linkColor.split(":\\$")[1];
					if (arrayColor.contains(color)) {
						listObj = returnTemplate(linkdt, color, title, selectCatagories, style, Price, index);
						listObjReturn.addAll(listObj);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listObjReturn;
	}

	public ArrayList<Template> returnTemplate(String link, String color, String title, String selectCatagories,
			String style, String Price, String index) {
		log.debug("Ra Ham returnTemplate");
		ArrayList<Template> listObj = new ArrayList<Template>();

		try {
			String[] styleAndPrices;
			String contentColor = "";
			String newLinkGetImg = "";
			String styleAndPricesSelect;
			StringBuffer contentChild;
			Elements divTitles = null;
			String linkColor;
			Float price = (float) 0;
			String[] linkArray = link.split("-");
			if (linkArray.length > 0) {
				for (int g = 0; g < linkArray.length - 1; g++) {
					newLinkGetImg += linkArray[g] + "-";
				}
			}
			contentColor = listColor.get(color);
			newLinkGetImg = newLinkGetImg + contentColor;
			contentChild = getContentURL(newLinkGetImg);

			// Nội dung trang chi tiết
			Document documentChild = Jsoup.parse(contentChild.toString());

			String sku = "";

			// Lấy hình ảnh Chính
			String linkImg = documentChild.select("div[class=CampaignProductImageShowcase]").select("img").attr("src");
			// Lay toan bo sixe
			Elements divArraySize = documentChild.select("select option[data-se]");

			for (String size : arraySize) {

				Template objTemplate = new Template();
				// Set titel for data Excel
				objTemplate.setTitle(title);
				if (color != null && color != "") {
					objTemplate.setOption2Value(color);
					// objTemplate.setHandle(title + "-" + style+ "-"
					// +color+index);
				} else {
					objTemplate.setOption2Value("-");
					if (!StringUtil.isBlank(index)) {
						index = "-" + index;
					}
					// objTemplate.setHandle(title + "-" + style+index);
				}
				objTemplate.setHandle(title);
				objTemplate.setImageAltText(title);
				objTemplate.setImagesrc(linkImg);
				objTemplate.setVariantImage(linkImg);
				objTemplate.setVariantSKU(sku);
				objTemplate.setOption3Value(style);
				objTemplate.setSEODescription(title);
				objTemplate.setSEOTitle(title);
				objTemplate.setBodyHTML(title);
				objTemplate.setTags(selectCatagories);
				objTemplate.setSEODescription(title);
				String url = link;
				objTemplate.setSourceURL(url);
				price = Float.valueOf(Price);
				if (size.equals("XXL") || size.equals("XXXL") || size.equals("XXXXL") || size.equals("XXXXXL")) {
					objTemplate.setVariantprice(String.valueOf(price + 3));
					if (size.equals("XXL"))
						objTemplate.setOption1Value("XXL");
					if (size.equals("XXXL"))
						objTemplate.setOption1Value("XXXL");
					if (size.equals("XXXXL"))
						objTemplate.setOption1Value("XXXXL");
					if (size.equals("XXXXXL"))
						objTemplate.setOption1Value("XXXXXL");
				} else {
					objTemplate.setVariantprice(String.valueOf(price));
					objTemplate.setOption1Value(size);
				}
				objTemplate.setVariantCompareAtPrice(String.valueOf(price + 10));
				listObj.add(objTemplate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		log.debug("Ra Ham returnTemplate");
		return listObj;
	}

	public ArrayList<Template> contentChild(String linkHref, String selectCatagories) {
		ArrayList<Template> listObj = new ArrayList<Template>();
		String[] arrayLink = new String[] {};
		if (linkHref != "") {
			try {
				log.debug("Vao trang contentChild");
				// Trang con sau khi link qua

				log.debug("Trang con sau khi link qua" + ":" + linkHref);
				String[] styleAndPrices;
				String styleAndPricesSelect;
				String linkStyle;
				String linkColor;
				StringBuffer contentChild;
				Float price = (float) 0;
				String color = "";
				String style = "";
				String title = "";
				String titleChild = "";
				String sku = "";
				boolean getMore = false;
				String[] styleAndPrice = null;
				String[] arrLink = linkHref.split("___");
				if (arrLink.length >= 2) {
					linkHref = arrLink[0];
					if (!"XXX".equals(arrLink[0]))
						title = arrLink[1];

				}
				contentChild = getContentURL(linkHref);
				/*
				 * Document documentChild = Jsoup.connect(linkHref)
				 * .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0"
				 * ) .referrer("http://www.google.com").get();
				 */

				// Nội dung trang chi tiết
				// Ná»™i dung trang chi tiáº¿t
				Document documentChild = Jsoup.parse(contentChild.toString());
				// log.debug(documentChild.toString());
				Elements TitleChild = documentChild.select("title");
				titleChild = TitleChild.text();
				// lay Descrition

				// Lấy vòng lập cho kiểu dáng
				Elements selectedsStyle = documentChild.select("select[class=ui selection dropdown fluid]").eq(0)
						.select("option");
				;

				System.out.println(selectedsStyle.toString());
				// Vòng lặp cho Style để ch�?n 1 mẫu mới
				for (Element selectedStyle : selectedsStyle) {

					styleAndPrice = selectedStyle.text().split("- \\$");
					if (styleAndPrice.length >= 2) {
						String arrayPrice = styleAndPrice[styleAndPrice.length - 1];
						price = Float.valueOf(arrayPrice);

						/////////////////////////
						// Lấy Style đang ch�?n//////
						style = styleAndPrice[0];
						style = trimspace(style);
						if (!arrayStyle.contains(style))
							continue;
					}
					// Lấy giá trị của style để link tới
					// trang khác
					if (!title.isEmpty())
						titleChild = title;
					linkStyle = selectedStyle.attr("value");

					//// log.debug("Lấy giá trị của style để
					//// link tới trang khác" + linkStyle);

					String linkdt = linkHref + "?retailProductSlug=" + linkStyle;
					contentChild = getContentURL(linkdt);
					System.out.println(linkdt + "********" + style);
					/*
					 * Document documentChild = Jsoup.connect(linkHref)
					 * .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0"
					 * ) .referrer("http://www.google.com").get();
					 */

					// Nội dung trang chi tiết
					// Ná»™i dung trang chi tiáº¿t
					documentChild = Jsoup.parse(contentChild.toString());
					// Lấy nhóm màu sắc để ch�?n màu
					Elements divBtnGroup = documentChild.select("div[class=ColorPicker interactive bordered]");
					// Nếu có màu sắc, thì duyệt các ô màu
					// để lấy ra trang chi tiết
					if (divBtnGroup.size() > 0) {
						ArrayList<Template> returnListTemplate = returnListTemplate(linkdt, divBtnGroup, titleChild,
								selectCatagories, style, String.valueOf(price), "");
						System.out.println("Tong truoc :" + listObj.size());
						for (Template element : returnListTemplate) {

							listObj.add(element);
						}
						System.out.println("Tong :" + listObj.size());
					} else {// Nếu không có màu sắc, thì
							// duyệt lấy ra thông tin trang
							// chi tiết luôn
						// combobox
						Element proSKU = documentChild.select("input[name=mockupID]").first();
						if (proSKU != null)
							sku = proSKU.attr("value");
						// Lấy list style và giá trong
						// combobox
						selectedStyle = documentChild.select("select option[selected]").first();
						//// log.debug("selectedStyle" +
						//// selectedStyle);

						// log.debug(selectedStyle.text());
						styleAndPrice = selectedStyle.text().split("- \\$");
						/////////////////////////
						// Lấy giá và Style đang ch�?n//////
						if (styleAndPrice.length >= 2) {
							String arrayPrice = styleAndPrice[styleAndPrice.length - 1];
							price = Float.valueOf(arrayPrice);
							/////////////////////////
							// Lấy Style đang ch�?n//////
							style = styleAndPrice[0];
							style = trimspace(style);
							if (!arrayStyle.contains(style))
								continue;
						}
						// String style=styleAndPrice[0]

						// Lấy hình ảnh Chính
						String linkImg = documentChild.select("div[class=product-zoom-image]").select("img")
								.attr("data-zoom");
						// log.debug(linkImg.attr("src"));

						for (String size : arraySize) {
							Template objTemplate = new Template();
							// Set titel for data Excel
							objTemplate.setTitle(titleChild);
							objTemplate.setImagesrc(linkImg);
							objTemplate.setVariantImage(linkImg);
							objTemplate.setOption1Value(size);

							objTemplate.setHandle(titleChild);
							objTemplate.setBodyHTML(titleChild);
							objTemplate.setOption3Value(style);
							objTemplate.setVariantSKU(sku);
							objTemplate.setImageAltText(titleChild);
							objTemplate.setSEODescription(titleChild);
							objTemplate.setSEOTitle(titleChild);
							objTemplate.setSEODescription(titleChild);
							if (size.equals("XXL") || size.equals("XXXL") || size.equals("XXXXL")
									|| size.equals("XXXXXL")) {
								objTemplate.setVariantprice(String.valueOf(price + 3));
								if (size.equals("XXL"))
									objTemplate.setOption1Value("XXL");
								if (size.equals("XXXL"))
									objTemplate.setOption1Value("XXXL");
								if (size.equals("XXXXL"))
									objTemplate.setOption1Value("XXXXL");
								if (size.equals("XXXXXL"))
									objTemplate.setOption1Value("XXXXXL");
							} else {
								objTemplate.setVariantprice(String.valueOf(price));
							}
							listObj.add(objTemplate);
						}
					}
				}
				log.debug("Ra trang contentChild");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return listObj;
	}

	public String trimspace(String str) {
		str = str.replaceAll("\\s+", " ");
		str = str.replaceAll("(^\\s+|\\s+$)", "");
		return str;
	}

	static StringBuffer getContentURL2(String urlLink) throws IOException {
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

	private StringBuffer getContentURL(String urlLink) throws IOException {
		URL url;
		StringBuffer doc = new StringBuffer();
		try {
			url = new URL(urlLink);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			// Get the input stream through URL Connection
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
}
