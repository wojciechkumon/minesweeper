package minesweeper.controller;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isMiddleMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;
import static minesweeper.model.Range.isPointInBounds;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import minesweeper.view.Backgrounds;
import minesweeper.view.MineButton;

public class MineButtonController implements MouseListener {
	private Controller owner;
	
	public MineButtonController(Controller owner) {
		this.owner = owner;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		MineButton mineBtn = (MineButton)e.getSource();
		
		if (mineBtn.getImage() == Backgrounds.getInstace().field) {
			mineBtn.setImage(Backgrounds.getInstace().hoveredField);
		} else if (mineBtn.getImage() == Backgrounds.getInstace().flag) {
			mineBtn.setImage(Backgrounds.getInstace().hoveredFlag);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();

		if (mineBtn.getImage() == Backgrounds.getInstace().hoveredField) {
			mineBtn.setImage(Backgrounds.getInstace().field);
		} else if (mineBtn.getImage() == Backgrounds.getInstace().hoveredFlag) {
			mineBtn.setImage(Backgrounds.getInstace().flag);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();

		if (mineBtn.getImage() == Backgrounds.getInstace().hoveredField) {
			mineBtn.setImage(Backgrounds.getInstace().clickedField);
		} else if (mineBtn.getImage() == Backgrounds.getInstace().hoveredFlag) {
			mineBtn.setImage(Backgrounds.getInstace().clickedFlag);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (isLeftMouseButton(e)) {
			mouseReleasedLeftBtn(e);
		} else if (isRightMouseButton(e)) {
			mouseReleasedRightBtn(e);
		} else if (isMiddleMouseButton(e)) {
			mouseReleasedMiddleBtn(e);
		}
	}

	private void mouseReleasedLeftBtn(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		BufferedImage btnImg = mineBtn.getImage();
		Backgrounds bg = Backgrounds.getInstace();
		
		if (btnImg == bg.clickedField) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT)) {
				owner.getModel().checkField(mineBtn);
			} else {
				mineBtn.setImage(bg.field);
			}
		} else if (btnImg == bg.clickedFlag) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT)) {
				mineBtn.setImage(bg.hoveredFlag);
			} else {
				mineBtn.setImage(bg.flag);
			}
		}
	}

	private void mouseReleasedRightBtn(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		BufferedImage btnImg = mineBtn.getImage();
		Backgrounds bg = Backgrounds.getInstace();
		
		if ((btnImg == bg.field) || (btnImg == bg.hoveredField) || (btnImg == bg.clickedField)) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT)) {
				owner.getModel().changeToFlag(mineBtn);
			} else {
				mineBtn.setImage(bg.field);
			}
		} else if ((btnImg == bg.flag) || (btnImg == bg.hoveredFlag) || (btnImg == bg.clickedFlag)) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT)) {
				owner.getModel().removeFlag(mineBtn);
			} else {
				mineBtn.setImage(bg.flag);
			}
		}
	}

	private void mouseReleasedMiddleBtn(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		BufferedImage btnImg = mineBtn.getImage();
		Backgrounds bg = Backgrounds.getInstace();
		//TODO
		if (owner.getModel().isAreaRevealPossible(mineBtn)) {
			owner.getModel().doAreaReveal(mineBtn);
		}
	}


}
