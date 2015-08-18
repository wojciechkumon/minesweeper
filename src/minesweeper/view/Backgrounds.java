package minesweeper.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Backgrounds {

	private static Backgrounds instance = null;
	public final BufferedImage field;
	public final BufferedImage hoveredField;
	public final BufferedImage clickedField;
	
	public final BufferedImage flag;
	public final BufferedImage hoveredFlag;
	public final BufferedImage clickedFlag;
	
	public final BufferedImage mine;

	public final BufferedImage background;
	
	public final BufferedImage minesCounter0;
	public final BufferedImage minesCounter1;
	public final BufferedImage minesCounter2;
	public final BufferedImage minesCounter3;
	public final BufferedImage minesCounter4;
	public final BufferedImage minesCounter5;
	public final BufferedImage minesCounter6;
	public final BufferedImage minesCounter7;
	public final BufferedImage minesCounter8;
	
	
	private Backgrounds() {
		
		BufferedImage[] images = new BufferedImage[17];
		for (int i = 0; i < images.length; i++) {
			images[i] = null;
		}
		
		fillImages(images);
		
		field = images[0];
		hoveredField = images[1];
	 	clickedField = images[2];
	 	
	 	flag = images[3];
	 	hoveredFlag = images[4];
	 	clickedFlag = images[5];
	 	
	 	mine = images[6];
	 	
	 	background = images[7];
	 	
	 	minesCounter0 = images[8];
	 	minesCounter1 = images[9];
	 	minesCounter2 = images[10];
	 	minesCounter3 = images[11];
	 	minesCounter4 = images[12];
	 	minesCounter5 = images[13];
	 	minesCounter6 = images[14];
	 	minesCounter7 = images[15];
	 	minesCounter8 = images[16];	
	 }
	
	private BufferedImage[] fillImages(BufferedImage[] images) {
		String sep = File.separator;
		
	 	try {
	 		images[0] = ImageIO.read(new File("img"+sep+"field.jpg"));
	 		images[1] = ImageIO.read(new File("img"+sep+"fieldHovered.jpg"));
	 		images[2] = ImageIO.read(new File("img"+sep+"fieldClicked.jpg"));
		 	
	 		images[3] = ImageIO.read(new File("img"+sep+"flag.jpg"));
	 		images[4] = ImageIO.read(new File("img"+sep+"flagHovered.jpg"));
	 		images[5] = ImageIO.read(new File("img"+sep+"flagClicked.jpg"));
		 	
	 		images[6] = ImageIO.read(new File("img"+sep+"mine.jpg"));
		 	
	 		images[7] = ImageIO.read(new File("img"+sep+"background.jpg"));
		 	
	 		images[8] = ImageIO.read(new File("img"+sep+"0.jpg"));
	 		images[9] = ImageIO.read(new File("img"+sep+"1.jpg"));
	 		images[10] = ImageIO.read(new File("img"+sep+"2.jpg"));
	 		images[11] = ImageIO.read(new File("img"+sep+"3.jpg"));
	 		images[12] = ImageIO.read(new File("img"+sep+"4.jpg"));
	 		images[13] = ImageIO.read(new File("img"+sep+"5.jpg"));
	 		images[14] = ImageIO.read(new File("img"+sep+"6.jpg"));
	 		images[15] = ImageIO.read(new File("img"+sep+"7.jpg"));
	 		images[16] = ImageIO.read(new File("img"+sep+"8.jpg"));
	 	} catch (IOException e) {
			e.printStackTrace();
		}
	 	return images;
	}
	
	
	
	public static Backgrounds getInstace() {
		if (instance == null)
			instance = new Backgrounds();
		return instance;
	}
	
	
}
