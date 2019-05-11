package searchTeenavySport;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jsoup.helper.StringUtil;

public class ImageServices {
	
	public String createFolderImage() {
		Date date = new Date();
		String currentDirectory = new File("").getAbsolutePath();
		new File(currentDirectory + "\\image" + date.getDay() + date.getMonth() + date.getYear() + "_" + date.getHours()
				+ date.getMinutes()).mkdir();
		return currentDirectory + "\\image" + date.getDay() + date.getMonth() + date.getYear() + "_" + date.getHours()
				+ date.getMinutes() + "\\";
	}

	public boolean writeImageFromUrl(String currentDirectory, String imageUrl, String title,String ckbPng,String ckbJpg) {
		BufferedImage image = null;
		boolean status = true;
		try {
			URL url = new URL(imageUrl);

			// read the url
			image = ImageIO.read(url);

			// for png
			/*if(!StringUtil.isBlank(ckbPng))
				ImageIO.write(image, "png", new File(currentDirectory + title + ".png"));*/

			// for jpg
			if(!StringUtil.isBlank(ckbJpg))
				ImageIO.write(image, "jpg", new File(currentDirectory + title + ".jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return status = false;
		}
		return status;
	}
}
