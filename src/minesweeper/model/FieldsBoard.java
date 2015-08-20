package minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import minesweeper.exceptions.AmountOutOfRange;
import minesweeper.exceptions.PointOutOfBoardBounds;

public class FieldsBoard {
	private int horizontalMines;
	private int verticalMines;
	private int minesToSet;
	private int minesLeft;
	private boolean isFirstClick = true;
	private ArrayList<ArrayList<Field>> board = new ArrayList<>(MAX_HORIZONTAL_FIELDS);
	
	
	private Randomizer randomizer = new Randomizer();
	private Model owner;
	
	public static final int MIN_HORIZONTAL_FIELDS = 8;
	public static final int MAX_HORIZONTAL_FIELDS = 30;
	public static final int MIN_VERTICAL_FIELDS = 8;
	public static final int MAX_VERTICAL_FIELDS = 16;
	public static final int MIN_AMOUNT_OF_MINES = 10;
	public static final int MAX_AMOUNT_OF_MINES = 99;


	public FieldsBoard(Model owner, int width, int height, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		this.owner = owner;
		for (int i = 0; i < MAX_HORIZONTAL_FIELDS; i++) {
			board.add(new ArrayList<>(MAX_VERTICAL_FIELDS));
		}	

		setGameBoard(width, height, mines);
	}
	
	public FieldsBoard(Model owner) throws AmountOutOfRange, PointOutOfBoardBounds {
		this(owner, MAX_HORIZONTAL_FIELDS, MAX_VERTICAL_FIELDS, MAX_AMOUNT_OF_MINES);
	}
	
	
	
	public void setGameBoard(int width, int height, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		checkBoardData(width, height, mines);
		setSize(width, height, mines);
		isFirstClick = true;
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
	
	public void setMines(Field field) {
		placeMines(field);
		setFieldStates();
	}
	
	private void placeMines(Field field) {
		int fieldNumberToSkip = field.getX() * horizontalMines + field.getY(); 
		int[] minesNumbers = randomizer.getRandomMineNumbersWithoutOne(minesToSet, horizontalMines*verticalMines, fieldNumberToSkip);
		Arrays.sort(minesNumbers);
		
		int counter = 0;
		
		for (int i = 0; i < verticalMines; i++) {
			if (counter == minesToSet) {
				break;
			}
				
			for (int j = 0; j < horizontalMines; j++) {
				int currentNumber = i*horizontalMines + j;
				if (currentNumber == minesNumbers[counter]) {
					get(i, j).setMineState(MineState.MINE);
					counter++;
					if (counter == minesToSet) {
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
		if (width < MIN_HORIZONTAL_FIELDS || width > MAX_HORIZONTAL_FIELDS)
			throw new AmountOutOfRange("MinesBoard width out of range");
	}
	
	private void checkHeight(int height) throws AmountOutOfRange {
		if (height < MIN_VERTICAL_FIELDS || height > MAX_VERTICAL_FIELDS)
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
	

	
	
	public void changeToFlag(Field field) {
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

	public void removeFlag(Field field) {
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
	
	public void checkField(Field field) {
		if (field.getClickState() != ClickState.NOT_CLICKED)
			return;
		
		if (isFirstClick) {
			setMines(field);
			isFirstClick = false;
		}

		field.clickField();
		
		UpdateBox updateBox = new UpdateBox();
		updateBox.addFieldToUpdate(field);
		if (field.isMine()) {
			updateBox.setLose(true);
			doLose();
		} else if (isWin()) {
			updateBox.setWin(true);
			doWin();
		}
			
		
		owner.setChanges();
		owner.notifyObservers(updateBox);
		if (field.getMineState() == MineState.SURROUNDING_0)
			clickAllSurrounding(field);
	}
	
	private void clickAllSurrounding(Field field) {
		Field[] surroundingFields = field.getSurroundingFields();
		
		for (int i = 0; i < surroundingFields.length; i++) {
			checkField(surroundingFields[i]);
		}
	}

	public boolean isAreaRevealPossible(Field field) {
		if (field.isAreaClickPossible())
			return true;
		return false;
	}
	
	public void doAreaReveal(Field field) {
		clickAllSurrounding(field);
	}
	
	private void doWin() {
		forEach(fld -> {
			if (fld.getMineState() == MineState.MINE)
				fld.setClickState(ClickState.FLAG);
			else
				fld.setClickState(ClickState.CLICKED);
		});
	}
	
	private void doLose() {
		forEach(fld -> fld.setClickState(ClickState.CLICKED));
	}
	
	public void restartGame() {
		forEach(fld -> fld.setClickState(ClickState.NOT_CLICKED));
		forEach(fld -> fld.setMineState(MineState.SURROUNDING_0));
		isFirstClick = true;
		
		minesLeft = minesToSet;
		
		UpdateBox updateBox = new UpdateBox();
		updateBox.setNeededRestart(true);
		
		owner.setChanges();
		owner.notifyObservers(updateBox);
	}
	
	public void restartGame(int horizontalMines, int verticalMines, int mines) throws AmountOutOfRange, PointOutOfBoardBounds {
		setGameBoard(horizontalMines, verticalMines, mines);
		
		UpdateBox updateBox = new UpdateBox();
		updateBox.setNeededRestart(true);
		
		owner.setChanges();
		owner.notifyObservers(updateBox);
	}
	
	private boolean isWin() {
		int sum = 0;
		for (ArrayList<Field> arr: board) {
			for (Field fld: arr) {
				if (fld.getClickState() != ClickState.CLICKED)
					++sum;
			}
		}
		if (sum == minesToSet)
			return true;
		return false;
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
		FieldsBoard fldBrd = null;
		try {
			long time1 = System.currentTimeMillis();
			fldBrd = new FieldsBoard(null, 30, 16, 99);
			long time2 = System.currentTimeMillis();
			fldBrd.setMines(new Field(0, 1, null));
			long time3 = System.currentTimeMillis();
			System.out.println(time2-time1);
			System.out.println(time3-time2);
		} catch (AmountOutOfRange | PointOutOfBoardBounds e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < fldBrd.getVerticalMines(); i++) {
			for (int j = 0; j < fldBrd.getHorizontalMines(); j++) {
				System.out.print(fldBrd.board.get(j).get(i).getMineState().getSurroundingMines());
			}
			System.out.println();
		}

	}

	

}
