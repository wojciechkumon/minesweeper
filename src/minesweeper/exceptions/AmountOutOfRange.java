package minesweeper.exceptions;

public class AmountOutOfRange extends Exception {
	private static final long serialVersionUID = -6711621726275403680L;

	public AmountOutOfRange() {
		super("Amount out of range exception");
	}

	public AmountOutOfRange(String message) {
		super(message);
	}

}