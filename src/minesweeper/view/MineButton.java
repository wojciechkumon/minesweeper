package minesweeper.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class MineButton extends JButton {

	private static final long serialVersionUID = 2006845669655854847L;
	public static final int WIDTH = 44;
	public static final int HEIGHT = 44;
	
	private DefaultView view;
	private BufferedImage image;
	private final int x;
	private final int y;
	
	
	
	public MineButton(int x, int y, DefaultView view) {
		this.view = view;
		this.x = x;
		this.y = y;
		
		setSize(WIDTH, HEIGHT);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, this);
	}

	public void resetImage() {
		setImage(Backgrounds.getInstace().field);
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public int getXPostition() {
		return x;
	}
	
	public int getYPostition() {
		return y;
	}
	
	public DefaultView getView() {
		return view;
	}
	public void setView(DefaultView view) {
		this.view = view;
	}

}
