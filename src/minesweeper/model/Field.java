package minesweeper.model;

import minesweeper.exceptions.PointOutOfBoardBounds;

public class Field {
	private final short x;
	private final short y;
	private MineState mineState = MineState.SURROUNDING_0;
	private FieldsBoard owner;
	private ClickState clickState = ClickState.NOT_CLICKED;
	
	
	public Field(int x, int y, FieldsBoard owner) throws PointOutOfBoardBounds {
		if (x < 0 || x >= owner.getHeight() || y < 0 || y >= owner.getWidth())
			throw new PointOutOfBoardBounds("Mine coordinates out of bounds exception");
			
		this.x = (short)x;
		this.y = (short)y;
		this.owner = owner;
	}
	


	public void calculateState() {
		if (getState() != MineState.SURROUNDING_0) {
			return;
		}
		mineState = MineState.getMineStateByInt(countSourroundingMineStates(MineState.MINE, getPosition()));	
	}

	private Position getPosition() {
		if (x > 0) {
			if (x < owner.getHeight() - 1) {
				if (y > 0) {
					if (y < owner.getWidth() - 1) {
						return Position.CENTER;
					} else
						return Position.BOTTOM;
				} else {
					return Position.TOP;
				}
			} else {
				if (y > 0) {
					if (y < owner.getWidth() - 1) {
						return Position.RIGHT_SIDE;
					} else
						return Position.RIGHT_LOWER_CORNER;
				} else {
					return Position.RIGHT_UPPER_CORNER;
				}
			}		
		} else {
			if (y > 0) {
				if (y < owner.getWidth() - 1) {
					return Position.LEFT_SIDE;
				} else
					return Position.LEFT_LOWER_CORNER;
			} else {
				return Position.LEFT_UPPER_CORNER;
			}
		}
	}
	
	private byte countSourroundingMineStates(MineState mineState, Position position) {
		switch (position) {
		case CENTER:
			return countSourroundingMineStatesCenter(mineState);
		case TOP:
			return countSourroundingMineStatesTop(mineState);
		case BOTTOM:
			return countSourroundingMineStatesBottom(mineState);
		case LEFT_SIDE:
			return countSourroundingMineStatesLeftSide(mineState);
		case RIGHT_SIDE:
			return countSourroundingMineStatesRightSide(mineState);
		case LEFT_UPPER_CORNER:
			return countSourroundingMineStatesLeftUpperCorner(mineState);
		case RIGHT_UPPER_CORNER:
			return countSourroundingMineStatesRightUpperCorner(mineState);
		case RIGHT_LOWER_CORNER:
			return countSourroundingMineStatesRightLowerCorner(mineState);
		default:
			return countSourroundingMineStatesLeftLowerCorner(mineState);
		}
	}
	
	private byte countSourroundingMineStatesCenter(MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1 + i, y - 1).getState() == mineState)
				sumOfMines++;
			if (owner.get(x - 1 + i, y + 1).getState() == mineState)
				sumOfMines++;
		}
		if (owner.get(x - 1, y).getState() == mineState)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private byte countSourroundingMineStatesTop(MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1 + i, y + 1).getState() == mineState)
				sumOfMines++;
		}
		if (owner.get(x - 1, y).getState() == mineState)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private byte countSourroundingMineStatesBottom(MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1 + i, y - 1).getState() == mineState)
				sumOfMines++;
		}
		if (owner.get(x - 1, y).getState() == mineState)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private byte countSourroundingMineStatesLeftSide(MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x + 1, y - 1 + i).getState() == mineState)
				sumOfMines++;
		}
		if (owner.get(x, y - 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private byte countSourroundingMineStatesRightSide(MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1, y - 1 + i).getState() == mineState)
				sumOfMines++;
		}
		if (owner.get(x, y - 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private byte countSourroundingMineStatesLeftUpperCorner(MineState mineState) {
		byte sumOfMines = 0;
		
		if (owner.get(x + 1, y).getState() == mineState)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x + 1, y + 1).getState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	private byte countSourroundingMineStatesRightUpperCorner(MineState mineState) {
		byte sumOfMines = 0;

		if (owner.get(x - 1, y).getState() == mineState)
			sumOfMines++;
		if (owner.get(x - 1, y + 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	private byte countSourroundingMineStatesRightLowerCorner(MineState mineState) {
		byte sumOfMines = 0;

		if (owner.get(x - 1, y - 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x, y - 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x - 1, y).getState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	private byte countSourroundingMineStatesLeftLowerCorner(MineState mineState) {
		byte sumOfMines = 0;

		if (owner.get(x, y - 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x + 1, y - 1).getState() == mineState)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	
	
	public short getY() {
		return y;
	}

	public short getX() {
		return x;
	}

	public MineState getState() {
		return mineState;
	}

	public void setState(MineState mineState) {
		this.mineState = mineState;
	}

	public ClickState getClickState() {
		return clickState;
	}

	public void setClickState(ClickState clickState) {
		this.clickState = clickState;
	}
	


}
