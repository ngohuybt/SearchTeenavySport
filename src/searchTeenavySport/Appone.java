package searchTeenavySport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
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
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature; 

public class Appone extends Exception {

	protected Shell shlExportExcel;
	final JFrame jFrame = new JFrame("Simple hello Example");
	private String[] arraySize = { "S", "M", "X", "XL", "2X", "3X" };
	String mainLink = "";
	String csvFile = "";
	String hostName = "https://www.sunfrog.com/";
	String hostSearchName = "https://www.sunfrog.com/search/";
	private static final String FILE_NAME = "/tmp/MyFirstExcel.xlsx";
	private static final Log log = LogFactory.getLog(Appone.class);
	private Text txtLink;
	int[] styles = { SWT.SINGLE, SWT.MULTI };
	Display display = new Display();
	List<String> dataListView = new ArrayList<String>();
	String nameFileOutPut = "";
	ImageServices imageServices;
	private Text textNameFileOut;

	/**
	 * Launch the application. Sử dụng thread + Hàm con
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Appone window = new Appone();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		shlExportExcel.setSize(561, 514);
		shlExportExcel.setText("Export Excel Sunfrog");
		JFileChooser fileChooser = new JFileChooser();
		JButton saveBtn = new JButton("Save");
		JButton openBtn = new JButton("Open");

		FocusListener listener;
		/*
		 * comboCatogeries.addFocusListener(new ItemListener() { public void
		 * itemStateChanged(ItemEvent arg0) { //Do Something } });
		 */

		Group group_1 = new Group(shlExportExcel, SWT.NONE);
		group_1.setBounds(10, 10, 525, 372);
		Label labMessage = new Label(group_1, SWT.NONE);
		labMessage.setAlignment(SWT.RIGHT);
		labMessage.setBounds(269, 27, 242, 36);
		labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));

		Label lblSearchLink_1 = new Label(group_1, SWT.NONE);
		lblSearchLink_1.setBounds(96, 69, 213, 26);
		lblSearchLink_1.setText("Search Link");

		CLabel lblSearchLink = new CLabel(group_1, SWT.NONE);
		lblSearchLink.setAlignment(SWT.RIGHT);
		lblSearchLink.setBounds(34, 100, 53, 26);
		lblSearchLink.setText("Link");

		txtLink = new Text(group_1, SWT.BORDER);
		txtLink.setBounds(96, 101, 419, 25);

		Button btnAdd = new Button(group_1, SWT.NONE);

		btnAdd.setBounds(247, 132, 74, 30);
		btnAdd.setText("Add");

		Button btnSave = new Button(group_1, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 Commond.saveFile(dataListView, "CSV", "config") ;
				labMessage.setText("Save list data successfully");
			}
		});
		btnSave.setBounds(441, 132, 74, 30);
		btnSave.setText("Save");
		Button btnExport = new Button(shlExportExcel, SWT.NONE);
		btnExport.setText("Export ");
		btnExport.setBounds(331, 424, 204, 37);
		Button btnNewButton_2 = new Button(group_1, SWT.NONE);
		btnNewButton_2.setBounds(425, 315, 90, 30);
		btnNewButton_2.setText("Open File");
		ListViewer listViewer = new ListViewer(group_1, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List listDataView = listViewer.getList();
		listDataView.setBounds(10, 168, 505, 141);

		Label lblDirFile = new Label(group_1, SWT.NONE);
		lblDirFile.setBounds(10, 320, 409, 26);

		Button rdoLink = new Button(group_1, SWT.RADIO);
		rdoLink.setBounds(67, 67, 23, 25);

		Label lbStatus = new Label(group_1, SWT.NONE);
		lbStatus.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lbStatus.setBounds(10, 23, 253, 40);

		Button btnDelete = new Button(group_1, SWT.NONE);

		btnDelete.setText("Delete");
		btnDelete.setBounds(345, 132, 74, 30);

		textNameFileOut = new Text(shlExportExcel, SWT.BORDER);
		textNameFileOut.setBounds(128, 394, 193, 25);

		CLabel label = new CLabel(shlExportExcel, SWT.NONE);
		label.setText("Name Out Put");
		label.setBounds(10, 394, 110, 26);

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

		listViewer.setContentProvider(new IStructuredContentProvider() {

			public Object[] getElements(Object inputElement) {
				return ((List) inputElement).toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
		});

		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JFileChooser openFile = new JFileChooser();
				int returnVal = openFile.showOpenDialog(jFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getCurrentDirectory();
					java.io.File fileDir = openFile.getSelectedFile();
					lblDirFile.setText("File Selected :" + fileDir);
					csvFile = fileDir.getPath();
					// String extent=file.get
					try {
						FileReader fileReader = new FileReader(csvFile);
						BufferedReader br = new BufferedReader(fileReader);
						String stringRead = br.readLine();
						int i = 0;

						while (stringRead != null) {
							boolean flag = true;
							StringTokenizer st = new StringTokenizer(stringRead, ",");
							String docNumber = st.nextToken();
							for (String string : dataListView) {
								if (docNumber.equals(string)) {
									flag = false;
								}
							}
							if (flag) {
								dataListView.add(docNumber);
								listViewer.add(docNumber);
							}

							// read the next line
							stringRead = br.readLine();
						}
						br.close();
					} catch (IOException ex) {
						lbStatus.setText("Problem accessing file" + file.getAbsolutePath());
					}
				} else {
					// System.out.println("File access cancelled by user.");
				}
			}
		});

		// Add String to List
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String link = txtLink.getText();
				if (link != "" && link != null) {
					boolean flag = true;
					List<String> list = new ArrayList<>();
					for (String string : dataListView) {
						if (link.equals(string)) {
							lbStatus.setText("String is exist");
							flag = false;
						}
					}
					if (flag) {
						listViewer.add(link);
						dataListView.add(link);
						lbStatus.setText("");
					}
					 txtLink.setText("");
				} else {
					lbStatus.setText("String is not empty");
				}

			}
		});
		// Delete String to List
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
				String language = (String) selection.getFirstElement();
				if (language != "" && language != null) {
					listViewer.remove(language);
					listViewer.setSelection(selection, true);
					;
					for (int i = 0; i < dataListView.size(); i++) {
						if (language.equals(dataListView.get(i))) {
							dataListView.remove(i);
						}
					}
					lbStatus.setText("");
				} else
					lbStatus.setText("Please select item on listview");
			}
		});

		btnExport.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				long begin = Calendar.getInstance().getTimeInMillis();
				File outputFile;
				BufferedReader reader;
				FileWriter fileWriter;
				CountDownLatch latch = new CountDownLatch(0);
				ArrayList<Template> listObj = new ArrayList<Template>();
				StringBuffer content;
				// Create list propertise from Template to check column of
				// tempalte excel
				ArrayList<String> listHeader = inspect(Template.class);
				int i = 0;
				// get response from URL to the web page
				try {
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					labMessage.setText("Running...........");

					if (rdoLink.getSelection()) {
						ArrayList<Template> listObjs = new ArrayList<Template>();
						ArrayList<Template> listObjLink = new ArrayList<Template>();
						if (dataListView.size() <= 0) {
							labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							labMessage.setText("List view is not null, add link to list view");
							return;
						}
						for (String link : dataListView) {
							listObjLink = contentChild(link, "");
							for (Template template : listObjLink) {
								listObjs.add(template);
							}
						}
						nameFileOutPut = textNameFileOut.getText();
						if (StringUtil.isBlank(nameFileOutPut)) {
							labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							labMessage.setText("nameFileOutPut is not null");
							return;
						}
						if (StringUtil.isBlank(nameFileOutPut)) {
							labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							labMessage.setText("Tag is not null");
							return;
						}

						Collections.sort(listObjs, new Template.CompTitle());
						long end = Calendar.getInstance().getTimeInMillis();
						log.debug("Executed Time: " + (end - begin));
						// Export File
						 List<Template2> lsttemplate2=Commond.convertObj(listObjs);
						 ObjectMapper objectMapper = new ObjectMapper();
					    	//Set pretty printing of json
					    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
					    	String arrayToJson = objectMapper.writeValueAsString(lsttemplate2);
					    	 
					    	System.out.println("1. Convert List of person objects to JSON :");
					    	System.out.println(arrayToJson);
					    	Commond.saveFileString(arrayToJson, "txt", nameFileOutPut,"");
						labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
						labMessage.setText("Export   successfully");
						return;
					}
				} catch (Exception ex) {
					log.error(ex);
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					labMessage.setText("Have error in process, check file log");
					Commond.saveFileString(ex.toString(), "txt", "log","log");
				}
			}
		});

	}

	static <T> ArrayList<String> inspect(Class<T> klazz) {
		Field[] fields = klazz.getDeclaredFields();
		ArrayList<String> listReturn = new ArrayList<String>();
		// System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			/*
			 * //System.out.printf("%s %s %s%n",
			 * Modifier.toString(field.getModifiers()),
			 * field.getType().getSimpleName(),field.getName());
			 */
			listReturn.add(field.getName());

		}
		return listReturn;
	}

	private StringBuffer getContentURL(String urlLink) throws IOException {
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

	// Tag dung cho tag, link dung them sour url trong tempalte2
	public ArrayList<Template> returnListTemplate(Elements divBtnGroup, String title, String selectCatagories,
			String index,String tag,String link) throws IOException {
		Elements labelsColor = divBtnGroup.select("label");
		ArrayList<Template> listObj = new ArrayList<Template>();
		/////////////////////////
		// Lấy màu đang ch�?n//////

		try {
			if (labelsColor.size() >= 1) {
				for (Element labelColor : labelsColor) {

					listObj = returnTemplate(labelColor, title, selectCatagories, index,tag,link);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listObj;
	}

	public ArrayList<Template> returnTemplate(Element labelColor, String title, String selectCatagories, String index,String tag,String link)
			throws IOException {
		// log.debug("Ra Ham returnTemplate");
		ArrayList<Template> listObj = new ArrayList<Template>();

		try {
			String[] styleAndPrices;
			String styleAndPricesSelect;
			StringBuffer contentChild;
			Elements divTitles = null;
			String linkColor;
			int price = 0;
			String color = "";
			String style = "";
			Element selectedStyle;
			linkColor = labelColor.attr("onClick").split("=")[1];
			color = labelColor.attr("title");
			//// System.out.println("https://www.sunfrog.com/" +
			//// linkColor.replace("'", "").replace(";", ""));
			contentChild = getContentURL("https://www.sunfrog.com/" + linkColor.replace("'", "").replace(";", ""));
			Document documentChild = new Document("");
			documentChild = Jsoup.parse(contentChild.toString());
			// Lấy style và giá
			selectedStyle = documentChild.select("select option[selected]").first();
			// log.debug(selectedStyle.text());
			String[] styleAndPrice = selectedStyle.text().split("\\$");
			/////////////////////////
			// Lấy giá và Style đang ch�?n//////
			if (styleAndPrice.length >= 2) {
				String[] arrayPrice = styleAndPrice[styleAndPrice.length - 1].split("\\.");
				if (arrayPrice.length >= 2) {
					/////////////////////////
					// Lấy Price đang ch�?n//////
					price = Integer.parseInt(arrayPrice[0]);
				}
				/////////////////////////
				// Lấy Style đang ch�?n//////
				style = styleAndPrice[0];
			}
			Element proSKU = documentChild.select("input[name=mockupID]").first();
			String sku = proSKU.attr("value");

			// Lấy hình ảnh Chính
			Element linkImg = documentChild.select("img[id=MainImgShow]").first();
			// log.debug(linkImg.attr("src"));
			for (String size : arraySize) {
				Template objTemplate = new Template();
				// Set titel for data Excel
				objTemplate.setTitle(title);
				if (color != null && color != "") {
					objTemplate.setOption2Value(color);
					objTemplate.setHandle(title + "-" + style + "-" + color + index);
				} else {
					objTemplate.setOption2Value("Black");
					if (!StringUtil.isBlank(index)) {
						index = "-" + index;
					}
					objTemplate.setHandle(title + "-" + style + index);
				}
				objTemplate.setImageAltText(title);
				objTemplate.setImagesrc("https://" + linkImg.attr("src").replace("//", ""));
				objTemplate.setVariantImage("https://" + linkImg.attr("src").replace("//", ""));
				objTemplate.setVariantSKU(sku);
				objTemplate.setOption3Value(style);
				objTemplate.setSEODescription(title);
				objTemplate.setSEOTitle(title);
				objTemplate.setBodyHTML(title);
				objTemplate.setTags(selectCatagories);
				objTemplate.setSEODescription(title);
				objTemplate.setTags(tag);
				objTemplate.setSourceURL(link);
				if (size.equals("2X") || size.equals("3X")) {
					objTemplate.setVariantprice(String.valueOf(price + 3));
					if (size.equals("2X"))
						objTemplate.setOption1Value("XXL");
					if (size.equals("3X"))
						objTemplate.setOption1Value("XXXL");
				} else {
					objTemplate.setVariantprice(String.valueOf(price));
					objTemplate.setOption1Value(size);
				}
				listObj.add(objTemplate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// log.debug("Ra Ham returnTemplate");
		return listObj;
	}

	public ArrayList<Template> contentChild(String linkHref, String selectCatagories) {
		ArrayList<Template> listObj = new ArrayList<Template>();
		if (linkHref != "") {
			try {
				log.debug("Vao trang contentChild");
				// Trang con sau khi link qua

				//// log.debug("Trang con sau khi link qua"
				//// + ":" + linkHref);
				String[] styleAndPrices;
				String styleAndPricesSelect;
				String linkStyle;
				String linkColor;
				StringBuffer contentChild;
				int price = 0;
				String color = "";
				String style = "";
				String title = "";
				String titleChild = "";
				if (linkHref.indexOf("https") == 0) {
					contentChild = getContentURL(linkHref);
				} else {
					contentChild = getContentURL(hostName + linkHref);
				}

				// Nội dung trang chi tiết
				Document documentChild = Jsoup.parse(contentChild.toString());
				// log.debug(documentChild.toString());
				Elements TitleChild = documentChild.select("h1[id=srshow]");
				titleChild = TitleChild.get(0).text().replaceAll(",", "/");
				Elements titletopwrap = documentChild.select("div[class=titletopwrap]");
				String tag = titletopwrap.select("li").get(1).text();
				// lay Descrition
				//titletopwrap
				// lay Descrition

				// Lấy vòng lập cho kiểu dáng
				Elements selectedsStyle = documentChild.select("select[id=shirtTypes]").select("option");
				// Vòng lặp cho Style để ch�?n 1 mẫu mới
				for (Element selectedStyle : selectedsStyle) {
					// Lấy giá trị của style để link tới
					// trang khác
					linkStyle = selectedStyle.attr("value");

					//// log.debug("Lấy giá trị của style để
					//// link tới trang khác" + linkStyle);

					contentChild = getContentURL("https://www.sunfrog.com/" + linkStyle);
					documentChild = new Document("");
					documentChild = Jsoup.parse(contentChild.toString());
					// Lấy nhóm màu sắc để ch�?n màu
					Elements divBtnGroup = documentChild.select("div.btn-group");
					// Nếu có màu sắc, thì duyệt các ô màu
					// để lấy ra trang chi tiết
					if (divBtnGroup.size() > 0) {
						ArrayList<Template> returnListTemplate = returnListTemplate(divBtnGroup, titleChild,
								selectCatagories, "",tag,linkHref);
						for (Template element : returnListTemplate) {
							listObj.add(element);
						}
					} else {// Nếu không có màu sắc, thì
							// duyệt lấy ra thông tin trang
							// chi tiết luôn
						// combobox
						Element proSKU = documentChild.select("input[name=mockupID]").first();
						String sku = proSKU.attr("value");
						// Lấy list style và giá trong
						// combobox
						selectedStyle = documentChild.select("select option[selected]").first();
						//// log.debug("selectedStyle" +
						//// selectedStyle);

						// log.debug(selectedStyle.text());
						String[] styleAndPrice = selectedStyle.text().split("\\$");
						/////////////////////////
						// Lấy giá và Style đang ch�?n//////
						if (styleAndPrice.length >= 2) {
							String[] arrayPrice = styleAndPrice[styleAndPrice.length - 1].split("\\.");
							if (arrayPrice.length >= 2) {
								/////////////////////////
								// Lấy Price đang ch�?n//////
								price = Integer.parseInt(arrayPrice[0]);
							}
							/////////////////////////
							// Lấy Style đang ch�?n//////
							style = styleAndPrice[0];
						}
						// String style=styleAndPrice[0]

						// Lấy hình ảnh Chính
						Element linkImg = documentChild.select("img[id=MainImgShow]").first();
						// log.debug(linkImg.attr("src"));
						for (String size : arraySize) {
							Template objTemplate = new Template();
							// Set titel for data Excel
							objTemplate.setTitle(titleChild);
							objTemplate.setImagesrc("https://" + linkImg.attr("src").replace("//", ""));
							objTemplate.setVariantImage("https://" + linkImg.attr("src").replace("//", ""));
							objTemplate.setOption1Value(size);
							if (color != null && color != "") {
								objTemplate.setOption2Value(color);
								objTemplate.setHandle(titleChild + "-" + style + "-" + color);
							} else {
								objTemplate.setOption2Value("Black");
								objTemplate.setHandle(titleChild + "-" + style);
							}
							objTemplate.setBodyHTML(titleChild);
							objTemplate.setOption3Value(style);
							objTemplate.setVariantSKU(sku);
							objTemplate.setImageAltText(titleChild);
							objTemplate.setSEODescription(titleChild);
							objTemplate.setSEOTitle(titleChild);
							objTemplate.setSEODescription(titleChild);
							objTemplate.setSourceURL(linkHref);
							objTemplate.setTags(tag);
							if (size.equals("2X") || size.equals("3X")) {
								objTemplate.setVariantprice(String.valueOf(price + 3));
								if (size.equals("2X"))
									objTemplate.setOption1Value("XXL");
								if (size.equals("3X"))
									objTemplate.setOption1Value("XXXL");
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
}
