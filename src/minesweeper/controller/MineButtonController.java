package minesweeper.controller;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isMiddleMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;
import static minesweeper.model.Range.isPointInBounds;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import minesweeper.model.ClickState;
import minesweeper.model.Field;
import minesweeper.model.Model;
import minesweeper.view.Backgrounds;
import minesweeper.view.MineButton;

public class MineButtonController implements MouseListener {
	private Controller owner;
	private boolean isGameActive = true;
	
	public MineButtonController(Controller owner) {
		this.owner = owner;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		MineButton mineBtn = (MineButton)e.getSource();
		Field field = owner.getModel().getField(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		owner.getModel().mouseEnteredField(field);
		
		if (mineBtn.getImage() == Backgrounds.getInstace().field) {
			mineBtn.setImage(Backgrounds.getInstace().hoveredField);
		} else if (mineBtn.getImage() == Backgrounds.getInstace().flag) {
			mineBtn.setImage(Backgrounds.getInstace().hoveredFlag);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		Field field = owner.getModel().getField(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		owner.getModel().mouseExitedField(field);

		if (mineBtn.getImage() == Backgrounds.getInstace().hoveredField) {
			mineBtn.setImage(Backgrounds.getInstace().field);
		} else if (mineBtn.getImage() == Backgrounds.getInstace().hoveredFlag) {
			mineBtn.setImage(Backgrounds.getInstace().flag);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		Field field = owner.getModel().getField(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		if (isMiddleMouseButton(e))
			owner.getModel().mouseMiddleButtonPressedField(field);
		else
			owner.getModel().mouseButtonPressedField(field);
		
		
		
		if (!isFieldPressed(mineBtn) && isMiddleMouseButton(e)) {
			mousePressedMiddleButton(e);
		}
	}
	
	private boolean isFieldPressed(MineButton mineBtn) {
		boolean isDone = false;
		if (mineBtn.getImage() == Backgrounds.getInstace().hoveredField) {
			mineBtn.setImage(Backgrounds.getInstace().clickedField);
			isDone = true;
		} else if (mineBtn.getImage() == Backgrounds.getInstace().hoveredFlag) {
			mineBtn.setImage(Backgrounds.getInstace().clickedFlag);
			isDone = true;
		} 
		return isDone;
	}
	
	private void pressField(MineButton mineBtn) {
		if (mineBtn.getImage() == Backgrounds.getInstace().field) {
			mineBtn.setImage(Backgrounds.getInstace().clickedField);
		} 
	}
	
	private void mousePressedMiddleButton(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		
		if (isGameActive && isFieldToAreaReveal(mineBtn.getImage())) {
			Model model = owner.getModel();
			Field field = model.getField(mineBtn.getXPostition(), mineBtn.getYPostition());
			Field[] surroundingFields = field.getSurroundingFields();
			
			for (Field fld: surroundingFields) {
				if (fld.getClickState() == ClickState.NOT_CLICKED) {
					System.out.println("not_clicked");
					pressField(mineBtn.getView().getMineButton(fld.getX(), fld.getY()));
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MineButton mineBtn = (MineButton)e.getSource();
		Field field = owner.getModel().getField(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		
		if (isLeftMouseButton(e)) {
			owner.getModel().mouseLeftButtonReleasedField(field);
			mouseReleasedLeftBtn(e);
		} else if (isRightMouseButton(e)) {
			owner.getModel().mouseRightButtonReleasedField(field);
			mouseReleasedRightBtn(e);
		} else if (isMiddleMouseButton(e)) {
			owner.getModel().mouseMiddleButtonReleasedField(field);
			mouseReleasedMiddleBtn(e);
		}
	}

	private void mouseReleasedLeftBtn(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		BufferedImage btnImg = mineBtn.getImage();
		Backgrounds bg = Backgrounds.getInstace();
		Model model = owner.getModel();
		
		if (btnImg == bg.clickedField) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT) && isGameActive) {
				model.checkField(model.getField(mineBtn.getXPostition(), mineBtn.getYPostition()));
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
		Model model = owner.getModel();
		
		if ((btnImg == bg.field) || (btnImg == bg.hoveredField) || (btnImg == bg.clickedField)) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT) && isGameActive) {
				model.changeToFlag(model.getField(mineBtn.getXPostition(), mineBtn.getYPostition()));
			} else {
				mineBtn.setImage(bg.field);
			}
		} else if ((btnImg == bg.flag) || (btnImg == bg.hoveredFlag) || (btnImg == bg.clickedFlag)) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT) && isGameActive) {
				model.removeFlag(model.getField(mineBtn.getXPostition(), mineBtn.getYPostition()));
			} else {
				mineBtn.setImage(bg.flag);
			}
		}
	}

	private void mouseReleasedMiddleBtn(MouseEvent e) {
		MineButton mineBtn = (MineButton) e.getSource();
		BufferedImage btnImg = mineBtn.getImage();
		Model model = owner.getModel();
		
		if (isFieldToAreaReveal(btnImg)) {
			if (isPointInBounds(e.getPoint(), 0, 0, MineButton.WIDTH, MineButton.HEIGHT)
					&& model.isAreaRevealPossible(model.getField(mineBtn.getXPostition(), mineBtn.getYPostition()))) {
				model.doAreaReveal(model.getField(mineBtn.getXPostition(), mineBtn.getYPostition()));
			} else {
				revertMiddleBtn(e);
			}
		} else 
			mouseReleasedLeftBtn(e);
	}
	
	private void revertMiddleBtn(MouseEvent e) {
		MineButton mineBtn = (MineButton)e.getSource();
		Model model = owner.getModel();
		Backgrounds bg = Backgrounds.getInstace();
		Field field = model.getField(mineBtn.getXPostition(), mineBtn.getYPostition());
		Field[] surroundingFields = field.getSurroundingFields();
		MineButton[] mineButtons = new MineButton[surroundingFields.length];

		for (int i = 0; i < surroundingFields.length; i++) {
			mineButtons[i] = mineBtn.getView().getMineButton(surroundingFields[i].getX(), surroundingFields[i].getY());
		}
		
		for (MineButton mnBtn: mineButtons) {
			if (mnBtn.getImage() == bg.clickedField) {
				Point p = mnBtn.getLocationOnScreen();
				System.out.println("getP "+e.getLocationOnScreen());
				System.out.println("X: "+p.getX()+" Y: "+p.getY());
				if (isPointInBounds(e.getLocationOnScreen(), p.getX(), p.getY(), p.getX() + MineButton.WIDTH,
						p.getY() + MineButton.HEIGHT)) {
					mnBtn.setImage(bg.hoveredField);
				} else
					mnBtn.setImage(bg.field);
			}
		}
	}
	
	
	private boolean isFieldToAreaReveal(BufferedImage img) {
		Backgrounds bg = Backgrounds.getInstace();
		if (img == bg.minesCounter1 || img == bg.minesCounter2 || img == bg.minesCounter3 || img == bg.minesCounter4 ||
				img == bg.minesCounter5 || img == bg.minesCounter6 || img == bg.minesCounter7 || img == bg.minesCounter8)
			return true;
		return false;
	}

	public boolean isGameActive() {
		return isGameActive;
	}

	public void setGameActive(boolean isGameActive) {
		this.isGameActive = isGameActive;
	}


}
