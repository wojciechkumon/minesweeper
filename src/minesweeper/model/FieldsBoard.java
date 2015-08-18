package minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;
import minesweeper.view.MineButton;

public class FieldsBoard {
	private int horizontalMines;
	private int verticalMines;
	private int minesToSet;
	private int minesLeft;
	private ArrayList<ArrayList<Field>> board = new ArrayList<>(MAX_WIDTH);
	private Randomizer randomizer = new Randomizer();
	private Model owner;
	
	private static final int MIN_WIDTH = 8;
	private static final int MAX_WIDTH = 30;
	private static final int MIN_HEIGHT = 8;
	private static final int MAX_HEIGHT = 16;
	private static final int MIN_AMOUNT_OF_MINES = 10;
	private static final int MAX_AMOUNT_OF_MINES = 99;


	public FieldsBoard(Model owner, int width, int height, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		this.owner = owner;
		for (int i = 0; i < MAX_WIDTH; i++) {
			board.add(new ArrayList<>(MAX_HEIGHT));
		}	

		setGameBoard(width, height, mines);
	}
	
	public FieldsBoard(Model owner) throws AmountOutOfRange, PointOutOfBoardBounds {
		this(owner, MAX_WIDTH, MAX_HEIGHT, MAX_AMOUNT_OF_MINES);
	}
	
	
	
	public void setGameBoard(int width, int height, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		checkBoardData(width, height, mines);
		setSize(width, height, mines);
		setMines(mines);
	}
	

	private void setSize(int width, int height, int mines) throws PointOutOfBoardBounds {
		board.forEach(l -> l.clear());
		this.horizontalMines = width;
		this.verticalMines = height;
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
		int[] minesNumbers = randomizer.getRandomMineNumbers(minesToPlace, horizontalMines*verticalMines);
		Arrays.sort(minesNumbers);
		
		int counter = 0;
		
		for (int i = 0; i < verticalMines; i++) {
			if (counter == minesToPlace) {
				break;
			}
				
			for (int j = 0; j < horizontalMines; j++) {
				int currentNumber = i*horizontalMines + j;
				if (currentNumber == minesNumbers[counter]) {
					get(i, j).setMineState(MineState.MINE);
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
		Field field = get(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		if (field.getClickState() != ClickState.NOT_CLICKED)
			return;
		
		field.setClickState(ClickState.FLAG);
		--minesLeft;
		UpdateBox updateBox = new UpdateBox();
		updateBox.setMinesLeftToUpdate(true);
		updateBox.addFieldToUpdate(field);

		owner.setChanges();
		owner.notifyObservers(updateBox);
	}

	public void removeFlag(MineButton mineBtn) {
		Field field = get(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		if (field.getClickState() != ClickState.FLAG)
			return;
		
		field.setClickState(ClickState.NOT_CLICKED);
		++minesLeft;
		
		UpdateBox updateBox = new UpdateBox();
		updateBox.setMinesLeftToUpdate(true);
		updateBox.addFieldToUpdate(field);
		
		owner.setChanges();
		owner.notifyObservers(updateBox);
	}
	
	public void checkField(MineButton mineBtn) {
		Field field = get(mineBtn.getXPostition(), mineBtn.getYPostition());
		
		
		field.clickField();
		if (field.getMineState() == MineState.SURROUNDING_0)
			clickAllSurrounding(mineBtn);
		
		
		UpdateBox updateBox = new UpdateBox();
		updateBox.addFieldToUpdate(field);
		if (field.isMine())
			updateBox.setLose(true);
		
		owner.setChanges();
		owner.notifyObservers(updateBox);
	}
	
	private void clickAllSurrounding(MineButton mineBtn) {
		// TODO Auto-generated method stub
		
	}

	public boolean isAreaRevealPossible(MineButton mineBtn) {
		Field field = get(mineBtn.getXPostition(), mineBtn.getYPostition());
	
		if (field.isAreaClickPossible())
			return true;
		return false;
	}
	
	public void doAreaReveal(MineButton mineBtn) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	public Field get(int x, int y) {
		return board.get(y).get(x);
	}
	
	public int getHorizontalMines() {
		return horizontalMines;
	}
	
	public int getVerticalMines() {
		return verticalMines;
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
	
	public Model getOwner() {
		return owner;
	}


	
	public static void main(String[] args) {
		FieldsBoard ms = null;
		try {
			long time1 = System.currentTimeMillis();
			ms = new FieldsBoard(null, 30, 16, 99);
			long time2 = System.currentTimeMillis();
			ms.setMines(ms.getMinesToSet());
			long time3 = System.currentTimeMillis();
			System.out.println(time2-time1);
			System.out.println(time3-time2);
		} catch (AmountOutOfRange | PointOutOfBoardBounds e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < ms.getVerticalMines(); i++) {
			for (int j = 0; j < ms.getHorizontalMines(); j++) {
				System.out.print(ms.board.get(j).get(i).getMineState().getSurroundingMines());
			}
			System.out.println();
		}

	}

	

}
