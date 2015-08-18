package minesweeper.model;

import java.util.Observable;

import minesweeper.view.MineButton;

public abstract class Model extends Observable {

	public abstract void changeToFlag(MineButton mineBtn);
	public abstract void removeFlag(MineButton mineBtn);
	public abstract void checkField(MineButton mineBtn);
	
}
