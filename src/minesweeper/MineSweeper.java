package minesweeper;

import minesweeper.controller.Controller;
import minesweeper.model.DefaultModel;
import minesweeper.model.Model;
import minesweeper.view.DefaultView;
import minesweeper.view.View;

public class MineSweeper {

	public static void main(String[] args) {
		View view;
		Model model;
		Controller controller;
		try {
			view = new DefaultView();
			model = new DefaultModel();

			model.addObserver(view);
			controller = new Controller(model, view);
			view.setController(controller);
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
