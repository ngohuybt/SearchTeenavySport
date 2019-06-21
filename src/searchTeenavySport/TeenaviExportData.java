package searchTeenavySport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.eclipse.swt.graphics.Point;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import moteefeObj.Campaign_mockups;
import moteefeObj.Campaign_products;
import moteefeObj.Colors;
import moteefeObj.MyPojo;
import moteefeObj.Products;
import moteefeObj.RootObject;
import moteefeObj.TemplatePestra;

public class TeenaviExportData {

	protected Shell shlExportExcel;
	final JFrame jFrame = new JFrame("Simple hello Example");
	private List<String> arraySize = new ArrayList<String>();
	private List<String> arrayStyle = new ArrayList<String>();
	private HashMap<String, String> hashMapMoteefeeStyle = new HashMap<String, String>();
	private HashMap<String, String> hashMapMoteefeePrice= new HashMap<String, String>();

	private HashMap<String, String> hashMapMoteefeeColor = new HashMap<String, String>();
	private HashMap<String, String> hashMapMoteefeeColorHoddie = new HashMap<String, String>();
	ArrayList<TemplatePestra> listTemplatePestra = new ArrayList<TemplatePestra>();
	private List<String> arrayColor = new ArrayList<String>();
	private String csvFile = "";
	private String nameFileOutPut = "";
	private String sellerID = "";
	private String nameStore = "";
	private String hostName = "";
	private static final Log log = LogFactory.getLog(TeenaviExportData.class);
	private Text txtLink;
	int[] styles = { SWT.SINGLE, SWT.MULTI };
	private Display display = new Display();
	private List<String> dataListView = new ArrayList<String>();
	private Text textNameFileOut;
	private Text txtTitleReplace;
	private Text txtStoreName;
	HashMap<String, String> hashStyleMap = new HashMap<String, String>();
	HashMap<String, String> hashColumn1 = new HashMap<String, String>();
	private Text txtTshirt;
	private Text txtHoddies;
	private Text txtSweatshirt;
	private Text txtQuantity;
	private Text txtCategory;
	private Button ckbGreen;
	ArrayList<String> arrayStringShirt = new ArrayList<String>();
	private Text txtTshirtPrice;
	private Text txtWomenTshirtPrice;
	private Text txtSweatshirtPrice;
	private Text txtHoddiesPrice;

	/**
	 * Launch the application. Sử dụng thread + Hàm con
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TeenaviExportData window = new TeenaviExportData();
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
		shlExportExcel.setMinimumSize(new Point(20, 47));
		shlExportExcel.setSize(798, 739);
		shlExportExcel.setText("Export Moteefe ");
		JFileChooser fileChooser = new JFileChooser();
		JButton saveBtn = new JButton("Save");
		JButton openBtn = new JButton("Open");

		FocusListener listener;
		/*
		 * comboCatogeries.addFocusListener(new ItemListener() { public void
		 * itemStateChanged(ItemEvent arg0) { //Do Something } });
		 */

		Group group_1 = new Group(shlExportExcel, SWT.NONE);
		group_1.setBounds(0, 0, 775, 682);
		Label labMessage = new Label(group_1, SWT.NONE);
		labMessage.setAlignment(SWT.RIGHT);
		labMessage.setBounds(523, 23, 242, 20);
		labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));

		CLabel lblSearchLink = new CLabel(group_1, SWT.NONE);
		lblSearchLink.setAlignment(SWT.RIGHT);
		lblSearchLink.setBounds(105, 49, 53, 26);
		lblSearchLink.setText("Link");

		txtLink = new Text(group_1, SWT.BORDER);
		txtLink.setBounds(176, 49, 267, 26);

		Button btnAdd = new Button(group_1, SWT.NONE);
		btnAdd.setImage(SWTResourceManager.getImage(TeenaviExportData.class, "/png/add.png"));

		btnAdd.setBounds(33, 308, 53, 26);

		Button btnSave = new Button(group_1, SWT.NONE);
		btnSave.setImage(SWTResourceManager.getImage(TeenaviExportData.class, "/png/003-save.png"));
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileWriter writer;
				Date date = new Date();
				try {
					String csvFile = "Config" + date.getDay() + "_" + date.getMonth() + "_" + date.getYear() + "_"
							+ date.getHours() + "_" + +date.getMinutes() + ".txt";
					writer = new FileWriter(csvFile);
					String[] arrayLink = {};
					String link1 = "";
					String title1 = "";
					for (int i = 0; i < dataListView.size(); i++) {
						arrayLink = dataListView.get(i).split("___");
						if (arrayLink.length >= 2) {
							link1 = arrayLink[0];
							title1 = arrayLink[1];
						} else {

							title1 = "";
							link1 = dataListView.get(i);
						}
						List<String> list = new ArrayList<>();
						list.add(link1);
						if (!title1.isEmpty())
							list.add(title1);

						CSVUtils.writeLine(writer, list, ',', ' ');
					}
					writer.flush();
					writer.close();
					labMessage.setText("Save list data successfully");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSave.setBounds(151, 308, 53, 26);
		Button btnNewButton_2 = new Button(group_1, SWT.NONE);
		btnNewButton_2.setImage(SWTResourceManager.getImage(TeenaviExportData.class, "/png/001-folder.png"));
		btnNewButton_2.setBounds(212, 308, 53, 26);
		ListViewer listViewer = new ListViewer(group_1, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List listDataView = listViewer.getList();
		listDataView.setBounds(34, 340, 708, 141);

		Label lblDirFile = new Label(group_1, SWT.NONE);
		lblDirFile.setBounds(34, 494, 706, 20);

		Label label = new Label(group_1, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 520, 756, 2);

		Label lbStatus = new Label(group_1, SWT.NONE);
		lbStatus.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lbStatus.setBounds(54, 23, 396, 20);

		Button btnDelete = new Button(group_1, SWT.NONE);
		btnDelete.setImage(SWTResourceManager.getImage(TeenaviExportData.class, "/png/005-minus.png"));
		btnDelete.setBounds(92, 308, 53, 26);

		textNameFileOut = new Text(group_1, SWT.BORDER);
		textNameFileOut.setText("MoteefeToWP");
		textNameFileOut.setBounds(632, 49, 111, 26);

		CLabel label_1 = new CLabel(group_1, SWT.NONE);
		label_1.setText("Name File Export");
		label_1.setBounds(470, 49, 123, 26);

		CLabel label_2 = new CLabel(group_1, SWT.NONE);
		label_2.setText("Title repalce");
		label_2.setBounds(68, 83, 90, 26);

		txtTitleReplace = new Text(group_1, SWT.BORDER);
		txtTitleReplace.setBounds(176, 81, 267, 26);

		Button ckbSizeS = new Button(group_1, SWT.CHECK);
		ckbSizeS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbSizeS.getSelection()) {
					arraySize.add(ckbSizeS.getText());

				} else {
					arraySize.remove(ckbSizeS.getText());
				}
			}
		});
		ckbSizeS.setText("S");
		ckbSizeS.setSelection(true);
		arraySize.add(ckbSizeS.getText());

		ckbSizeS.setBounds(213, 554, 111, 20);

		Button ckbSizeM = new Button(group_1, SWT.CHECK);
		ckbSizeM.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbSizeM.getSelection()) {
					arraySize.add(ckbSizeM.getText());

				} else {
					arraySize.remove(ckbSizeM.getText());
				}
			}
		});
		ckbSizeM.setText("M");
		ckbSizeM.setSelection(true);
		arraySize.add(ckbSizeM.getText());

		ckbSizeM.setBounds(213, 580, 111, 20);

		Button ckbSizeL = new Button(group_1, SWT.CHECK);
		ckbSizeL.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbSizeL.getSelection()) {
					arraySize.add(ckbSizeL.getText());

				} else {
					arraySize.remove(ckbSizeL.getText());
				}
			}
		});
		ckbSizeL.setText("L");
		ckbSizeL.setSelection(true);
		arraySize.add(ckbSizeL.getText());

		ckbSizeL.setBounds(213, 606, 111, 20);

		Button ckbSizeXl = new Button(group_1, SWT.CHECK);
		ckbSizeXl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbSizeXl.getSelection()) {
					arraySize.add(ckbSizeXl.getText());

				} else {
					arraySize.remove(ckbSizeXl.getText());
				}
			}
		});
		ckbSizeXl.setText("XL");
		ckbSizeXl.setSelection(true);
		arraySize.add(ckbSizeXl.getText());

		ckbSizeXl.setBounds(213, 632, 111, 20);

		Button ckbSizeXxl = new Button(group_1, SWT.CHECK);
		ckbSizeXxl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbSizeXxl.getSelection()) {
					arraySize.add(ckbSizeXxl.getText());

				} else {
					arraySize.remove(ckbSizeXxl.getText());
				}
			}
		});
		ckbSizeXxl.setText("XXL");
		ckbSizeXxl.setSelection(true);
		arraySize.add(ckbSizeXxl.getText());

		ckbSizeXxl.setBounds(367, 554, 111, 20);

		Button ckbSizeXxxl = new Button(group_1, SWT.CHECK);
		ckbSizeXxxl.setSelection(true);
		ckbSizeXxxl.setText("XXXL");
		arraySize.add(ckbSizeXxxl.getText());
		ckbSizeXxxl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbSizeXxxl.getSelection()) {
					arraySize.add(ckbSizeXxxl.getText());

				} else {
					arraySize.remove(ckbSizeXxxl.getText());
				}
			}
		});

		ckbSizeXxxl.setBounds(367, 582, 111, 20);

		Button ckbColorBlack = new Button(group_1, SWT.CHECK);
		ckbColorBlack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbColorBlack.getSelection()) {
					arrayColor.add(ckbColorBlack.getText());

				} else {
					arrayColor.remove(ckbColorBlack.getText());
				}
			}
		});
		ckbColorBlack.setText("Black");
		ckbColorBlack.setSelection(true);
		arrayColor.add(ckbColorBlack.getText());

		ckbColorBlack.setBounds(594, 578, 111, 20);

		Button ckbColorNavyblue = new Button(group_1, SWT.CHECK);
		ckbColorNavyblue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbColorNavyblue.getSelection()) {
					arrayColor.add(ckbColorNavyblue.getText());

				} else {
					arrayColor.remove(ckbColorNavyblue.getText());
				}
			}
		});
		ckbColorNavyblue.setText("NavyBlue");
		ckbColorNavyblue.setSelection(true);
		arrayColor.add(ckbColorNavyblue.getText());

		ckbColorNavyblue.setBounds(594, 604, 111, 20);

		Button ckbXxxxl = new Button(group_1, SWT.CHECK);
		ckbXxxxl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbXxxxl.getSelection()) {
					arraySize.add(ckbXxxxl.getText());

				} else {
					arrayColor.remove(ckbXxxxl.getText());
				}
			}
		});
		ckbXxxxl.setText("XXXXL");
		ckbXxxxl.setBounds(367, 608, 111, 20);

		Button ckbXxxxxl = new Button(group_1, SWT.CHECK);
		ckbXxxxxl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbXxxxxl.getSelection()) {
					arraySize.add(ckbXxxxxl.getText());

				} else {
					arrayColor.remove(ckbXxxxxl.getText());
				}
			}
		});
		ckbXxxxxl.setText("XXXXXL");
		ckbXxxxxl.setBounds(367, 634, 111, 20);

		Label label_3 = new Label(group_1, SWT.SEPARATOR | SWT.VERTICAL);
		label_3.setBounds(194, 530, 7, 122);

		Label label_4 = new Label(group_1, SWT.SEPARATOR);
		label_4.setBounds(523, 530, 7, 122);

		Button ckbAllSize = new Button(group_1, SWT.CHECK);
		ckbAllSize.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbAllSize.getSelection()) {
					ckbSizeM.setSelection(true);
					ckbSizeS.setSelection(true);
					ckbSizeL.setSelection(true);
					ckbSizeXl.setSelection(true);
					ckbSizeXxxl.setSelection(true);
					ckbSizeXxl.setSelection(true);
					ckbXxxxl.setSelection(true);
					ckbXxxxxl.setSelection(true);

					ckbSizeM.setEnabled(false);
					ckbSizeS.setEnabled(false);
					ckbSizeL.setEnabled(false);
					ckbSizeXl.setEnabled(false);
					ckbSizeXxxl.setEnabled(false);
					ckbSizeXxl.setEnabled(false);
					ckbXxxxl.setEnabled(false);
					ckbXxxxxl.setEnabled(false);

					arraySize.add(ckbSizeM.getText());
					arraySize.add(ckbSizeS.getText());
					arraySize.add(ckbSizeL.getText());
					arraySize.add(ckbSizeXl.getText());
					arraySize.add(ckbSizeXxxl.getText());
					arraySize.add(ckbSizeXxl.getText());
					arraySize.add(ckbXxxxl.getText());
					arraySize.add(ckbXxxxxl.getText());
				} else {
					ckbSizeM.setSelection(true);
					ckbSizeS.setSelection(true);
					ckbSizeL.setSelection(true);
					ckbSizeXl.setSelection(true);
					ckbSizeXxxl.setSelection(true);
					ckbSizeXxl.setSelection(true);
					ckbXxxxl.setSelection(false);
					ckbXxxxxl.setSelection(false);

					ckbSizeM.setEnabled(true);
					ckbSizeS.setEnabled(true);
					ckbSizeL.setEnabled(true);
					ckbSizeXl.setEnabled(true);
					ckbSizeXxxl.setEnabled(true);
					ckbSizeXxl.setEnabled(true);
					ckbXxxxl.setEnabled(true);
					ckbXxxxxl.setEnabled(true);

					arraySize = new ArrayList<String>();
					arraySize.add(ckbSizeM.getText());
					arraySize.add(ckbSizeS.getText());
					arraySize.add(ckbSizeL.getText());
					arraySize.add(ckbSizeXl.getText());
					arraySize.add(ckbSizeXxxl.getText());
					arraySize.add(ckbSizeXxl.getText());
				}
			}
		});
		ckbAllSize.setText("All");
		ckbAllSize.setBounds(213, 528, 62, 20);

		Button ckbAlColor = new Button(group_1, SWT.CHECK);
		ckbAlColor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbAlColor.getSelection()) {
					ckbColorBlack.setSelection(true);
					ckbColorNavyblue.setSelection(true);
					ckbGreen.setSelection(true);

					ckbColorBlack.setEnabled(false);
					ckbColorNavyblue.setEnabled(false);
					ckbGreen.setEnabled(false);
					arrayColor.add(ckbColorBlack.getText());
					arrayColor.add(ckbColorNavyblue.getText());
					arrayColor.add(ckbGreen.getText());
				} else {
					ckbColorBlack.setSelection(true);
					ckbColorNavyblue.setSelection(true);
					ckbGreen.setSelection(true);

					ckbColorBlack.setEnabled(true);
					ckbColorNavyblue.setEnabled(true);
					ckbGreen.setSelection(true);

					arrayColor = new ArrayList<String>();
					arrayColor.add(ckbColorBlack.getText());
					arrayColor.add(ckbColorNavyblue.getText());
					arrayColor.add(ckbGreen.getText());
				}
			}
		});
		ckbAlColor.setText("All");
		ckbAlColor.setBounds(594, 552, 111, 20);

		Button ckbGuysTee = new Button(group_1, SWT.CHECK);
		ckbGuysTee.setText("Classic Men's T-Shirt");
		ckbGuysTee.setSelection(true);
		ckbGuysTee.setBounds(9, 547, 179, 20);
		arrayStyle.add(ckbGuysTee.getText());
		ckbGuysTee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbGuysTee.getSelection()) {
					arrayStyle.add(ckbGuysTee.getText());
				} else {
					arrayStyle.remove(ckbGuysTee.getText());
				}

			}
		});
		Button ckbLadiesTee = new Button(group_1, SWT.CHECK);
		ckbLadiesTee.setText("Classic Women's T-Shirt");
		ckbLadiesTee.setSelection(true);
		ckbLadiesTee.setBounds(9, 573, 179, 20);
		arrayStyle.add(ckbLadiesTee.getText());
		ckbLadiesTee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbLadiesTee.getSelection()) {
					arrayStyle.add(ckbLadiesTee.getText());

				} else {
					arrayStyle.remove(ckbLadiesTee.getText());
				}
			}
		});
		Button ckbHoodie = new Button(group_1, SWT.CHECK);
		ckbHoodie.setText("Unisex Hoodie");
		ckbHoodie.setSelection(true);
		ckbHoodie.setBounds(9, 625, 171, 20);
		arrayStyle.add(ckbHoodie.getText());
		ckbHoodie.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbHoodie.getSelection()) {
					arrayStyle.add(ckbHoodie.getText());

				} else {
					arrayStyle.remove(ckbHoodie.getText());
				}
			}
		});
		Button ckSweatshirtUnisex = new Button(group_1, SWT.CHECK);
		ckSweatshirtUnisex.setSelection(true);
		ckSweatshirtUnisex.setText("Unisex Sweatshirt");
		ckSweatshirtUnisex.setBounds(9, 599, 158, 20);
		ckSweatshirtUnisex.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckSweatshirtUnisex.getSelection()) {
					arrayStyle.add(ckSweatshirtUnisex.getText());

				} else {
					arrayStyle.remove(ckSweatshirtUnisex.getText());
				}
			}
		});
		arrayStyle.add(ckSweatshirtUnisex.getText());
		Button btnExport = new Button(group_1, SWT.NONE);

		btnExport.setImage(SWTResourceManager.getImage(TeenaviExportData.class, "/png/006-export.png"));
		btnExport.setBounds(631, 308, 111, 26);

		Label lblNewLabel = new Label(group_1, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(436, 311, 171, 20);
		lblNewLabel.setText("28-03-2019");

		txtStoreName = new Text(group_1, SWT.BORDER);
		txtStoreName.setText("***");
		txtStoreName.setBounds(631, 81, 111, 26);

		CLabel label_5 = new CLabel(group_1, SWT.NONE);
		label_5.setText("Store");
		label_5.setBounds(470, 81, 123, 26);

		txtTshirt = new Text(group_1, SWT.BORDER);
		txtTshirt.setText(
				"6.1-ounce, 100% cotton \\n Double-needle neck, sleeves and hem; Roomy Unisex Fit \\n Ash is 99% cotton, 1% poly; Sport Grey is 90% cotton, 10% poly; Dark Heather is 50% cotton, 50% polyester \\n Decoration type: Digital Print \\n Made by Gildan \\n");
		txtTshirt.setBounds(176, 113, 565, 26);

		Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setText("Description TShirt");
		label_6.setBounds(35, 119, 123, 20);

		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setText("Description Hoodie");
		label_7.setBounds(22, 151, 136, 20);

		txtHoddies = new Text(group_1, SWT.BORDER);
		txtHoddies.setText(
				"8 oz 50/50 cotton/poly \\n Air jet yarn creates a smooth, low-pill surface \\n Double needle stitching; Pouch pocket; Unisex sizing \\n  Decoration type: Digital Print \\n Made by Gildan");
		txtHoddies.setBounds(175, 145, 566, 26);

		txtSweatshirt = new Text(group_1, SWT.BORDER);
		txtSweatshirt.setText(
				"8 oz; 50% cotton/50% polyester \\n Air jet yarn for a softer feel and no pilling \\n 1x1 athletic rib cuffs and waistband with spandex; Double-needle stitching \\n Decoration type: Digital Print \\n Made by Gildan");
		txtSweatshirt.setBounds(175, 177, 566, 26);

		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setText("Description Sweatshirt ");
		label_8.setBounds(10, 177, 148, 20);

		Label label_9 = new Label(group_1, SWT.NONE);
		label_9.setText("Quantity");
		label_9.setBounds(88, 203, 70, 20);

		txtQuantity = new Text(group_1, SWT.BORDER);
		txtQuantity.setText("100");
		txtQuantity.setBounds(174, 209, 78, 26);

		txtCategory = new Text(group_1, SWT.BORDER);
		txtCategory.setText("2,20");
		txtCategory.setBounds(480, 209, 78, 26);

		Label label_10 = new Label(group_1, SWT.NONE);
		label_10.setText("Category ");
		label_10.setBounds(327, 212, 70, 20);

		Button button = new Button(group_1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dataListView.clear();
				listViewer.getList().removeAll();
			}
		});
		button.setImage(SWTResourceManager.getImage(TeenaviExportData.class, "/png/delete.png"));
		button.setBounds(271, 308, 53, 26);

		ckbGreen = new Button(group_1, SWT.CHECK);
		ckbGreen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ckbGreen.getSelection()) {
					arrayColor.add(ckbGreen.getText());

				} else {
					arrayColor.remove(ckbGreen.getText());
				}
			}
		});
		ckbGreen.setText("Green"); 
		ckbGreen.setBounds(594, 632, 111, 20);
		
		Label label_11 = new Label(group_1, SWT.NONE);
		label_11.setText("Tshirt");
		label_11.setAlignment(SWT.RIGHT);
		label_11.setBounds(81, 247, 70, 20);
		
		txtTshirtPrice = new Text(group_1, SWT.BORDER);
		txtTshirtPrice.setText("19.95");
		txtTshirtPrice.setBounds(171, 241, 49, 26);
		
		Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setText("Women-Tshirt");
		label_12.setBounds(233, 247, 70, 20);
		
		txtWomenTshirtPrice = new Text(group_1, SWT.BORDER);
		txtWomenTshirtPrice.setText("22.95");
		txtWomenTshirtPrice.setBounds(324, 241, 49, 26);
		
		Label label_13 = new Label(group_1, SWT.NONE);
		label_13.setText("Sweatshirt");
		label_13.setBounds(402, 247, 70, 20);
		
		txtSweatshirtPrice = new Text(group_1, SWT.BORDER);
		txtSweatshirtPrice.setText("29.95");
		txtSweatshirtPrice.setBounds(482, 241, 49, 26);
		
		Label label_14 = new Label(group_1, SWT.NONE);
		label_14.setText("Hoddies");
		label_14.setAlignment(SWT.RIGHT);
		label_14.setBounds(537, 247, 70, 20);
		
		txtHoddiesPrice = new Text(group_1, SWT.BORDER);
		txtHoddiesPrice.setText("39.95");
		txtHoddiesPrice.setBounds(634, 241, 49, 26);

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
				labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				labMessage.setText("Running...........");
				nameFileOutPut = textNameFileOut.getText();
				hashMapMoteefeeStyle.put("Classic Men's T-Shirt", "Men's T-Shirt");
				hashMapMoteefeeStyle.put("Classic Women's T-Shirt", "Women's T-Shirt");
				hashMapMoteefeeStyle.put("Unisex Sweatshirt", "Unisex Sweatshirt");
				hashMapMoteefeeStyle.put("Unisex Hoodie", "Unisex Hoodie");
				
				hashMapMoteefeePrice.put("Classic Men's T-Shirt", "Men's T-Shirt");
				hashMapMoteefeePrice.put("Classic Women's T-Shirt", "Women's T-Shirt");
				hashMapMoteefeePrice.put("Unisex Sweatshirt", "Unisex Sweatshirt");
				hashMapMoteefeePrice.put("Unisex Hoodie", "Unisex Hoodie");
				
				hashMapMoteefeeColor.put("Black", "black");
				hashMapMoteefeeColor.put("NavyBlue", "navy blue");
				hashMapMoteefeeColor.put("Green", "green");

				hashMapMoteefeeColorHoddie.put("Black", "jet black");
				hashMapMoteefeeColorHoddie.put("NavyBlue", "oxford navy");
				hashMapMoteefeeColorHoddie.put("Green", "green");

				hashStyleMap.put("Men's T-Shirt", "Men-T-Shirt");
				hashStyleMap.put("Women's T-Shirt", "Women-T-Shirt");
				hashStyleMap.put("Unisex Sweatshirt", "Sweatshirt");
				hashStyleMap.put("Unisex Hoodie", "Hoodie");

				arrayStringShirt.add("\\ shirt\\ ");
				arrayStringShirt.add("\\ shirts\\ ");
				arrayStringShirt.add("\\ tshirts\\ ");
				arrayStringShirt.add("\\ tshirt\\ ");
				arrayStringShirt.add("\\ t\\ shirts\\ ");
				arrayStringShirt.add("\\ t\\ shirt\\ ");
				nameStore = txtStoreName.getText();
				if (StringUtil.isBlank(nameFileOutPut)) {
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					labMessage.setText("NameFileOutPut is not null");
					return;
				}
				if (StringUtil.isBlank(nameFileOutPut)) {
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					labMessage.setText("NameFileOutPut is not null");
					return;
				}
				if (StringUtil.isBlank(nameFileOutPut)) {
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					labMessage.setText("Tag is not null");
					return;
				}

				ArrayList<Template> listObjs = new ArrayList<Template>();

				if (dataListView.size() <= 0) {
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					labMessage.setText("List view is not null, add link to list view");
					return;
				}
				latch = new CountDownLatch(dataListView.size());
				String[] arrayLink = new String[] {};
				if (StringUtil.isBlank(hostName)) {
					arrayLink = dataListView.get(0).split("/");
					for (int x = 0; x < 3; x++) {
						hostName += arrayLink[x] + "/";
					}
				}
				ArrayList<Template> listObjLink = new ArrayList<Template>();
				for (String link : dataListView) {
					listObjLink = new ArrayList<Template>();
					if(link.contains("https://moteefe.com")) {
						listObjLink = contentChild(link, "");
					}else {
						listObjLink = contentChildGetTeeNavi(link, "");
					}
					
					for (Template template : listObjLink) {
						listObjs.add(template);
					}
				}

				Collections.sort(listObjs, new Template.CompTitle());
				long end = Calendar.getInstance().getTimeInMillis();
				log.debug("Executed Time: " + (end - begin));
				// ExportExcel(listObj1, listHeader);
				String nameFile = "";
				nameFile = Commond.exportCSV(listObjs, nameFileOutPut);

				try {
					// Collections.sort(listTemplate, new Template.CompTitle());
					String ProIDTemp = "";
					try {
						FileReader fileReader = new FileReader(nameFile);
						BufferedReader br = new BufferedReader(fileReader);
						String stringRead = "";
						boolean flag = false;
						String title = "";
						String size="";
						String sizeTemp="";
						double price=0;
						String productId = "";
						TemplatePestra temp2 = new TemplatePestra();
						TemplatePestra temp = new TemplatePestra();
						listTemplatePestra.clear();
						int indexRow = 0;
						String titleTemp = "";
						hashColumn1.clear();
						hashColumn1 = new HashMap<String, String>();

						while ((stringRead = br.readLine()) != null && !stringRead.isEmpty()) {
							// List<String> line = Commond.parseLine(stringRead);
							stringRead = stringRead.replace("\",\"", ";");
							String[] fields = stringRead.split(";");

							if (fields.length <= 0)
								break;
							if (indexRow == 0) {
								for (int xxxx = 0; xxxx < fields.length; xxxx++) {
									// stringRead=stringRead.replace("\"", "");
									hashColumn1.put(
											fields[xxxx].trim().replace("\"", "").toLowerCase().replace("\\\"", ""),
											String.valueOf(xxxx));

								}
								indexRow++;
								continue;
							}

							if (fields.length < 10) {
								labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
								labMessage.setText("Check content Body(html) ");
							}

							temp = new TemplatePestra();

							title = fields[Integer.valueOf(hashColumn1.get("Title".trim().toLowerCase()))].replace("\"",
									"");
							size = fields[Integer.valueOf(hashColumn1.get("Option1 Value".toLowerCase()))].replace("\"", ""); 
							productId = fields[Integer.valueOf(hashColumn1.get("Variant SKU".trim().toLowerCase()))]
									.replace("\"", "") + "111";
							if (listTemplatePestra.size() >= 1) {
								temp2 = new TemplatePestra();
								temp2 = listTemplatePestra.get(listTemplatePestra.size() - 1);
								titleTemp = temp2.getTitle();
								sizeTemp= temp2.getSize() ;
								if (!titleTemp.equals(title)) {
										
									listTemplatePestra.remove(listTemplatePestra.size() - 1);
										
									if (titleTemp.trim().toLowerCase().contains("hoodie")) {
										temp.assignFullTemplate(temp2, txtHoddies.getText(), txtCategory.getText(),
												hashColumn1);
 										flag = true;
									}
									if (titleTemp.trim().toLowerCase().contains("sweatshirt")) {
										temp.assignFullTemplate(temp2, txtSweatshirt.getText(), txtCategory.getText(),
												hashColumn1);
 
										flag = true;
									}
									if (!flag) {
										temp.assignFullTemplate(temp2, txtTshirt.getText(), txtCategory.getText(),
												hashColumn1);

									}
									if (sizeTemp.equals("XXL") || size.equals("XXXL") || sizeTemp.equals("XXXXL") || sizeTemp.equals("XXXXXL")||
											sizeTemp.equals("2XL") || size.equals("3XL") || sizeTemp.equals("4XL") || sizeTemp.equals("5XL")) {
  									} 
									listTemplatePestra.add(temp);
									flag = false;
									temp = new TemplatePestra();
									ProIDTemp = productId;
								}

								if (title.trim().toLowerCase().contains("men-t-shirt")
										|| title.trim().toLowerCase().contains("men t shirt")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "1", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtTshirtPrice.getText());

									flag = true;
								}
								if (title.trim().toLowerCase().contains("women-t-shirt")
										|| title.trim().toLowerCase().contains("women t shirt")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "4", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtWomenTshirtPrice.getText());

									flag = true;
								}
								if (title.trim().toLowerCase().contains("hoodie")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "2", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtHoddiesPrice.getText());

									flag = true;
								}
								if (title.trim().toLowerCase().contains("sweatshirt")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "3", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtSweatshirtPrice.getText());

									flag = true;
								}
								if (!flag) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "5", txtQuantity.getText(),
											hashColumn1);

								}
								if (size.equals("XXL") || size.equals("XXXL") || size.equals("XXXXL") || size.equals("XXXXXL")||
										size.equals("2XL") || size.equals("3XL") || size.equals("4XL") || size.equals("5XL")) {
									price=Double.valueOf(temp.getPrice())+3;
									temp.setPrice(String.valueOf(price)); 
								} 
								listTemplatePestra.add(temp);
								flag = false;
								temp = new TemplatePestra();

							} else {
								ProIDTemp = productId;
								if (title.trim().toLowerCase().contains("men-t-shirt")
										|| title.trim().toLowerCase().contains("men t shirt")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "1", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtTshirtPrice.getText());
									flag = true;
								}
								if (title.trim().toLowerCase().contains("women-t-shirt")
										|| title.trim().toLowerCase().contains("women t shirt")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "4", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtWomenTshirtPrice.getText());
									flag = true;
								}
								if (title.trim().toLowerCase().contains("hoodie")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "2", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtHoddiesPrice.getText());
									flag = true;
								}
								if (title.trim().toLowerCase().contains("sweatshirt")) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "3", txtQuantity.getText(),
											hashColumn1);
									temp.setPrice(txtSweatshirtPrice.getText());
									flag = true;
								}
								if (!flag) {
									temp.assignTemplateNoTag(fields, title, ProIDTemp + "5", txtQuantity.getText(),
											hashColumn1);

								}
								if (size.equals("XXL") || size.equals("XXXL") || size.equals("XXXXL") || size.equals("XXXXXL")||
										size.equals("2XL") || size.equals("3XL") || size.equals("4XL") || size.equals("5XL")) {
									price=Double.valueOf(temp.getPrice())+3;
									temp.setPrice(String.valueOf(price)); 
								} 
								listTemplatePestra.add(temp);
								flag = false;
								temp = new TemplatePestra();
							}
						}
						// Chot dong cuoi cung
						if (listTemplatePestra.size() > 1) {
							temp2 = new TemplatePestra();
							temp2 = listTemplatePestra.get(listTemplatePestra.size() - 1);

							listTemplatePestra.remove(listTemplatePestra.size() - 1);
							if (title.trim().toLowerCase().contains("men-t-shirt")
									|| title.trim().toLowerCase().contains("men t shirt")) {
								temp.assignFullTemplate(temp2, txtTshirt.getText(), txtCategory.getText(), hashColumn1);
								temp.setPrice(txtTshirtPrice.getText());
								flag = true;
							}
							if (title.trim().toLowerCase().contains("women-t-shirt")
									|| title.trim().toLowerCase().contains("women t shirt")) {
								temp.assignFullTemplate(temp2, txtTshirt.getText(), txtCategory.getText(), hashColumn1);
								temp.setPrice(txtWomenTshirtPrice.getText());
								flag = true;
							}
							if (title.trim().toLowerCase().contains("hoodie")) {
								temp.assignFullTemplate(temp2, txtHoddies.getText(), txtCategory.getText(),
										hashColumn1);
								temp.setPrice(txtHoddiesPrice.getText());
								flag = true;
							}
							if (title.trim().toLowerCase().contains("sweatshirt")) {
								temp.assignFullTemplate(temp2, txtSweatshirt.getText(), txtCategory.getText(),
										hashColumn1);
								temp.setPrice(txtSweatshirtPrice.getText());
								flag = true;
							}
							if (!flag) {
								temp.assignFullTemplate(temp2, "", txtCategory.getText(), hashColumn1);

							}
							if (size.equals("XXL") || size.equals("XXXL") || size.equals("XXXXL") || size.equals("XXXXXL")||
									size.equals("2XL") || size.equals("3XL") || size.equals("4XL") || size.equals("5XL")) {
								price=Double.valueOf(temp.getPrice())+3;
								temp.setPrice(String.valueOf(price)); 
							} 
							listTemplatePestra.add(temp);
							flag = false;
							temp = new TemplatePestra();
						}

						br.close();
					} catch (IOException ex) {
					}
					// ExportExcel(listObj1, listHeader);
					Commond.exportXLSTemplatePestra(listTemplatePestra);
					Commond.exportCSVTemplateWP(listTemplatePestra);
					// Commond.exportCSVTemplatePestra(listTemplatePestra);
					labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					labMessage.setText("Export Success !!!");

				} catch (Exception e1) {
					// TODO: handle exception
				}
				labMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				labMessage.setText("Excel written successfully");
				return;
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
						String docNumber = "";
						String[] strArray = {};
						while (stringRead != null) {
							boolean flag = true;
							stringRead = stringRead.replace("\"", "");
							if (!stringRead.contains("http")) {
								stringRead = br.readLine();
								continue;
							}
							strArray = stringRead.split(",");
							if (strArray.length == 1)
								docNumber = strArray[0] + "___";
							if (strArray.length == 2) {
								docNumber = strArray[0] + "___" + strArray[1].replace("\"", "");
								System.out.println(docNumber.toString());
							}
							if (strArray.length == 3) {
								docNumber = strArray[0] + "___" + strArray[1].replace("\"", "") + ", "
										+ strArray[2].replace("\"", "");
								System.out.println(docNumber.toString());
							}
							if (strArray.length == 4) {
								docNumber = strArray[0] + "___" + strArray[1].replace("\"", "") + ", "
										+ strArray[2].replace("\"", "") + ", " + strArray[3].replace("\"", "");
								System.out.println(docNumber.toString());
							}
							if (strArray.length == 5) {
								docNumber = strArray[0] + "___" + strArray[1].replace("\"", "") + ", "
										+ strArray[2].replace("\"", "") + ", " + strArray[3].replace("\"", "") + ", "
										+ strArray[4].replace("\"", "");
								System.out.println(docNumber.toString());
							}

							if (!docNumber.isEmpty())
								docNumber.replaceAll("\"___\"", "");
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
					System.out.println("File access cancelled by user.");
				}
			}
		});

		// Add String to List
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String link = txtLink.getText();
				String titleReplace = txtTitleReplace.getText();
				String temp = "";
				if (link != "" && link != null) {
					boolean flag = true;
					List<String> list = new ArrayList<>();

					if (link.contains("?") && !link.contains("https://moteefe.com")
							&& !link.contains("https://www.moteefe.com")) {
						String[] arrayLink = link.split("\\?");
						if (arrayLink.length > 0) {
							link = arrayLink[0];
						}
					}

					for (String string : dataListView) {
						String[] arrLink = string.split("___");
						if (arrLink.length >= 2) {
							temp = arrLink[0];
						} else {
							temp = string;
						}
						if (temp.equals(link)) {
							lbStatus.setText("String is exist");
							flag = false;
						}
						temp = "";
					}
					if (flag)
						if (!StringUtil.isBlank(titleReplace)) {
							listViewer.add(link + "___" + titleReplace);
							dataListView.add(link + "___" + titleReplace);
							lbStatus.setText("");
						} else {
							listViewer.add(link);
							dataListView.add(link);
							labMessage.setText("");
							txtLink.setText("");
						}
					txtLink.setText("");
					txtTitleReplace.setText("");
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

	}

	static <T> ArrayList<String> inspect(Class<T> klazz) {
		Field[] fields = klazz.getDeclaredFields();
		ArrayList<String> listReturn = new ArrayList<String>();
		System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			/*
			 * System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()),
			 * field.getType().getSimpleName(),field.getName());
			 */
			listReturn.add(field.getName());

		}
		return listReturn;
	}

	/*
	 * private StringBuffer getContentURL(String urlLink) throws IOException { URL
	 * url; StringBuffer doc = new StringBuffer(); try { url = new URL(urlLink); //
	 * Get the input stream through URL Connection URLConnection con =
	 * url.openConnection(); InputStream is = con.getInputStream();
	 * 
	 * BufferedReader br = new BufferedReader(new InputStreamReader(is)); String
	 * line = "";
	 * 
	 * // read each line and write to System.out while ((line = br.readLine()) !=
	 * null) { doc.append(line); }
	 * 
	 * URL oracle = new URL(urlLink); URLConnection yc = oracle.openConnection();
	 * BufferedReader in = new BufferedReader(new InputStreamReader(
	 * yc.getInputStream())); String inputLine; while ((inputLine = in.readLine())
	 * != null) doc.append(inputLine); in.close(); } catch (MalformedURLException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return doc; }
	 */

	public ArrayList<Template> returnListTemplate(Elements divBtnGroup, String title, String selectCatagories,
			String index) {
		Elements labelsColor = divBtnGroup.select("label");
		ArrayList<Template> listObj = new ArrayList<Template>();
		/////////////////////////
		// Lấy màu đang ch�?n//////
		ArrayList<Template> listObjReturn = new ArrayList<Template>();
		String linkColor;
		String color = "";
		try {
			if (labelsColor.size() >= 1) {
				for (Element labelColor : labelsColor) {

					linkColor = labelColor.attr("onClick").split("=")[1];
					color = labelColor.attr("title");
					if (arrayColor.contains(color)) {
						listObj = returnTemplate(labelColor, title, selectCatagories, index);
						listObjReturn.addAll(listObj);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listObjReturn;
	}

	public ArrayList<Template> returnTemplate(Element labelColor, String title, String selectCatagories, String index) {
		log.debug("Ra Ham returnTemplate");
		ArrayList<Template> listObj = new ArrayList<Template>();

		try {
			String[] styleAndPrices;
			String styleAndPricesSelect;
			StringBuffer contentChild;
			Elements divTitles = null;
			String linkColor;
			Float price = (float) 0;
			String color = "";
			String style = "";
			Element selectedStyle;
			linkColor = labelColor.attr("onClick").split("=")[1];
			color = labelColor.attr("title");
			System.out.println(hostName + linkColor.replace("'", "").replace(";", ""));
			Document documentChild = Jsoup.connect(hostName + linkColor.replace("'", "").replace(";", ""))
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.referrer("http://www.google.com").get();

			// Lấy style và giá
			selectedStyle = documentChild.select("select option[selected]").first();
			// log.debug(selectedStyle.text());
			String[] styleAndPrice = selectedStyle.text().split("\\$");
			/////////////////////////
			// Lấy giá và Style đang ch�?n//////
			String arrayPrice = "";
			if (styleAndPrice.length >= 2) {
				arrayPrice = styleAndPrice[styleAndPrice.length - 1];

				price = Float.valueOf(arrayPrice);

				/////////////////////////
				// Lấy Style đang ch�?n//////
				style = styleAndPrice[0];
			}
			Element proSKU = documentChild.select("input[name=mockupID]").first();
			String sku = proSKU.attr("value");

			// Lấy hình ảnh Chính
			String linkImg = documentChild.select("div[class=product-zoom-image]").select("img").attr("data-zoom");
			// Lay toan bo sixe
			Elements divArraySize = documentChild.select("select option[data-se]");

			if (arraySize.size() <= 0) {
				arraySize = new ArrayList<String>();
				for (int g = 0; g < divArraySize.size(); g++) {
					String data = divArraySize.get(g).text();
					arraySize.add(data);
				}
			}

			for (String size : arraySize) {
				System.out.println("labelColor-----" + labelColor + "size---" + size);
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
				objTemplate.setVendor("Sunfog");
				objTemplate.setHandle(title);
				objTemplate.setImageAltText(title);
				objTemplate.setImagesrc("https://" + linkImg.replace("//", ""));
				objTemplate.setVariantImage("https://" + linkImg.replace("//", ""));
				objTemplate.setVariantSKU(sku);
				objTemplate.setOption3Value(style);
				objTemplate.setSEODescription(title);
				objTemplate.setSEOTitle(title);
				objTemplate.setBodyHTML(title);
				objTemplate.setTags(selectCatagories);
				objTemplate.setSEODescription(title);
				String url = (hostName + linkColor.replace("'", "").replace(";", "") + "?" + sellerID);
				objTemplate.setSourceURL(url);
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
		} catch (

		Exception e) {
			// TODO: handle exception
		}
		log.debug("Ra Ham returnTemplate");
		return listObj;
	}
	
	public ArrayList<Template> contentChildGetTeeNavi(String linkHref, String selectCatagories) {
		ArrayList<Template> listObj = new ArrayList<Template>();
		if (linkHref != "") {
			String imageLink = "";
			double price = 0;
			String title = "";
			String[] arrLink = linkHref.split("___");
			if (arrLink.length >= 2) {
				linkHref = arrLink[0];
				if (!"XXX".equals(arrLink[0]))
					title = arrLink[1];
			} else {
				linkHref = arrLink[0];
			}
			Document docPageMain;
			try {
				docPageMain = Jsoup.connect(linkHref).timeout(50 * 1000)
						.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
						.referrer("http://www.google.com").ignoreContentType(true).get();

				imageLink = docPageMain.getElementsByTag("meta").get(9).attr("content");
			} catch (IOException e1) {
				e1.printStackTrace();

			}
			if (linkHref.contains("?")) {
				String array[] = linkHref.split("\\?");
				if (array.length > 0) {
					if (!array[0].contains("?locale=en&user_currency=USD"))
						linkHref = array[0] + "?locale=en&user_currency=USD";
					else
						linkHref = array[0];
				}
			} else {
				if (!linkHref.contains("?locale=en&user_currency=USD"))
					linkHref = linkHref + "?locale=en&user_currency=USD";
			}
			System.out.println("Title  : " + title);
			String styleMoteefeNo = "";
			String sku = "";
			Template objTemplate = new Template();
			for (String selectedStyle : arrayStyle) {
				styleMoteefeNo = hashMapMoteefeeStyle.get(selectedStyle);
				for (String labelColor : arrayColor) {
					String styleTemp = "";
					styleTemp = hashStyleMap.get(styleMoteefeNo);
					if ("Men-T-Shirt".equals(styleTemp) || "Women-T-Shirt".equals(styleTemp)) {
						price = 19.95;
					}
					if ("Hoodie".equals(styleTemp)) {
						price = 39.95;
					}
					if ("Sweatshirt".equals(styleTemp)) {
						price = 29.95;
					}
					for (String size : arraySize) {
						objTemplate = new Template();
						// Set titel for data Excel
						objTemplate.setTitle(title + " " + styleTemp + " - " + nameStore);
						objTemplate.setHandle(title + " " + styleTemp + " - " + nameStore.replace("#", ""));
						objTemplate.setImageAltText(title + " " + styleTemp + " - " + " " + nameStore);

						objTemplate.setVendor("Moteefe");
						objTemplate.setImagesrc(imageLink);
						objTemplate.setVariantImage(imageLink);
						objTemplate.setOption1Value(size);
						objTemplate.setOption2Value(labelColor);
						objTemplate.setBodyHTML(title);
						objTemplate.setOption3Value(styleMoteefeNo);
						objTemplate.setVariantSKU(sku);
						objTemplate.setImageAltText(title);
						objTemplate.setSEODescription(title);
						objTemplate.setSEOTitle(title);
						objTemplate.setSEODescription(title);
						objTemplate.setTags("Moteefe");
						objTemplate.setSourceURL(linkHref);
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
		}
		return listObj;
	}

	public ArrayList<Template> contentChild(String linkHref, String selectCatagories) {
		ArrayList<Template> listObj = new ArrayList<Template>();
		String[] arrayLink = new String[] {};
		if (linkHref != "") {
			try {
				// log.debug("Vao trang contentChild");
				// Trang con sau khi link qua

				//// log.debug("Trang con sau khi link qua"
				//// + ":" + linkHref);
				String[] styleAndPrices;
				String styleAndPricesSelect;
				String linkStyle;
				String linkColor;
				StringBuffer contentChild;
				double price = 0;
				double pricetmp = 0;
				String color = "";
				String style = "";
				String title = "";
				String titleChild = "";
				boolean flagEx = false;
				String[] styleAndPrice = null;
				String[] arrLink = linkHref.split("___");
				if (arrLink.length >= 2) {
					linkHref = arrLink[0];
					if (!"XXX".equals(arrLink[0]))
						title = arrLink[1];

				}else {
					linkHref = arrLink[0];
				}

//				if (linkHref.contains("https://teenavi.net") || linkHref.contains("https://www.teenavi.net")) {
				if (true) {
					Document docPageMain;
					try {
						docPageMain = Jsoup.connect(linkHref).timeout(50 * 1000)
								.userAgent(
										"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
								.referrer("http://www.google.com").ignoreContentType(true).get();

						Document documentChildMain = Jsoup.parse(docPageMain.toString());
						// Commond .saveFileTXT(documentChild +
						// "\n", "txt","MoteefeDoc");
						Elements scriptElement = documentChildMain.select("script[data-component-name=StorefrontApp]");
						String data = scriptElement.html();
						// Commond.saveFileTXT(data + "\n", "txt",
						// "MoteefeDoctext");
						// System.out.println("scriptElement size : " +
						// String.valueOf(scriptElement.size()));
						ObjectMapper mapper = new ObjectMapper();
						mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						// log.debug("title" + title);
						MyPojo myPojo = new MyPojo();
						// create a new Gson instance
						Gson gson = new Gson();
						// convert your list to json
//						String jsonCartList = gson.toJson(myPojo);
						//huynnp
//						myPojo = mapper.readValue(data, new TypeReference<MyPojo>() {
//						});
//						if (myPojo.getState().getStore().getCampaigns().size() > 0)
//							linkHref = linkHref + "/" + myPojo.getState().getStore().getCampaigns().get(0).getSlug()
//									+ "?locale=en&user_currency=USD";
//						System.out.println("linkHref : " + linkHref  );
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						flagEx = true;
						e1.printStackTrace();

					}
					Document docPage;
					try {
						if (linkHref.contains("?")) {
							String array[] = linkHref.split("\\?");
							if (array.length > 0) {
								if (!array[0].contains("?locale=en&user_currency=USD"))
									linkHref = array[0] + "?locale=en&user_currency=USD";
								else
									linkHref = array[0];
							}
						} else {
							if (!linkHref.contains("?locale=en&user_currency=USD"))
								linkHref = linkHref + "?locale=en&user_currency=USD";
						}

						docPage = Jsoup.connect(linkHref).timeout(50 * 1000)
								.userAgent(
										"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
								.referrer("http://www.google.com").ignoreContentType(true).get();

						Document documentChild = Jsoup.parse(docPage.toString());
						// Commond .saveFileTXT(documentChild + "\n",
						// "txt","MoteefeDoc");
						Elements scriptElement = documentChild.select("script[ data-component-name=CampaignPageApp]");
						String data = scriptElement.html();
						//Commond.saveFileTXT(data + "\n", "txt", "MoteefeDoctext");
						System.out.println("scriptElement size : " + String.valueOf(scriptElement.size()));
						ObjectMapper mapper = new ObjectMapper();
						mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						// log.debug("title" + title);
						RootObject rootObject = new RootObject();
						// create a new Gson instance
						Gson gson = new Gson();
						// convert your list to json
//						String jsonCartList = gson.toJson(rootObject);
						rootObject = mapper.readValue(data, new TypeReference<RootObject>() {
						});
						if (StringUtil.isBlank(title))
							title = rootObject.getState().getPage().getCampaign().getName();

						String idProduct = "";
						String imageMoteefeNo = "";
						Campaign_mockups campaign_mockups = new Campaign_mockups();
						campaign_mockups = rootObject.getState().getPage().getCampaign_mockups()[0];
						if (campaign_mockups.getSide().toLowerCase().equals("back")) {
							System.out.println("link : " + linkHref + " ---- back \n");
							return listObj;

						}
						System.out.println("Title  : " + title);
						title = Commond.replaceString(arrayStringShirt, title, " ");
						// String colorMoteefe="";
						String colorMoteefe = "";
						String colorMoteefeNo = "";
						String styleMoteefeNo = "";
						String sku = "";
						Template objTemplate = new Template();
						int indexImage = 0;
						for (String selectedStyle : arrayStyle) {
							// Kiem hinh va gia
							styleMoteefeNo = hashMapMoteefeeStyle.get(selectedStyle);
							idProduct = "";
							for (Products products : rootObject.getState().getPage().getProducts()) {
								if (!products.getTracking_name().toLowerCase().equals(styleMoteefeNo.toLowerCase())) {
									continue;
								} else {

									idProduct = products.getId();
									titleChild = title;
									sku = rootObject.getState().getPage().getCampaign().getId();
									break;
								}

							}
							if (StringUtil.isBlank(idProduct)) {
								break;
							}
							for (String labelColor : arrayColor) {
								if (!hashMapMoteefeeColor.get(labelColor).isEmpty()) {
									if (styleMoteefeNo.equals("Men's T-Shirt")
											|| (styleMoteefeNo.equals("Women's T-Shirt"))) {
										colorMoteefe = hashMapMoteefeeColor.get(labelColor);
									} else {
										colorMoteefe = hashMapMoteefeeColorHoddie.get(labelColor);
									}
									for (Products products : rootObject.getState().getPage().getProducts()) {
										if (!products.getTracking_name().toLowerCase()
												.equals(styleMoteefeNo.toLowerCase())) {
											continue;
										} else {
											for (Colors colorlst : products.getColors()) {
												if (!colorlst.getName().toLowerCase()
														.equals(colorMoteefe.toLowerCase())) {
													continue;
												} else {
													colorMoteefeNo = colorlst.getId();
													break;
												}

											}
											break;
										}

									}
								}
								imageMoteefeNo = "";
								indexImage = 0;
								for (Campaign_mockups campaignmockups : rootObject.getState().getPage()
										.getCampaign_mockups()) {
									// System.out.println("1-------" + idProduct
									// + "/" + colorMoteefeNo);
									// System.out.println("2-------" +
									// campaignmockups.getProduct_id() + "/" +
									// campaignmockups.getColor_id());
									if (indexImage == 0) {
										if (campaignmockups.getProduct_id().equals(idProduct)
												&& campaignmockups.getColor_id().toLowerCase()
														.equals(colorMoteefeNo.toLowerCase())
												&& campaignmockups.getSide().equals("front")) {

											imageMoteefeNo = campaignmockups.getImage().getBig();
											indexImage = 1;
											System.out.println(imageMoteefeNo + "\n link : " + linkHref + "\n ");
											for (Campaign_products campaignpro : rootObject.getState().getPage()
													.getCampaign_products()) {
												if (campaignpro.getProduct_id().equals(idProduct)) {
													pricetmp = Float.valueOf(campaignpro.getPrice());
													price = (double) Math.round(pricetmp * 100) / 100;
													break;
												}
											}
										}
									}

								}
								if (StringUtil.isBlank(imageMoteefeNo)) {
									continue;
								}
								String styleTemp = "";
								styleTemp = hashStyleMap.get(styleMoteefeNo);
								for (String size : arraySize) {
									objTemplate = new Template();
									// Set titel for data Excel
									objTemplate.setTitle(titleChild + " " + styleTemp + " - " + nameStore);
									objTemplate
											.setHandle(titleChild + " " + styleTemp + " - " + nameStore.replace("#", ""));
									objTemplate.setImageAltText(titleChild + " " + styleTemp + " - " + " " + nameStore);

									objTemplate.setVendor("Moteefe");
									objTemplate.setImagesrc(imageMoteefeNo.toString());
									objTemplate.setVariantImage(imageMoteefeNo.toString());
									objTemplate.setOption1Value(size);
									objTemplate.setOption2Value(labelColor);
									objTemplate.setBodyHTML(titleChild);
									objTemplate.setOption3Value(styleMoteefeNo);
									objTemplate.setVariantSKU(sku);
									objTemplate.setImageAltText(titleChild);
									objTemplate.setSEODescription(titleChild);
									objTemplate.setSEOTitle(titleChild);
									objTemplate.setSEODescription(titleChild);
									objTemplate.setTags("Moteefe");
									objTemplate.setSourceURL(linkHref);
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

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					return listObj;
				}

				Document documentChild = Jsoup.connect(linkHref)
						.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
						.referrer("http://www.google.com").get();

				// Nội dung trang chi tiết

				// log.debug(documentChild.toString());
				Elements TitleChild = documentChild.select("h1[class=product-title]");
				titleChild = TitleChild.get(0).text().replaceAll(",", "/");
				// lay Descrition

				// Lấy vòng lập cho kiểu dáng
				Elements selectedsStyle = documentChild.select("select[id=shirtTypes]").select("option");
				// Vòng lặp cho Style để ch�?n 1 mẫu mới
				for (Element selectedStyle : selectedsStyle) {
					styleAndPrice = selectedStyle.text().split("\\$");
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

					documentChild = Jsoup.connect(hostName + linkStyle)
							.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
							.referrer("http://www.google.com").get();

					// Lấy nhóm màu sắc để ch�?n màu
					Elements divBtnGroup = documentChild.select("div.btn-group");
					// Nếu có màu sắc, thì duyệt các ô màu
					// để lấy ra trang chi tiết
					if (divBtnGroup.size() > 0) {
						ArrayList<Template> returnListTemplate = returnListTemplate(divBtnGroup, titleChild,
								selectCatagories, "");
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
						String sku = proSKU.attr("value");
						// Lấy list style và giá trong
						// combobox
						selectedStyle = documentChild.select("select option[selected]").first();
						//// log.debug("selectedStyle" +
						//// selectedStyle);

						// log.debug(selectedStyle.text());
						styleAndPrice = selectedStyle.text().split("\\$");
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
						// Lay toan bo sixe
						Elements divArraySize = documentChild.select("select option[data-se]");
						arraySize = new ArrayList<String>();

						if (arraySize.size() <= 0) {
							arraySize = new ArrayList<String>();
							for (int g = 0; g < divArraySize.size(); g++) {
								String data = divArraySize.get(g).text();
								arraySize.add(data);
							}
						}
						String styleTemp = "";
						styleTemp = hashStyleMap.get(style);
						for (String size : arraySize) {
							Template objTemplate = new Template();

							objTemplate.setImagesrc("https://" + linkImg.replace("//", ""));
							objTemplate.setVariantImage("https://" + linkImg.replace("//", ""));
							objTemplate.setOption1Value(size);
							/*
							 * if (color != null && color != ""){ objTemplate.setOption2Value(color);
							 * objTemplate.setHandle(titleChild + "-" + style+ "-" +color); } else{
							 * objTemplate.setOption2Value("-"); objTemplate.setHandle(titleChild + "-" +
							 * style ); }
							 */
							objTemplate.setVendor("Moteefe");
							// Set titel for data Excel
							objTemplate.setTitle(titleChild + " " + styleTemp + " " + nameStore);
							objTemplate.setHandle(titleChild + " " + styleTemp + " " + nameStore.replace("#", ""));
							objTemplate.setImageAltText(titleChild + " " + styleTemp + " " + " " + nameStore);

							objTemplate.setBodyHTML(titleChild);
							objTemplate.setOption3Value(style);
							objTemplate.setVariantSKU(sku);
							objTemplate.setSEODescription(titleChild);
							objTemplate.setSEOTitle(titleChild);
							objTemplate.setSEODescription(titleChild);
							objTemplate.setSourceURL(hostName + linkStyle);
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
}
