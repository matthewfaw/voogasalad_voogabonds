package mainmenu.screens;

import java.io.IOException;

import engine.controller.ApplicationController;
import utility.ErrorBox;

public class LoadGameScreen extends AbstractLoadScreen {
	public ApplicationController myApplicationController;

	public LoadGameScreen(String title) throws IOException {
		super(title);
		myApplicationController = new ApplicationController();
	}

	@Override
	protected void start(String selectedGame) {
		try {
			myApplicationController.load(getStage(), selectedGame);
		} catch (Exception e1) {
			ErrorBox.displayError(getResources().getString("NewPlayerError"));
		}
	}

	

}
