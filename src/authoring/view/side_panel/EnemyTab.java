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
 * Creates the enemy pane option that allows user to add enemies. Preexisting/created enemies will showup in the pane as buttons that can be edited upon click.
 */

public class EnemyTab extends Tab {
	
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab enemyTab;
	private int screenWidth;
	private int screenHeight;
	
	public EnemyTab(TabPane pane) {
		screenInfo();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.enemyTab = new Tab(myResources.getString("Enemies"));
		enemyTabOptions(enemyTab);
		pane.getTabs().add(enemyTab);
	}
	
	private void enemyTabOptions(Tab enemyTab) {
		VBox enemyArea = new VBox(screenHeight*0.01);
		enemyArea.setMaxHeight(screenHeight*0.88);
		ScrollPane availableEnemies = new ScrollPane();
		availableEnemies.setPrefSize(screenWidth/5, screenHeight);
		HBox enemyButtons = new HBox(screenWidth*0.05);
		enemyButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button addEnemy = new Button(myResources.getString("AddEnemy"));
		enemyButtons.getChildren().addAll(addEnemy);
		enemyArea.getChildren().addAll(availableEnemies, enemyButtons);
		enemyTab.setContent(enemyArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}

}
