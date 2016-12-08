package authoring.view.tabs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import authoring.controller.Container;
import authoring.controller.WaveDataContainer;
import authoring.controller.MapDataContainer;
import authoring.controller.EnemyDataContainer;
import authoring.model.EnemyData;
import authoring.model.WaveData;
import authoring.view.input_menus.WaveMenu;
import engine.IObserver;
import utility.Point;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WaveTab extends ListTab<String> implements IObserver<Container>{

	private WaveDataContainer myContainer;
	private ArrayList<String> myEnemies;
	private ArrayList<String> mySpawnPoints;
	
	public WaveTab(String text, WaveDataContainer container){
		super(text);
		myContainer = container;
	}
	
	/**
	 * IObserver interface methods
	 */
	public void update(Container c){
		if (c instanceof MapDataContainer){
			mySpawnPoints.clear();
			for (String spawnPoint: ((MapDataContainer) c).getSpawnPointMap().keySet()){
				mySpawnPoints.add(spawnPoint);
			}
		}else if(c instanceof EnemyDataContainer){
			myEnemies.clear();
			for (String enemyName: ((EnemyDataContainer) c).getEnemyDataMap().keySet()){
				myEnemies.add(enemyName);
			}
		}
	}

	@Override
	protected EventHandler handleAddNewObject() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				VBox menu = setUpMenu();
				myContent.getChildren().add(menu);
			}
		};
	}
	
	private VBox setUpMenu(){
		VBox v = new VBox();
		v.getChildren().add(new Button("hi"));
		return v;
	}
}
