package minesweeper.exceptions;

public class PointOutOfBoardBounds extends Exception {
	private static final long serialVersionUID = -8235262834924459842L;

	public PointOutOfBoardBounds() {
		super("Point out of bounds exception");
	}

	public PointOutOfBoardBounds(String message) {
		super(message);
	}

}
