package minesweeper.model;

import java.util.Observer;

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
	public void restartGame() {
		fieldsBoard.restartGame();
	}

	@Override
	public void restartGame(int horizontalMines, int verticalMines, int mines) {
		try {
			fieldsBoard.restartGame(horizontalMines, verticalMines, mines);
		} catch (AmountOutOfRange | PointOutOfBoardBounds e) {
			e.printStackTrace();
		}
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
		return fieldsBoard.getVerticalMines();
	}

	@Override
	public int getNumberOfMines() {
		return fieldsBoard.getMinesToSet();
	}
	
	@Override
	public void addObserver(Observer o) {
		super.addObserver(o);
		UpdateBox updateBox = new UpdateBox();
		updateBox.setNeededRestart(true);
		setChanged();
		notifyObservers(updateBox);
	}

	@Override
	public int getMinHorizontalFields() {
		return FieldsBoard.MIN_HORIZONTAL_FIELDS;
	}

	@Override
	public int getMaxHorizontalFields() {
		return FieldsBoard.MAX_HORIZONTAL_FIELDS;
	}

	@Override
	public int getMinVerticalFields() {
		return FieldsBoard.MIN_VERTICAL_FIELDS;
	}

	@Override
	public int getMaxVerticalFields() {
		return FieldsBoard.MAX_VERTICAL_FIELDS;
	}

	@Override
	public int getMinAmountOfMines() {
		return FieldsBoard.MIN_AMOUNT_OF_MINES;
	}

	@Override
	public int getMaxAmountOfMines() {
		return FieldsBoard.MAX_AMOUNT_OF_MINES;
	}

	
}
