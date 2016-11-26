package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.TowerDataController;
import authoring.model.WeaponData;
import authoring.view.input_menus.TowerMenu;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
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
 * Implements the tab that allows user to add, delete, or edit preexisting towers.
 */

public class TowerTab extends Tab {

	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab towerTab;
	private int screenWidth, screenHeight;
	private VBox myContent;
	private TowerMenu myMenu;
        private TowerDataController myController;
	private List<String> myTowers, myWeapons, myTerrains;
	
	public TowerTab(TabPane pane, TowerDataController controller) {
		screenInfo();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.towerTab = new Tab(myResources.getString("Towers"));
		myMenu = new TowerMenu(myResources,this);
		myController = controller;
		towerTabOptions(towerTab);
		pane.getTabs().add(towerTab);
	}
	
	public void addTower(String name) {
	        myTowers.add(name);
	}
	
	public void showError (String message) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle(myResources.getString("ErrorTitle"));
	        alert.setContentText(message);
	        alert.showAndWait();
	}
	
	private void towerTabOptions(Tab towerTab) {
		VBox towerArea = new VBox(screenHeight*0.01);
		towerArea.setMaxHeight(screenHeight*0.88);
		ScrollPane availableTowers = new ScrollPane();
		availableTowers.setPrefSize(screenWidth/5, screenHeight);
		HBox towerButtons = new HBox(screenWidth*0.05);
		towerButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button addTower = new Button(myResources.getString("AddTower"));
		addTower.setOnAction(addTowerHandler());
		towerButtons.getChildren().addAll(addTower);
		towerArea.getChildren().addAll(availableTowers, towerButtons);
		towerTab.setContent(towerArea);
	}
	
	public TowerDataController getController() {
	    return myController;
	}
	
	public List<String> getTowers() {
	    return myTowers;
	}
	
	private EventHandler<ActionEvent> addTowerHandler(){
            EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event){
                        myMenu.createTowerMenu(myResources.getString("DefaultTowerName"), 
                                               myResources.getString("DefaultHealth"), myResources.getString("DefaultBuyPrice"), 
                                               myResources.getString("DefaultSellPrice"), myResources.getString("DefaultSize"), 
                                               myResources.getString("DefaultImage"));
                    }
            };
            return handler;
        }
	
	public MapChangeListener<String, WeaponData> createWeaponListener(){
	    MapChangeListener<String, WeaponData> listener = new MapChangeListener<String, WeaponData>() {
	        @Override
	        public void onChanged(MapChangeListener.Change<? extends String, ? extends WeaponData> change) {
	            if (change.wasAdded()){
	                myWeapons.add(change.getKey());
	            }
	            else if (change.wasRemoved()){
	                myWeapons.remove(change.getKey());
	            }
	        }
	    };
	    return listener;
	}

	public SetChangeListener<String> createTerrainListener(){
	    SetChangeListener<String> listener = new SetChangeListener<String>() {
	        @Override
	        public void onChanged(SetChangeListener.Change<? extends String> change) {
	            if (change.wasAdded()){
	                myTerrains.add(change.getElementAdded());
	            }
	            else if (change.wasRemoved()){
	                myTerrains.remove(change.getElementRemoved());
	            }
	        }
	    };
	    return listener;
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
