package minesweeper.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButtonController implements ActionListener {
	private Controller owner;
	
	public RestartButtonController(Controller owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		owner.getModel().restartGame();
	}

}
