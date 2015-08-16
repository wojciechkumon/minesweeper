package minesweeper.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Backgrounds {

	public final BufferedImage standardField;
	public final BufferedImage hoveredField;
	public final BufferedImage clickedField;
	
	public final BufferedImage standardFlag;
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
	
	
	public Backgrounds() throws IOException {
		String sep = File.separator;
	 	standardField = ImageIO.read(new File("img"+sep+"field.jpg"));
	 	hoveredField = ImageIO.read(new File("img"+sep+"fieldHovered.jpg"));
	 	clickedField = ImageIO.read(new File("img"+sep+"fieldClicked.jpg"));
	 	
	 	standardFlag = ImageIO.read(new File("img"+sep+"flag.jpg"));
	 	hoveredFlag = ImageIO.read(new File("img"+sep+"flagHovered.jpg"));
	 	clickedFlag = ImageIO.read(new File("img"+sep+"flagClicked.jpg"));
	 	
	 	mine = ImageIO.read(new File("img"+sep+"mine.jpg"));
	 	
	 	background = ImageIO.read(new File("img"+sep+"background.jpg"));
	 	
	 	minesCounter0 = ImageIO.read(new File("img"+sep+"0.jpg"));
	 	minesCounter1 = ImageIO.read(new File("img"+sep+"1.jpg"));
	 	minesCounter2 = ImageIO.read(new File("img"+sep+"2.jpg"));
	 	minesCounter3 = ImageIO.read(new File("img"+sep+"3.jpg"));
	 	minesCounter4 = ImageIO.read(new File("img"+sep+"4.jpg"));
	 	minesCounter5 = ImageIO.read(new File("img"+sep+"5.jpg"));
	 	minesCounter6 = ImageIO.read(new File("img"+sep+"6.jpg"));
	 	minesCounter7 = ImageIO.read(new File("img"+sep+"7.jpg"));
	 	minesCounter8 = ImageIO.read(new File("img"+sep+"8.jpg"));
	}


	public BufferedImage getStandardField() {
		return standardField;
	}
	public BufferedImage getHoveredField() {
		return hoveredField;
	}
	public BufferedImage getClickedField() {
		return clickedField;
	}
	public BufferedImage getStandardFlag() {
		return standardFlag;
	}
	public BufferedImage getHoveredFlag() {
		return hoveredFlag;
	}
	public BufferedImage getClickedFlag() {
		return clickedFlag;
	}
	public BufferedImage getMine() {
		return mine;
	}
	public BufferedImage getBackground() {
		return background;
	}
	public BufferedImage getMinesCounter0() {
		return minesCounter0;
	}
	public BufferedImage getMinesCounter1() {
		return minesCounter1;
	}
	public BufferedImage getMinesCounter2() {
		return minesCounter2;
	}
	public BufferedImage getMinesCounter3() {
		return minesCounter3;
	}
	public BufferedImage getMinesCounter4() {
		return minesCounter4;
	}
	public BufferedImage getMinesCounter5() {
		return minesCounter5;
	}
	public BufferedImage getMinesCounter6() {
		return minesCounter6;
	}
	public BufferedImage getMinesCounter7() {
		return minesCounter7;
	}
	public BufferedImage getMinesCounter8() {
		return minesCounter8;
	}
	
	
}
