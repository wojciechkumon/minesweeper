package minesweeper.model;

import java.util.Observable;

public abstract class Model extends Observable {

	public abstract void changeToFlag(Field field);
	public abstract void removeFlag(Field field);
	public abstract void checkField(Field field);
	public abstract boolean isAreaRevealPossible(Field field);
	public abstract void doAreaReveal(Field field);
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
	public abstract Field getField(int x, int y);
	public abstract void mouseEnteredField(Field field);
	public abstract void mouseExitedField(Field field);
	public abstract void mouseMiddleButtonPressedField(Field field);
	public abstract void mouseButtonPressedField(Field field);
	public abstract void mouseLeftButtonReleasedField(Field field);
	public abstract void mouseRightButtonReleasedField(Field field);
	public abstract void mouseMiddleButtonReleasedField(Field field);
	
	
	
	
}
