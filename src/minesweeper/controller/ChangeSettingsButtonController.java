package minesweeper.controller;

import static minesweeper.model.Range.isInRange;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import minesweeper.model.Model;
import minesweeper.view.ButtonOk;
import minesweeper.view.SetGameDialog;

public class ChangeSettingsButtonController implements ActionListener {
	private Controller owner;

	public ChangeSettingsButtonController(Controller owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isOk = true;
		
		SetGameDialog dialog = ((ButtonOk)e.getSource()).getOwner();
		
		int horizontalFields = 30;
		int verticalFields = 16;
		int mines = 99;
		
		try {
			horizontalFields = Integer.parseInt(dialog.getHorizontalFields());
			verticalFields = Integer.parseInt(dialog.getVerticalFields());
			mines = Integer.parseInt(dialog.getMines());
		} catch (NumberFormatException ex) {
			isOk = false;
		} finally {
			Model model = owner.getModel();
			if (isOk && isInRange(horizontalFields, model.getMinHorizontalFields(), model.getMaxHorizontalFields())
					&& isInRange(verticalFields, model.getMinVerticalFields(), model.getMaxVerticalFields())
					&& isInRange(mines, model.getMinAmountOfMines(), model.getMaxAmountOfMines())) {

				owner.getModel().restartGame(horizontalFields, verticalFields, mines);
				dialog.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(dialog, "Wrong data!", "ERROR", WindowConstants.EXIT_ON_CLOSE);
				dialog.showDialog();
			}
			
			
		}
	}

}
