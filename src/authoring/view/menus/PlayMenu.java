package authoring.view.menus;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Christopher Lu
 * Creates the Play submenus that include: play game from start, play game from current state, and return to authoring.
 */

public class PlayMenu extends Menu {
	
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Menu play;
	
	public PlayMenu(MenuBar bar) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.play = new Menu(myResources.getString("PlayMenuLabel"));
		personalizeOptions(play);
		bar.getMenus().add(play);
	}

	private void personalizeOptions(Menu play) {
		MenuItem playStart = new MenuItem(myResources.getString("PlayFromStart"));
		performPlayStart(playStart);
		MenuItem playHere = new MenuItem(myResources.getString("PlayFromHere"));
		performPlayHere(playHere);
		MenuItem returnToAuthoring = new MenuItem(myResources.getString("ReturnToAuthoring"));
		performReturnAuthoring(returnToAuthoring);
		play.getItems().addAll(playStart, playHere, returnToAuthoring);
	}
	
	private void performPlayStart(MenuItem playStart) {
		playStart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that plays the game from the start upon clicking the Play From Start button. This means starting the game from the beginning, before the 1st wave.
			}
		});
	}
	
	private void performPlayHere(MenuItem playHere) {
		playHere.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that plays the game from the current state. The user may have clicked play from start earlier, got to wave 5, returned to authoring, 
				// wants to continue playing from wave 5 onwards e.g. debugging.
			}
		});
	}
	
	private void performReturnAuthoring(MenuItem returnToAuthoring) {
		returnToAuthoring.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that returns to authoring mode from playing mode.
			}
		});
	}
	
}
