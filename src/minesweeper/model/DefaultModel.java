package minesweeper.model;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;
import minesweeper.view.MineButton;

public class DefaultModel extends Model {
	private FieldsBoard fieldsBoard;
	
	public DefaultModel() throws AmountOutOfRange, PointOutOfBoardBounds {
		fieldsBoard = new FieldsBoard(this);
	}

	public FieldsBoard getFieldsBoard() {
		return fieldsBoard;
	}


	@Override
	public void changeToFlag(MineButton mineBtn) {
		fieldsBoard.changeToFlag(mineBtn);
	}

	@Override
	public void removeFlag(MineButton mineBtn) {
		fieldsBoard.removeFlag(mineBtn);
	}

	@Override
	public void checkField(MineButton mineBtn) {
		fieldsBoard.checkField(mineBtn);
	}

	@Override
	public boolean isAreaRevealPossible(MineButton mineBtn) {
		return fieldsBoard.isAreaRevealPossible(mineBtn);
	}

	@Override
	public void doAreaReveal(MineButton mineBtn) {
		fieldsBoard.doAreaReveal(mineBtn);
	}
	
	@Override
	public void setChanges() {
		setChanged();
	}

	@Override
	public int getMinesLeft() {
		return fieldsBoard.getMinesLeft();
	}

	@Override
	public int getHorizontalNumberOfMines() {
		return fieldsBoard.getHorizontalMines();
	}

	@Override
	public int getVerticalNumberOfMines() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfMines() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
