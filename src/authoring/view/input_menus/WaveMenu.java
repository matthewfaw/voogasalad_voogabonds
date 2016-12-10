package authoring.view.input_menus;

import java.util.ResourceBundle;

import authoring.model.WaveData;
import authoring.view.tabs.WaveTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Deprecated
public class WaveMenu {
	private static final int SIZE = 500;
	
	private ResourceBundle myResources;
	private WaveTab myTab;
	private Stage myWaveWindow;
	private MenuHelper myHelper;
	private TextField myNameField;
	private TextField myTimeBetweenField;
	private TextField myTimeForField;
	private TextField myNumberField;
	private ComboBox<String> myEnemyBox;
	private ComboBox<String> mySpawnBox;
	private boolean myIsDefault;
	
	public WaveMenu(ResourceBundle resources, WaveTab tab){
		myResources = resources;
		myTab = tab;
		myHelper = new MenuHelper(myResources);
	}
	
	public void createWaveWindow(String nameVal, String timeBetweenVal, String timeForVal, String numberVal,
			String enemyVal, String spawnVal, boolean isDefault){
//		if (myTab.getEnemies().size() == 0){
//			myHelper.showError(myResources.getString("NoEnemiesError"));
//			return;
//		}
//		if (myTab.getSpawnPoints().size() == 0){
//			myHelper.showError(myResources.getString("NoSpawnPointsError"));
//			return;
//		}
		myIsDefault = isDefault;
		myWaveWindow = new Stage();
		myWaveWindow.initModality(Modality.APPLICATION_MODAL);
		VBox root = new VBox();
		setUpWaveScreen(root, nameVal, timeBetweenVal, timeForVal, numberVal, enemyVal, spawnVal);
		Scene scene = new Scene(root, SIZE, SIZE);
		myWaveWindow.setTitle(myResources.getString("AddWave"));
		myWaveWindow.setScene(scene);
		myWaveWindow.show();
	}

	private void setUpWaveScreen(VBox root, String nameVal, String timeBetweenVal, String timeForVal, 
			String numberVal, String enemyVal, String spawnVal) {
		myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
		myTimeBetweenField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterTimeBetween"),
				timeBetweenVal);
		myTimeForField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterTimeFor"), 
				timeForVal);
		myNumberField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterNumber"), numberVal);
		if (myIsDefault) {
			myEnemyBox = myHelper.setUpComboBoxUserInput(root, myResources.getString("EnterEnemyName"), 
					myTab.getEnemies().get(0), myTab.getEnemies());
			mySpawnBox = myHelper.setUpComboBoxUserInput(root, myResources.getString("EnterSpawnPoint"),
					myTab.getSpawnPoints().get(0), myTab.getSpawnPoints());
		}
		else {
			myEnemyBox = myHelper.setUpComboBoxUserInput(root, myResources.getString("EnterEnemyName"), 
					enemyVal, myTab.getEnemies());
			mySpawnBox = myHelper.setUpComboBoxUserInput(root, myResources.getString("EnterSpawnPoint"),
					spawnVal, myTab.getSpawnPoints());
		}
		setUpFinishButton(root, nameVal);
	}

	private void setUpFinishButton(VBox root, String originalName) {
		Button finishButton = new Button(myResources.getString("Finish"));
		finishButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				String name = myNameField.getCharacters().toString();
				double timeBetween = 0;
				double timeFor = 0;
				int number = 0;
				try {
					timeBetween = Double.parseDouble(myTimeBetweenField.getCharacters().toString());
					timeFor = Double.parseDouble(myTimeForField.getCharacters().toString());
					number = Integer.parseInt(myNumberField.getCharacters().toString());
				} catch(Exception e){
					myHelper.showError(myResources.getString("BadIntInput"));
					return;
				}
				String enemy = myEnemyBox.getValue();
				String spawn = mySpawnBox.getValue();
				WaveData wave = createWaveData(name, timeBetween, timeFor, number, enemy, spawn);
				if (wave == null){
					return;
				}
				myTab.removeButtonDuplicates(name);
				myTab.addButtonToDisplay(name);
				if (myIsDefault){
					myTab.getController().createNewWave(name, wave);
				}
				else {
					myTab.getController().updateWave(wave, originalName);
				}
				myWaveWindow.close();
			}
		});
		root.getChildren().add(finishButton);
	}
	
	private WaveData createWaveData(String name, double timeBetween, double timeFor, int number, 
			String enemy, String spawn){
		WaveData wave = new WaveData();
		try {
			wave.setName(name);
			wave.setTimeBetweenEnemy(timeBetween);
			wave.setTimeForWave(timeFor);
			wave.setNumEnemies(number);
			wave.setWaveEntity(enemy);
			wave.setSpawnPointName(spawn);
		} catch(Exception e) {
			myHelper.showError(e.getMessage());
			return null;
		}
		return wave;
	}
}
