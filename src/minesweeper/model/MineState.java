package minesweeper.model;

public enum MineState {
	SURROUNDING_0(0), SURROUNDING_1(1), SURROUNDING_2(2), SURROUNDING_3(3),
	SURROUNDING_4(4), SURROUNDING_5(5), SURROUNDING_6(6), SURROUNDING_7(7),
	SURROUNDING_8(8), MINE(9);
	
	private int surroundingMines;
	
	MineState(int surroundingMines) {
		this.surroundingMines = surroundingMines;
	}
	
	public int getSurroundingMines() {
		return surroundingMines;
	}
	
	public static MineState getMineStateByInt(int minesAmount) {
		switch (minesAmount) {
		case 0:
			return MineState.SURROUNDING_0;
		case 1:
			return MineState.SURROUNDING_1;
		case 2:
			return MineState.SURROUNDING_2;
		case 3:
			return MineState.SURROUNDING_3;
		case 4:
			return MineState.SURROUNDING_4;
		case 5:
			return MineState.SURROUNDING_5;
		case 6:
			return MineState.SURROUNDING_6;
		case 7:
			return MineState.SURROUNDING_7;
		case 8:
			return MineState.SURROUNDING_8;
		default:
			return MineState.MINE;	
		}
	}
	
	
}
