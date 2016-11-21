package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Christopher Lu
 * Implements the tab that allows user to add, delete, or edit preexisting towers.
 */

public class TowerTab extends Tab {

	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab towerTab;
	private int screenWidth;
	private int screenHeight;
	
	public TowerTab(TabPane pane) {
		screenInfo();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.towerTab = new Tab(myResources.getString("Towers"));
		towerTabOptions(towerTab);
		pane.getTabs().add(towerTab);
	}
	
	private void towerTabOptions(Tab towerTab) {
		VBox towerArea = new VBox(screenHeight*0.01);
		towerArea.setMaxHeight(screenHeight*0.88);
		ScrollPane availableTowers = new ScrollPane();
		availableTowers.setPrefSize(screenWidth/5, screenHeight);
		HBox towerButtons = new HBox(screenWidth*0.05);
		towerButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button addTower = new Button(myResources.getString("AddTower"));
		towerButtons.getChildren().addAll(addTower);
		towerArea.getChildren().addAll(availableTowers, towerButtons);
		towerTab.setContent(towerArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
