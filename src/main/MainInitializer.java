package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import authoring.controller.MapDataContainer;
import authoring.view.display.AuthorDisplay;
import authoring.view.display.GameDisplay;
import authoring.view.menus.TopMenuBar;
import authoring.view.side_panel.InfoTabs;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Christopher Lu
 * Initializes the VOOGASalad application to the Main Menu. Also responsible for switching to the in game scene.
 */

public class MainInitializer {
	
	private Stage stage;
	private int screenWidth;
	private int screenHeight;
	private String title;
	private Scene scene;
	private BorderPane root;

	public MainInitializer(Stage s) throws IOException {
		this.stage = s;
		root = new BorderPane();
		setUpScreenResolution();
		MainMenu menu = new MainMenu(this, s);
		Scene mainMenu = menu.init();
		scene = new Scene(root, screenWidth, screenHeight, Color.WHITE);
		stage.setScene(mainMenu);
		stage.show();
	}

	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void initSim() {
		//TopMenuBar menuBar = new TopMenuBar(this, root);
		//InfoTabs infoTab = new InfoTabs(root, scene);
	        AuthorDisplay authoring = new AuthorDisplay(this, root, scene);
		stage.setScene(scene);
	}
	
}
