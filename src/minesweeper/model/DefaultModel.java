package minesweeper.model;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;
import minesweeper.view.MineButton;

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
	
}
