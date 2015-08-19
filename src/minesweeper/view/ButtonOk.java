package minesweeper.view;

import javax.swing.JButton;

public class ButtonOk extends JButton {
	private static final long serialVersionUID = 2971814209438995667L;
	private SetGameDialog owner;
	
	public ButtonOk(SetGameDialog owner, String text) {
		super(text);
		this.owner = owner;
	}
	
	public SetGameDialog getOwner() {
		return owner;
	}
}
