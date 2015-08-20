package minesweeper.model;

public class StateCalculator {
	
	
	public static Field[] getSurroundingFields(Field field) {
		Position position = field.getFieldPosition();
		switch (position) {
		case CENTER:
			return getSurroundingFieldsCenter(field);
		case TOP:
			return getSurroundingFieldsTop(field);
		case BOTTOM:
			return getSurroundingFieldsBottom(field);
		case LEFT_SIDE:
			return getSurroundingFieldsLeftSide(field);
		case RIGHT_SIDE:
			return getSurroundingFieldsRightSide(field);
		case LEFT_UPPER_CORNER:
			return getSurroundingFieldsLeftUpperCorner(field);
		case RIGHT_UPPER_CORNER:
			return getSurroundingFieldsRightUpperCorner(field);
		case RIGHT_LOWER_CORNER:
			return getSurroundingFieldsRightLowerCorner(field);
		default:
			return getSurroundingFieldsLeftLowerCorner(field);
		}
	}
	
	private static Field[] getSurroundingFieldsCenter(Field field) {
		Field[] fields = new Field[8];
		
		for (int i = 0; i < 3; i++) {
			fields[i] = field.getOwner().get(field.getX() - 1 + i, field.getY() - 1);
			fields[i + 3] = field.getOwner().get(field.getX() - 1 + i, field.getY() + 1);
		}
		fields[6] = field.getOwner().get(field.getX() - 1, field.getY());
		fields[7] = field.getOwner().get(field.getX() + 1, field.getY());
		
		return fields;
	}

	private static Field[] getSurroundingFieldsTop(Field field) {
		Field[] fields = new Field[5];
		
		for (int i = 0; i < 3; i++) {
			fields[i] = field.getOwner().get(field.getX() - 1 + i, field.getY() + 1);
		}
		
		fields[3] = field.getOwner().get(field.getX() - 1, field.getY());
		fields[4] = field.getOwner().get(field.getX() + 1, field.getY());
		
		return fields;
	}

	private static Field[] getSurroundingFieldsBottom(Field field) {
		Field[] fields = new Field[5];
		
		for (int i = 0; i < 3; i++) {
			fields[i] = field.getOwner().get(field.getX() - 1 + i, field.getY() - 1);
		}
		
		fields[3] = field.getOwner().get(field.getX() - 1, field.getY());
		fields[4] = field.getOwner().get(field.getX() + 1, field.getY());
		
		return fields;
	}

	private static Field[] getSurroundingFieldsLeftSide(Field field) {
		Field[] fields = new Field[5];
		
		for (int i = 0; i < 3; i++) {
			fields[i] = field.getOwner().get(field.getX() + 1, field.getY() - 1 + i);
		}
		
		fields[3] = field.getOwner().get(field.getX(), field.getY() - 1);
		fields[4] = field.getOwner().get(field.getX(), field.getY() + 1);
		
		return fields;
	}

	private static Field[] getSurroundingFieldsRightSide(Field field) {
		Field[] fields = new Field[5];
		
		for (int i = 0; i < 3; i++) {
			fields[i] = field.getOwner().get(field.getX() - 1, field.getY() - 1 + i);
		}
		
		fields[3] = field.getOwner().get(field.getX(), field.getY() - 1);
		fields[4] = field.getOwner().get(field.getX(), field.getY() + 1);
		
		return fields;
	}

	private static Field[] getSurroundingFieldsLeftUpperCorner(Field field) {
		Field[] fields = new Field[3];
		
		fields[0] = field.getOwner().get(field.getX() + 1, field.getY());
		fields[1] = field.getOwner().get(field.getX(), field.getY() + 1);
		fields[2] = field.getOwner().get(field.getX() + 1, field.getY() + 1);
		
		return fields;
	}

	private static Field[] getSurroundingFieldsRightUpperCorner(Field field) {
		Field[] fields = new Field[3];
		
		fields[0] = field.getOwner().get(field.getX() - 1, field.getY());
		fields[1] = field.getOwner().get(field.getX() - 1, field.getY() + 1);
		fields[2] = field.getOwner().get(field.getX(), field.getY() + 1);
		
		return fields;
	}

	private static Field[] getSurroundingFieldsRightLowerCorner(Field field) {
		Field[] fields = new Field[3];
		
		fields[0] = field.getOwner().get(field.getX() - 1, field.getY() - 1);
		fields[1] = field.getOwner().get(field.getX(), field.getY() - 1);
		fields[2] = field.getOwner().get(field.getX() - 1, field.getY());
		
		return fields;
	}

	private static Field[] getSurroundingFieldsLeftLowerCorner(Field field) {
		Field[] fields = new Field[3];
		
		fields[0] = field.getOwner().get(field.getX(), field.getY() - 1);
		fields[1] = field.getOwner().get(field.getX() + 1, field.getY() - 1);
		fields[2] = field.getOwner().get(field.getX() + 1, field.getY());
		
		return fields;
	}

	public static byte countSourroundingClickStates(Field field, ClickState clickState, Position position) {
		switch (position) {
		case CENTER:
			return countSourroundingClickStatesCenter(field, clickState);
		case TOP:
			return countSourroundingClickStatesTop(field, clickState);
		case BOTTOM:
			return countSourroundingClickStatesBottom(field, clickState);
		case LEFT_SIDE:
			return countSourroundingClickStatesLeftSide(field, clickState);
		case RIGHT_SIDE:
			return countSourroundingClickStatesRightSide(field, clickState);
		case LEFT_UPPER_CORNER:
			return countSourroundingClickStatesLeftUpperCorner(field, clickState);
		case RIGHT_UPPER_CORNER:
			return countSourroundingClickStatesRightUpperCorner(field, clickState);
		case RIGHT_LOWER_CORNER:
			return countSourroundingClickStatesRightLowerCorner(field, clickState);
		default:
			return countSourroundingClickStatesLeftLowerCorner(field, clickState);
		}
	}
	
	private static byte countSourroundingClickStatesCenter(Field field, ClickState clickState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() - 1).getClickState() == clickState)
				sumOfMines++;
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() + 1).getClickState() == clickState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX() - 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingClickStatesTop(Field field, ClickState clickState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() + 1).getClickState() == clickState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX() - 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingClickStatesBottom(Field field, ClickState clickState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() - 1).getClickState() == clickState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX() - 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingClickStatesLeftSide(Field field, ClickState clickState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() + 1, field.getY() - 1 + i).getClickState() == clickState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX(), field.getY() - 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getClickState() == clickState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingClickStatesRightSide(Field field, ClickState clickState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1, field.getY() - 1 + i).getClickState() == clickState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX(), field.getY() - 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getClickState() == clickState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingClickStatesLeftUpperCorner(Field field, ClickState clickState) {
		byte sumOfMines = 0;
		
		if (field.getOwner().get(field.getX() + 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY() + 1).getClickState() == clickState)
			sumOfMines++;

		return sumOfMines;
	}


	private static byte countSourroundingClickStatesRightUpperCorner(Field field, ClickState clickState) {
		byte sumOfMines = 0;

		if (field.getOwner().get(field.getX() - 1, field.getY()).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() - 1, field.getY() + 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getClickState() == clickState)
			sumOfMines++;

		return sumOfMines;
	}


	private static byte countSourroundingClickStatesRightLowerCorner(Field field, ClickState clickState) {
		byte sumOfMines = 0;

		if (field.getOwner().get(field.getX() - 1, field.getY() - 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() - 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() - 1, field.getY()).getClickState() == clickState)
			sumOfMines++;

		return sumOfMines;
	}


	private static byte countSourroundingClickStatesLeftLowerCorner(Field field, ClickState clickState) {
		byte sumOfMines = 0;

		if (field.getOwner().get(field.getX(), field.getY() - 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY() - 1).getClickState() == clickState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getClickState() == clickState)
			sumOfMines++;

		return sumOfMines;
	}
	
	public static byte countSourroundingMineStates(Field field, MineState mineState, Position position) {
		switch (position) {
		case CENTER:
			return countSourroundingMineStatesCenter(field, mineState);
		case TOP:
			return countSourroundingMineStatesTop(field, mineState);
		case BOTTOM:
			return countSourroundingMineStatesBottom(field, mineState);
		case LEFT_SIDE:
			return countSourroundingMineStatesLeftSide(field, mineState);
		case RIGHT_SIDE:
			return countSourroundingMineStatesRightSide(field, mineState);
		case LEFT_UPPER_CORNER:
			return countSourroundingMineStatesLeftUpperCorner(field, mineState);
		case RIGHT_UPPER_CORNER:
			return countSourroundingMineStatesRightUpperCorner(field, mineState);
		case RIGHT_LOWER_CORNER:
			return countSourroundingMineStatesRightLowerCorner(field, mineState);
		default:
			return countSourroundingMineStatesLeftLowerCorner(field, mineState);
		}
	}
	
	private static byte countSourroundingMineStatesCenter(Field field, MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() - 1).getMineState() == mineState)
				sumOfMines++;
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() + 1).getMineState() == mineState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX() - 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingMineStatesTop(Field field, MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() + 1).getMineState() == mineState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX() - 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingMineStatesBottom(Field field, MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1 + i, field.getY() - 1).getMineState() == mineState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX() - 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingMineStatesLeftSide(Field field, MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() + 1, field.getY() - 1 + i).getMineState() == mineState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX(), field.getY() - 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getMineState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingMineStatesRightSide(Field field, MineState mineState) {
		byte sumOfMines = 0;
		for (int i = 0; i < 3; i++) {
			if (field.getOwner().get(field.getX() - 1, field.getY() - 1 + i).getMineState() == mineState)
				sumOfMines++;
		}
		if (field.getOwner().get(field.getX(), field.getY() - 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getMineState() == mineState)
			sumOfMines++;
	
		return sumOfMines;
	}


	private static byte countSourroundingMineStatesLeftUpperCorner(Field field, MineState mineState) {
		byte sumOfMines = 0;
		
		if (field.getOwner().get(field.getX() + 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY() + 1).getMineState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	private static byte countSourroundingMineStatesRightUpperCorner(Field field, MineState mineState) {
		byte sumOfMines = 0;

		if (field.getOwner().get(field.getX() - 1, field.getY()).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() - 1, field.getY() + 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() + 1).getMineState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	private static byte countSourroundingMineStatesRightLowerCorner(Field field, MineState mineState) {
		byte sumOfMines = 0;

		if (field.getOwner().get(field.getX() - 1, field.getY() - 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX(), field.getY() - 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() - 1, field.getY()).getMineState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}


	private static byte countSourroundingMineStatesLeftLowerCorner(Field field, MineState mineState) {
		byte sumOfMines = 0;

		if (field.getOwner().get(field.getX(), field.getY() - 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY() - 1).getMineState() == mineState)
			sumOfMines++;
		if (field.getOwner().get(field.getX() + 1, field.getY()).getMineState() == mineState)
			sumOfMines++;

		return sumOfMines;
	}
	
}
