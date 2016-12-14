package mainmenu.screens;

import java.io.IOException;

import engine.controller.ApplicationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import utility.ErrorBox;


/**
 * @author Christopher Lu
 * This class creates the load authoring environment screen when the user wants to open a preexisting authoring environment to work on.
 */

public class NewGameScreen extends AbstractLoadScreen{
	
	
	public NewGameScreen(String title, String files) throws IOException {
		super(title, files);
	}
	
	protected void start(String selectedGame){
		try {
			initGamePlay(selectedGame);
		} catch (Exception e1) {
			ErrorBox.displayError(getResources().getString("NewPlayerError"));
		}
	}
	
	public void initGamePlay(String gameTitle) throws Exception { //TODO HELP
		ApplicationController appController = new ApplicationController();
		appController.init(getStage(), gameTitle);
	}

		
	
}
