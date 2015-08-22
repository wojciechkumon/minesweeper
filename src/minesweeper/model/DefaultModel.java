package minesweeper.model;

import java.util.Observer;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;

public class DefaultModel extends Model {
	private FieldsBoard fieldsBoard;
	
	public DefaultModel() throws AmountOutOfRange, PointOutOfBoardBounds {
		fieldsBoard = new FieldsBoard(this);
	}

	public FieldsBoard getFieldsBoard() {
		return fieldsBoard;
	}


	@Override
	public void changeToFlag(Field field) {
		fieldsBoard.changeToFlag(field);
	}

	@Override
	public void removeFlag(Field field) {
		fieldsBoard.removeFlag(field);
	}

	@Override
	public void checkField(Field field) {
		fieldsBoard.checkField(field);
	}

	@Override
	public boolean isAreaRevealPossible(Field field) {
		return fieldsBoard.isAreaRevealPossible(field);
	}

	@Override
	public void doAreaReveal(Field field) {
		fieldsBoard.doAreaReveal(field);
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

	@Override
	public Field getField(int x, int y) {
		return fieldsBoard.get(x, y);
	}

	@Override
	public void mouseEnteredField(Field field) {
		fieldsBoard.updateFieldView(field);
	}

	@Override
	public void mouseExitedField(Field field) {
		fieldsBoard.updateFieldView(field);
	}

	@Override
	public void mouseMiddleButtonPressedField(Field field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseButtonPressedField(Field field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseLeftButtonReleasedField(Field field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseRightButtonReleasedField(Field field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMiddleButtonReleasedField(Field field) {
		// TODO Auto-generated method stub
		
	}

	
}
