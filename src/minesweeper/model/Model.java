package minesweeper.model;

import java.util.Observable;

import minesweeper.view.MineButton;

public abstract class Model extends Observable {

	public abstract void changeToFlag(MineButton mineBtn);
	public abstract void removeFlag(MineButton mineBtn);
	public abstract void checkField(MineButton mineBtn);
	public abstract boolean isAreaRevealPossible(MineButton mineBtn);
	public abstract void doAreaReveal(MineButton mineBtn);
	public abstract void restartGame();
	public abstract void restartGame(int horizontalMines, int verticalMines, int mines);
	
	public abstract int getHorizontalNumberOfMines();
	public abstract int getVerticalNumberOfMines();
	public abstract int getNumberOfMines();
	public abstract int getMinesLeft();
	public abstract void setChanges();
	
	public abstract int getMinHorizontalFields();
	public abstract int getMaxHorizontalFields();
	public abstract int getMinVerticalFields();
	public abstract int getMaxVerticalFields();
	public abstract int getMinAmountOfMines();
	public abstract int getMaxAmountOfMines();
}
