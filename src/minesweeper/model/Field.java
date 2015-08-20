package minesweeper.model;

import static minesweeper.model.StateCalculator.countSourroundingClickStates;
import static minesweeper.model.StateCalculator.countSourroundingMineStates;

import minesweeper.exceptions.PointOutOfBoardBounds;

public class Field {
	private final short x;
	private final short y;
	private MineState mineState = MineState.SURROUNDING_0;
	private FieldsBoard owner;
	private ClickState clickState = ClickState.NOT_CLICKED;

	public Field(int x, int y, FieldsBoard owner) throws PointOutOfBoardBounds {
		if (x < 0 || x >= owner.getVerticalMines() || y < 0 || y >= owner.getHorizontalMines())
			throw new PointOutOfBoardBounds(
					"Mine coordinates out of bounds exception");

		this.x = (short) x;
		this.y = (short) y;
		this.owner = owner;
	}

	public void calculateState() {
		if (getMineState() != MineState.SURROUNDING_0) {
			return;
		}
		mineState = MineState.getMineStateByInt(countSourroundingMineStates(
				this, MineState.MINE, getFieldPosition()));
	}

	public boolean isAreaClickPossible() {
		int mineStateInt = mineState.getSurroundingMines();
		if (clickState != ClickState.CLICKED || mineStateInt == 9)
			return false;

		if (countSourroundingClickStates(this, ClickState.FLAG,
				getFieldPosition()) == mineStateInt)
			return true;
		
		return false;
	}
	
	public void clickField() {
		clickState = ClickState.CLICKED;
	}
	
	public boolean isMine() {
		if (mineState == MineState.MINE)
			return true;
		return false;
	}
	

	public Position getFieldPosition() {
		if (x > 0) {
			if (x < owner.getVerticalMines() - 1) {
				if (y > 0) {
					if (y < owner.getHorizontalMines() - 1) {
						return Position.CENTER;
					} else
						return Position.BOTTOM;
				} else {
					return Position.TOP;
				}
			} else {
				if (y > 0) {
					if (y < owner.getHorizontalMines() - 1) {
						return Position.RIGHT_SIDE;
					} else
						return Position.RIGHT_LOWER_CORNER;
				} else {
					return Position.RIGHT_UPPER_CORNER;
				}
			}		
		} else {
			if (y > 0) {
				if (y < owner.getHorizontalMines() - 1) {
					return Position.LEFT_SIDE;
				} else
					return Position.LEFT_LOWER_CORNER;
			} else {
				return Position.LEFT_UPPER_CORNER;
			}
		}
	}
	
	public Field[] getSurroundingFields() {
		return StateCalculator.getSurroundingFields(this);
	}
	
	
	public short getY() {
		return y;
	}

	public short getX() {
		return x;
	}

	public ClickState getClickState() {
		return clickState;
	}

	public void setClickState(ClickState clickState) {
		this.clickState = clickState;
	}

	public MineState getMineState() {
		return mineState;
	}

	public void setMineState(MineState mineState) {
		this.mineState = mineState;
	}

	public FieldsBoard getOwner() {
		return owner;
	}


}
