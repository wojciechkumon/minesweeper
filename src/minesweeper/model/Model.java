package minesweeper.model;

import java.util.Observable;

import minesweeper.view.MineButton;

public abstract class Model extends Observable {

	public abstract void changeToFlag(MineButton mineBtn);
	public abstract void removeFlag(MineButton mineBtn);
	public abstract void checkField(MineButton mineBtn);
	public abstract boolean isAreaRevealPossible(MineButton mineBtn);
	public abstract void doAreaReveal(MineButton mineBtn);
	
	public abstract int getHorizontalNumberOfMines();
	public abstract int getVerticalNumberOfMines();
	public abstract int getNumberOfMines();
	public abstract int getMinesLeft();
	public abstract void setChanges();
}
