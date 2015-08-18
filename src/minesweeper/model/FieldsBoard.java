package minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;
import minesweeper.view.MineButton;

public class FieldsBoard {
	private int width;
	private int height;
	private int minesToSet;
	private int minesLeft;
	private ArrayList<ArrayList<Field>> board = new ArrayList<>(MAX_WIDTH);
	private Randomizer randomizer = new Randomizer();
	
	private static final int MIN_WIDTH = 8;
	private static final int MAX_WIDTH = 30;
	private static final int MIN_HEIGHT = 8;
	private static final int MAX_HEIGHT = 16;
	private static final int MIN_AMOUNT_OF_MINES = 10;
	private static final int MAX_AMOUNT_OF_MINES = 99;


	public FieldsBoard(int width, int height, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		for (int i = 0; i < MAX_WIDTH; i++) {
			board.add(new ArrayList<>(MAX_HEIGHT));
		}	

		setGameBoard(width, height, mines);
	}
	
	public FieldsBoard() throws AmountOutOfRange, PointOutOfBoardBounds {
		this(MAX_WIDTH, MAX_HEIGHT, MAX_AMOUNT_OF_MINES);
	}
	
	
	
	public void setGameBoard(int width, int height, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		checkBoardData(width, height, mines);
		setSize(width, height, mines);
	}
	

	private void setSize(int width, int height, int mines) throws PointOutOfBoardBounds {
		board.forEach(l -> l.clear());
		this.width = width;
		this.height = height;
		this.minesToSet = mines;
		minesLeft = mines;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board.get(i).add(new Field(j, i, this));
			}
		}
	}
	
	public void setMines(int mines) throws AmountOutOfRange {
		placeMines(mines);
		setFieldStates();
	}
	
	private void placeMines(int minesToPlace) throws AmountOutOfRange {
		int[] minesNumbers = randomizer.getRandomMineNumbers(minesToPlace, width*height);
		Arrays.sort(minesNumbers);
		
		int counter = 0;
		
		for (int i = 0; i < height; i++) {
			if (counter == minesToPlace) {
				break;
			}
				
			for (int j = 0; j < width; j++) {
				int currentNumber = i*width + j;
				if (currentNumber == minesNumbers[counter]) {
					get(i, j).setState(MineState.MINE);
					counter++;
					if (counter == minesToPlace) {
						break;
					}
				}
			}
		}
	}
	
	private void setFieldStates() {
		this.forEach( fld -> fld.calculateState() );
	}
	
	public void forEach(Consumer<? super Field> action) {
		board.forEach(lst -> lst.forEach(action));
	}
	
	
	private void checkWidth(int width) throws AmountOutOfRange {
		if (width < MIN_WIDTH || width > MAX_WIDTH)
			throw new AmountOutOfRange("MinesBoard width out of range");
	}
	
	private void checkHeight(int height) throws AmountOutOfRange {
		if (height < MIN_HEIGHT || height > MAX_HEIGHT)
			throw new AmountOutOfRange("MinesBoard height out of range");
	}
	
	private void checkMinesAmount(int width, int height, int mines) throws AmountOutOfRange {
		if (mines < MIN_AMOUNT_OF_MINES || mines > MAX_AMOUNT_OF_MINES || mines > width*height - 1)
			throw new AmountOutOfRange("MinesBoard mines amount out of range");
	}
	
	private void checkBoardData(int width, int height, int mines) throws AmountOutOfRange {
		checkWidth(width);
		checkHeight(height);
		checkMinesAmount(width, height, mines);
	}
	
	
	
	
	
	

	
	
	
	public void changeToFlag(MineButton mineBtn) {
		--minesLeft;
		//TODO model flag
	}

	public void removeFlag(MineButton mineBtn) {
		++minesLeft;
		//TODO model remove flag
	}
	
	public void checkField(MineButton mineBtn) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	public Field get(int x, int y) {
		return board.get(y).get(x);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getMinesToSet() {
		return minesToSet;
	}

	public void setMinesToSet(int minesToSet) {
		this.minesToSet = minesToSet;
	}
	
	public int getMinesLeft() {
		return minesLeft;
	}

	public void setMinesLeft(int minesLeft) {
		this.minesLeft = minesLeft;
	}
	
	
	
	
	public static void main(String[] args) {
		FieldsBoard ms = null;
		try {
			long time1 = System.currentTimeMillis();
			ms = new FieldsBoard(30, 16, 99);
			long time2 = System.currentTimeMillis();
			ms.setMines(ms.getMinesToSet());
			long time3 = System.currentTimeMillis();
			System.out.println(time2-time1);
			System.out.println(time3-time2);
		} catch (AmountOutOfRange | PointOutOfBoardBounds e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < ms.getHeight(); i++) {
			for (int j = 0; j < ms.getWidth(); j++) {
				System.out.print(ms.board.get(j).get(i).getState().getSurroundingMines());
			}
			System.out.println();
		}

	}

	
	

	

}
