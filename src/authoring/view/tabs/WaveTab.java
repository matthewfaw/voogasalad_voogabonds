package authoring.view.tabs;

import java.util.ArrayList;

import authoring.controller.Container;
import authoring.controller.WaveDataContainer;
import authoring.model.WaveData;
import authoring.controller.MapDataContainer;
import authoring.controller.EntityDataContainer;
import engine.IObserver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class WaveTab extends ListTab<String> implements IObserver<Container>{

	private WaveDataContainer myContainer;
	
	private ObservableList<String> myEntities = FXCollections.observableList(new ArrayList<String>());
	private ObservableList<String> mySpawnPoints = FXCollections.observableList(new ArrayList<String>());
	
	public WaveTab(String text, WaveDataContainer container){
		super(text);
		myContainer = container;
	}
	

	@Override
	protected EventHandler handleAddNewObject() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				VBox menu = setUpMenu(getResources().getString("DefaultWaveName"), 
						getResources().getString("DefaultTimeBetween"), 
						getResources().getString("DefaultTimeFor"), 
						getResources().getString("DefaultNumberEnemies"), null, null);
				getTilePane().getChildren().add(menu);
			}
		};
	}
	
	private VBox setUpMenu(String name, String timeBetween, String timeFor, String numEnemies, 
			String enemy, String spawn){
		VBox v = new VBox();
		Label nameLabel = setUpLabel(getResources().getString("EnterName"));
		TextField nameField = setUpTextInput(name);
		nameLabel.setLabelFor(nameField);
		Label timeBetweenLabel = setUpLabel(getResources().getString("EnterTimeBetween"));
		TextField timeBetweenField = setUpTextInput(timeBetween);
		timeBetweenLabel.setLabelFor(timeBetweenField);
		Label timeForLabel = setUpLabel(getResources().getString("EnterTimeFor"));
		TextField timeForField = setUpTextInput(timeFor);
		timeForLabel.setLabelFor(timeForField);
		Label numEnemiesLabel = setUpLabel(getResources().getString("EnterNumberEnemies"));
		TextField numEnemiesField = setUpTextInput(numEnemies);
		numEnemiesLabel.setLabelFor(numEnemiesField);
		Label enemyLabel = setUpLabel(getResources().getString("EnterEnemyName"));
		ComboBox<String> enemyBox = setUpStringComboBox(myEntities, enemy);
		enemyLabel.setLabelFor(enemyBox);
		Label spawnLabel = setUpLabel(getResources().getString("EnterSpawnPoint"));
		ComboBox<String> spawnBox = setUpStringComboBox(mySpawnPoints, spawn);
		spawnLabel.setLabelFor(spawnBox);
		Button finish = setUpFinishButton(nameField, timeBetweenField, timeForField, numEnemiesField, 
				enemyBox, spawnBox, v);
		v.getChildren().addAll(nameLabel, nameField, timeBetweenLabel, timeBetweenField, timeForLabel, 
				timeForField, numEnemiesLabel, numEnemiesField, enemyLabel, enemyBox, spawnLabel, spawnBox,
				finish);
		return v;
	}
	
	private Button setUpFinishButton(TextField nameField, TextField timeBetweenField, TextField timeForField,
			TextField numEnemiesField, ComboBox<String> enemyBox, ComboBox<String> spawnBox, VBox root) {
		Button finish = new Button(getResources().getString("Finish"));
		finish.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				WaveData wave = new WaveData();
				try {
					wave.setName(nameField.getText());
					wave.setTimeBetweenEnemy(timeBetweenField.getText());
					wave.setTimeForWave(timeForField.getText());
					wave.setNumEnemies(numEnemiesField.getText());
					wave.setWaveEnemy(enemyBox.getValue());
					wave.setSpawnPointName(spawnBox.getValue());
				} catch(Exception e){
					showError(e.getMessage());
					return;
				}
				myContainer.createNewWave(nameField.getText(), wave);
				getTilePane().getChildren().remove(root);
				getObservableList().add(nameField.getText());
			}
		});
		return finish;
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
		}else if(c instanceof EntityDataContainer){
			myEntities.clear();
			for (String enemyName: ((EntityDataContainer) c).getEntityDataMap().keySet()){
				myEntities.add(enemyName);
			}
		}
	}
}
