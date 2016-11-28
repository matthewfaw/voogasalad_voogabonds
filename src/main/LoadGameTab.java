package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ResourceBundle;

import authoring.model.serialization.JSONDeserializer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Christopher Lu
 * Creates the LOAD game tab where a user will select a list of preexisting projects to open.
 */

public class LoadGameTab {

	private TabPane root;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab loadGame;
	private int screenWidth;
	private int screenHeight;
	
	public LoadGameTab (TabPane pane) {
		setUpScreenResolution();
		this.root = pane;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.loadGame = new Tab(myResources.getString("LoadGameTabName"));
		populateTab();
		root.getTabs().add(loadGame);
	}
	
	private void populateTab() {
		VBox loadGameOptions = new VBox(screenHeight*0.1);
		loadGameOptions.setPadding(new Insets(screenHeight*0.01, screenWidth*0.01, screenHeight*0.01, screenWidth*0.01));
		TextField selectedGameName = new TextField();
		selectedGameName.setPromptText(myResources.getString("NewGameName"));
		selectedGameName.setPrefColumnCount((int) (screenWidth*0.1));
		Button chooseSavePath = new Button(myResources.getString("OldGame"));
		loadHandler(chooseSavePath, selectedGameName);
		loadGameOptions.getChildren().addAll(selectedGameName, chooseSavePath);
		loadGame.setContent(loadGameOptions);
	}
	
	private void loadHandler(Button button, TextField gameName) {
		button.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						FileChooser loadGame = new FileChooser();
						Stage stage = new Stage();
						File file = loadGame.showOpenDialog(stage);
						if (file != null) {
							JSONDeserializer json = new JSONDeserializer();
						}
					}
				});
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
