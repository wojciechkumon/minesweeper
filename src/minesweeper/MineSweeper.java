package minesweeper;

import minesweeper.controller.Controller;
import minesweeper.model.DefaultModel;
import minesweeper.model.Model;
import minesweeper.view.DefaultView;

public class MineSweeper {

	public static void main(String[] args) {
		DefaultView view;
		Model model;
		Controller controller;
		try {
			model = new DefaultModel();
			view = new DefaultView();
			controller = new Controller(model, view);
			view.setController(controller);
			model.addObserver(view);
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
