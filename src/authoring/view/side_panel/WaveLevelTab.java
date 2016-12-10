package authoring.view.side_panel;

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

public class WaveLevelTab extends Tab implements IObserver<Container>{

	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab waveTab;
	private int screenWidth;
	private int screenHeight;
	private WaveMenu myMenu;
	private WaveDataContainer myController;
	private ArrayList<String> myEnemies;
	private ArrayList<String> mySpawnPoints;
	private VBox myContent;
	
	public WaveLevelTab(TabPane pane, WaveDataContainer controller) {
		screenInfo();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		waveTab = new Tab(myResources.getString("Waves"));
		myMenu = new WaveMenu(myResources, this);
		myController = controller;
		myEnemies = new ArrayList<String>();
		mySpawnPoints = new ArrayList<String>();
		waveTabOptions(waveTab);
		pane.getTabs().add(waveTab);
	}
	
	private void waveTabOptions(Tab waveTab) {
		VBox waveArea = new VBox(screenHeight*0.01);
		waveArea.setMaxHeight(screenHeight*0.88);
		ScrollPane currentWaves = new ScrollPane();
		myContent = new VBox();
		currentWaves.setContent(myContent);
		currentWaves.setPrefSize(screenWidth/5, screenHeight);
		HBox waveButtons = new HBox(screenWidth*0.05);
		waveButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button addWave = new Button(myResources.getString("AddWave"));
		addWave.setOnAction(addWaveHandler());
		waveButtons.getChildren().add(addWave);
		waveArea.getChildren().addAll(currentWaves, waveButtons);
		waveTab.setContent(waveArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private EventHandler<ActionEvent> addWaveHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				myMenu.createWaveWindow(myResources.getString("DefaultWaveName"), 
						myResources.getString("DefaultTimeBetween"), myResources.getString("DefaultTimeFor"),
						myResources.getString("DefaultNumber"), null, null, true);
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
				WaveData wave = myController.getWaveData(text);
				myMenu.createWaveWindow(wave.getName(), String.valueOf(wave.getTimeBetweenEnemy()), 
						String.valueOf(wave.getTimeUntilNextWave()), String.valueOf(wave.getNumEnemies()), 
						wave.getWaveEntity(), wave.getSpawnPointName(), false);
			}
		});
		myContent.getChildren().add(button);
	}
	
	public WaveDataContainer getController(){
		return myController;
	}
	
	public ArrayList<String> getEnemies(){
		return myEnemies;
	}
	
	public ArrayList<String> getSpawnPoints(){
		return mySpawnPoints;
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
	
//	public MapChangeListener<String, EnemyData> createEnemyListener(){
//		MapChangeListener<String, EnemyData> listener = new MapChangeListener<String, EnemyData>() {
//			@Override
//			public void onChanged(MapChangeListener.Change<? extends String, ? extends EnemyData> change) {
//				if (change.wasAdded()){
//					myEnemies.add(change.getKey());
//				}
//				else if (change.wasRemoved()){
//					myEnemies.remove(change.getKey());
//				}
//			}
//		};
//		return listener;
//	}
//	
//	public MapChangeListener<String, ArrayList<Point>> createSpawnPointListener(){
//		MapChangeListener<String, ArrayList<Point>> listener = new MapChangeListener<String, ArrayList<Point>>() {
//			@Override
//			public void onChanged(MapChangeListener.Change<? extends String, ? extends ArrayList<Point>> change) {
//				if (change.wasAdded()){
//					mySpawnPoints.add(change.getKey());
//				}
//				else if (change.wasRemoved()){
//					mySpawnPoints.remove(change.getKey());
//				}
//			}
//		};
//		return listener;
//	}
	
}
