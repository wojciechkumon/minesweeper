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
			
			model.addObserver(view);
			controller = new Controller(model, view);
			view.setController(controller);
			view.setVisible(true);
			view.makeFields(30, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
