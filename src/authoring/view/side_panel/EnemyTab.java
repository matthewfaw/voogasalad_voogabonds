package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import authoring.controller.EnemyDataController;
import authoring.view.input_menus.EnemyMenu;
import authoring.view.objects.FrontEndEnemy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private HashMap<String, FrontEndEnemy> myEnemyMap;
	private EnemyMenu myMenu;
	
	public EnemyTab(TabPane pane) {
		screenInfo();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		enemyTab = new Tab(myResources.getString("Enemies"));
		myEnemyMap = new HashMap<String, FrontEndEnemy>();
		myMenu = new EnemyMenu(myResources, this);
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
						myResources.getString("DefaultType"), myResources.getString("DefaultHealth"), 
						myResources.getString("DefaultSpeed"), myResources.getString("DefaultSpawn"), 
						myResources.getString("DefaultEnd"), myResources.getString("DefaultImage"), 
						myResources.getString("DefaultSound"));
			}
		};
		return handler;
	}
	
	public void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
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
				FrontEndEnemy enemy = myEnemyMap.get(text);
				myMenu.createEnemyWindow(enemy.getName(), enemy.getType(), String.valueOf(enemy.getHealth()), 
						String.valueOf(enemy.getSpeed()), point2DToString(enemy.getSpawnPoint()), 
						point2DToString(enemy.getEndPoint()), enemy.getImage(), enemy.getSound());
			}
		});
		myContent.getChildren().add(button);
	}
	
	private String point2DToString(Point2D point){
		StringBuilder sb = new StringBuilder("(");
		sb.append(point.getX());
		sb.append(", ");
		sb.append(point.getY());
		sb.append(")");
		return sb.toString();
	}
	
	public Map<String, FrontEndEnemy> getEnemyMap(){
		return myEnemyMap;
	}
}
