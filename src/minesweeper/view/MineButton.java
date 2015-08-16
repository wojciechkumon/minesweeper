package minesweeper.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class MineButton extends JButton {

	private static final long serialVersionUID = 2006845669655854847L;
	private DefaultView view;
	private BufferedImage image;
	private int x;
	private int y;
	
	
	
	public MineButton(int x, int y, DefaultView view) {
		this.view = view;
		this.x = x;
		this.y = y;
		image = view.getBg().standardField;
	}
	
	public void addController(MouseListener controller) {
		addMouseListener(controller);
	}
	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, this);
	}
	
	
	
	public DefaultView getView() {
		return view;
	}
	public void setView(DefaultView view) {
		this.view = view;
	}

}
