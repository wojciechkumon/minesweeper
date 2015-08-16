package minesweeper.model;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;

public class DefaultModel extends Model {
	private FieldsBoard fieldsBoard;
	
	public DefaultModel() throws AmountOutOfRange, PointOutOfBoardBounds {
		setFieldsBoard(new FieldsBoard());
	}

	public FieldsBoard getFieldsBoard() {
		return fieldsBoard;
	}

	public void setFieldsBoard(FieldsBoard fieldsBoard) {
		this.fieldsBoard = fieldsBoard;
	}
	
}
