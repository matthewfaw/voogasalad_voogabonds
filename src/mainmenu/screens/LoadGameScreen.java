package mainmenu.screens;

import java.io.IOException;

import engine.controller.ApplicationController;
import utility.ErrorBox;

public class LoadGameScreen extends AbstractLoadScreen {

	public LoadGameScreen(String title, String files) throws IOException {
		super(title, files);
	}

	@Override
	protected void start(String selectedGame) {
		try {
			ApplicationController appController = new ApplicationController();
			appController.load(getStage(), selectedGame);
		} catch (Exception e1) {
			ErrorBox.displayError(getResources().getString("NewPlayerError"));
		}
	}

	

}
