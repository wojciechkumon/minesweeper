package minesweeper.model;

import java.util.ArrayList;

public class UpdateBox {
	private boolean isNeededRestart = false;
	private boolean isMinesLeftToUpdate = false;
	private boolean isWin = false;
	private boolean isLose = false;
	private ArrayList<Field> fieldsToUpdate = null;
	
	
	
	public boolean isNeededRestart() {
		return isNeededRestart;
	}
	public void setNeededRestart(boolean isNeededRestart) {
		this.isNeededRestart = isNeededRestart;
	}
	public boolean isMinesLeftToUpdate() {
		return isMinesLeftToUpdate;
	}
	public void setMinesLeftToUpdate(boolean isMinesLeftToUpdate) {
		this.isMinesLeftToUpdate = isMinesLeftToUpdate;
	}
	public ArrayList<Field> getFieldsToUpdate() {
		return fieldsToUpdate;
	}
	public void addFieldToUpdate(Field field) {
		if (fieldsToUpdate == null)
			fieldsToUpdate = new ArrayList<Field>(9);
		fieldsToUpdate.add(field);
	}
	
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	public boolean isLose() {
		return isLose;
	}
	public void setLose(boolean isLose) {
		this.isLose = isLose;
	}
	
}
