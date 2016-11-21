package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.controller.EnemyDataController;
import authoring.model.EnemyData;
import authoring.view.input_menus.EnemyMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Christopher Lu
 * Creates the enemy pane option that allows user to add enemies. Preexisting/created enemies will showup in the pane as buttons that can be edited upon click.
 */

public class EnemyTab extends Tab {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private ResourceBundle myResources;
	private Tab enemyTab;
	private int screenWidth;
	private int screenHeight;
	private VBox myContent;
	private ArrayList<String> myEnemies;
	private EnemyMenu myMenu;
	private EnemyDataController myController;
	
	public EnemyTab(TabPane pane) {
		screenInfo();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		enemyTab = new Tab(myResources.getString("Enemies"));
		myEnemies = new ArrayList<String>();
		myMenu = new EnemyMenu(myResources, this);
		myController = new EnemyDataController();
		enemyTabOptions(enemyTab);
		pane.getTabs().add(enemyTab);
	}
	
	private void enemyTabOptions(Tab enemyTab) {
		VBox enemyArea = new VBox(screenHeight*0.01);
		enemyArea.setMaxHeight(screenHeight*0.88);
		ScrollPane availableEnemies = new ScrollPane();
		myContent = new VBox();
		availableEnemies.setContent(myContent);
		availableEnemies.setPrefSize(screenWidth/5, screenHeight);
		HBox enemyButtons = new HBox(screenWidth*0.05);
		enemyButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button addEnemy = new Button(myResources.getString("AddEnemy"));
		addEnemy.setOnAction(addEnemyHandler());
		enemyButtons.getChildren().addAll(addEnemy);
		enemyArea.getChildren().addAll(availableEnemies, enemyButtons);
		enemyTab.setContent(enemyArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private EventHandler<ActionEvent> addEnemyHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				myMenu.createEnemyWindow(myResources.getString("DefaultName"), 
						myResources.getString("DefaultHealth"), myResources.getString("DefaultSpeed"), 
						myResources.getString("DefaultCollisionRadius"), 
						myResources.getString("DefaultKillReward"), myResources.getString("DefaultImage"),
						true);
			}
		};
		return handler;
	}
	
	public void removeButtonDuplicates(String s) {
		for (int i = 0; i < myContent.getChildren().size(); i++) {
			Button button = (Button) (myContent.getChildren().get(i));
			if (button.getText().equals(s)) {
				myContent.getChildren().remove(i);
				i--;
			}
		}
	}
	
	public void addButtonToDisplay(String text) {
		Button button = new Button(text);
		button.setMinWidth(myContent.getMinWidth());
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				EnemyData enemy = myController.getEnemyData(text);
				myMenu.createEnemyWindow(enemy.getName(), String.valueOf(enemy.getMaxHealth()), 
						String.valueOf(enemy.getSpeed()), String.valueOf(enemy.getCollisionRadius()), 
						String.valueOf(enemy.getKillReward()), enemy.getImagePath(), false);
			}
		});
		myContent.getChildren().add(button);
	}
	
	public List<String> getEnemies(){
		return myEnemies;
	}
	
	public EnemyDataController getController(){
		return myController;
	}
}
