package minesweeper.view;

import java.io.IOException;

import javax.swing.JFrame;

import minesweeper.controller.Controller;

public class DefaultView extends JFrame implements View {
	private static final long serialVersionUID = -1786179871578950490L;
	
	private Backgrounds bg;
	private MineButton[][] mineBtns;
	private Controller controller;
	
	
	public DefaultView() throws IOException {
		setBg(new Backgrounds());
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public Backgrounds getBg() {
		return bg;
	}


	public void setBg(Backgrounds bg) {
		this.bg = bg;
	}



}
