package minesweeper.controller;

import minesweeper.model.Model;
import minesweeper.view.View;

public class Controller {
	private MineButtonController mineBtnController;
	private View view;
	private Model model;
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
	}
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
