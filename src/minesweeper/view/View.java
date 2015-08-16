package minesweeper.view;

import java.util.Observer;

import minesweeper.controller.Controller;

public interface View extends Observer {
	void setController(Controller controller);
	void setVisible(boolean isVisible);
}
