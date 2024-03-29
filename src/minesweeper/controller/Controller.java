package minesweeper.controller;

import minesweeper.model.Model;
import minesweeper.view.View;

public class Controller {
	private MineButtonController mineBtnController;
	private RestartButtonController restartBtnController;
	private ChangeSettingsButtonController changeSettingsBtnController;
	
	private View view;
	private Model model;
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
		mineBtnController = new MineButtonController(this);
		restartBtnController = new RestartButtonController(this);
		changeSettingsBtnController = new ChangeSettingsButtonController(this);
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

	public MineButtonController getMineBtnController() {
		return mineBtnController;
	}

	public void setMineBtnController(MineButtonController mineBtnController) {
		this.mineBtnController = mineBtnController;
	}

	public RestartButtonController getRestartBtnController() {
		return restartBtnController;
	}

	public void setRestartBtnController(RestartButtonController restartBtnController) {
		this.restartBtnController = restartBtnController;
	}

	public ChangeSettingsButtonController getChangeSettingsBtnController() {
		return changeSettingsBtnController;
	}

	public void setChangeSettingsBtnController(ChangeSettingsButtonController changeSettingsBtnController) {
		this.changeSettingsBtnController = changeSettingsBtnController;
	}

}
