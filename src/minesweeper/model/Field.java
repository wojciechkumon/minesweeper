package minesweeper.model;

import minesweeper.exceptions.PointOutOfBoardBounds;

public class Field {
	private final int x;
	private final int y;
	private MineState mineState;
	private FieldsBoard owner;
	private ClickState clickState = ClickState.NOT_CLICKED;
	
	
	public Field(int x, int y, FieldsBoard owner) throws PointOutOfBoardBounds {
		if (x < 0 || x >= owner.getHeight() || y < 0 || y >= owner.getWidth())
			throw new PointOutOfBoardBounds("Mine coordinates out of bounds exception");
			
		this.x = x;
		this.y = y;
		this.owner = owner;
		setState(MineState.SURROUNDING_0);
	}


	public void calculateState() {
		if (getState() != MineState.SURROUNDING_0) {
			return;
		}
		mineState = MineState.getMineStateByInt(countSourroundingMines(getPosition()));	
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
	
	private int countSourroundingMines(Position position) {
		switch (position) {
		case CENTER:
			return countSourroundingMinesCenter();
		case TOP:
			return countSourroundingMinesTop();
		case BOTTOM:
			return countSourroundingMinesBottom();
		case LEFT_SIDE:
			return countSourroundingMinesLeftSide();
		case RIGHT_SIDE:
			return countSourroundingMinesRightSide();
		case LEFT_UPPER_CORNER:
			return countSourroundingMinesLeftUpperCorner();
		case RIGHT_UPPER_CORNER:
			return countSourroundingMinesRightUpperCorner();
		case RIGHT_LOWER_CORNER:
			return countSourroundingMinesRightLowerCorner();
		default:
			return countSourroundingMinesLeftLowerCorner();
		}
	}
	
	private int countSourroundingMinesCenter() {
		int sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1 + i, y - 1).getState() == MineState.MINE)
				sumOfMines++;
			if (owner.get(x - 1 + i, y + 1).getState() == MineState.MINE)
				sumOfMines++;
		}
		if (owner.get(x - 1, y).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == MineState.MINE)
			sumOfMines++;
	
		return sumOfMines;
	}


	private int countSourroundingMinesTop() {
		int sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1 + i, y + 1).getState() == MineState.MINE)
				sumOfMines++;
		}
		if (owner.get(x - 1, y).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == MineState.MINE)
			sumOfMines++;
	
		return sumOfMines;
	}


	private int countSourroundingMinesBottom() {
		int sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1 + i, y - 1).getState() == MineState.MINE)
				sumOfMines++;
		}
		if (owner.get(x - 1, y).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == MineState.MINE)
			sumOfMines++;
	
		return sumOfMines;
	}


	private int countSourroundingMinesLeftSide() {
		int sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x + 1, y - 1 + i).getState() == MineState.MINE)
				sumOfMines++;
		}
		if (owner.get(x, y - 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == MineState.MINE)
			sumOfMines++;
	
		return sumOfMines;
	}


	private int countSourroundingMinesRightSide() {
		int sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (owner.get(x - 1, y - 1 + i).getState() == MineState.MINE)
				sumOfMines++;
		}
		if (owner.get(x, y - 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == MineState.MINE)
			sumOfMines++;
	
		return sumOfMines;
	}


	private int countSourroundingMinesLeftUpperCorner() {
		int sumOfMines = 0;
		
		if (owner.get(x + 1, y).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x + 1, y + 1).getState() == MineState.MINE)
			sumOfMines++;

		return sumOfMines;
	}


	private int countSourroundingMinesRightUpperCorner() {
		int sumOfMines = 0;

		if (owner.get(x - 1, y).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x - 1, y + 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x, y + 1).getState() == MineState.MINE)
			sumOfMines++;

		return sumOfMines;
	}


	private int countSourroundingMinesRightLowerCorner() {
		int sumOfMines = 0;

		if (owner.get(x - 1, y - 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x, y - 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x - 1, y).getState() == MineState.MINE)
			sumOfMines++;

		return sumOfMines;
	}


	private int countSourroundingMinesLeftLowerCorner() {
		int sumOfMines = 0;

		if (owner.get(x, y - 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x + 1, y - 1).getState() == MineState.MINE)
			sumOfMines++;
		if (owner.get(x + 1, y).getState() == MineState.MINE)
			sumOfMines++;

		return sumOfMines;
	}


	
	
	public int getY() {
		return y;
	}

	public int getX() {
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
