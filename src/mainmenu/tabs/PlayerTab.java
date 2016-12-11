package mainmenu.tabs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import main.MainInitializer;

/**
 * @author Christopher Lu
 * User can choose to either play a new game a load a previous game in this tab.
 */

public class PlayerTab extends Tab {
	
	private TabPane root;
	private Tab playerTab;
	private int screenWidth;
	private int screenHeight;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private VBox playOptions;
	private MainInitializer initializer;
	
	public PlayerTab(TabPane mainMenuTab, MainInitializer init) {
		setUpScreenResolution();
		this.root = mainMenuTab;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.playerTab = new Tab(myResources.getString("EnterPlayerMode"));
		this.playOptions = new VBox(screenHeight*0.1);
		this.initializer = init;
		populateTab();
		root.getTabs().add(playerTab);
	}
	
	private void populateTab() {
		populateBox();
		playerTab.setContent(playOptions);
	}
	
	private void populateBox() {
		Button newGame = new Button(myResources.getString("PlayNewGame"));
		newGame.getStylesheets().add("style.css");
		newGame.setId("button");
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				handleNewGame();
			}
		});
		Button loadGame = new Button(myResources.getString("PlayOldGame"));
		loadGame.getStylesheets().add("style.css");
		loadGame.setId("button");
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				handleOldGame();
			}
		});
		playOptions.getChildren().addAll(newGame, loadGame);
	}
	
	private void handleNewGame() {
		
	}
	
	private void handleOldGame() {
		
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
