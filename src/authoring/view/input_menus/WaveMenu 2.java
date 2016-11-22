package authoring.view.input_menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import authoring.view.side_panel.EnemyTab;
import authoring.view.side_panel.WaveLevelTab;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WaveMenu {
	private static final int SIZE = 500;
	
	private ResourceBundle myResources;
	private WaveLevelTab myTab;
	private Stage myWaveWindow;
	private MenuHelper myHelper;
	private TextField myNumberField;
	private EnemyTab myEnemyTab;
	private ArrayList<TextField> myEnemyFields;
	private HashMap<String, Integer> myEnemyTotals;
	
	public WaveMenu(ResourceBundle resources, WaveLevelTab tab, EnemyTab enemyTab){
		myResources = resources;
		myTab = tab;
		myHelper = new MenuHelper(myResources);
		myEnemyTab = enemyTab;
		myEnemyFields = new ArrayList<TextField>();
		myEnemyTotals = new HashMap<String, Integer>();
	}
	
	public void createWaveWindow(String numberVal){
		myWaveWindow = new Stage();
		myWaveWindow.initModality(Modality.APPLICATION_MODAL);
		VBox root = new VBox();
		setUpWaveScreen(root, numberVal);
		ScrollPane pane = new ScrollPane(root);
		Scene scene = new Scene(pane, SIZE, SIZE);
		myWaveWindow.setTitle(myResources.getString("AddWave"));
		myWaveWindow.setScene(scene);
		myWaveWindow.show();
	}

	private void setUpWaveScreen(VBox root, String numberVal) {
		myNumberField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterNumber"), numberVal);
		setUpWaveEnemies(root);
	}

	private void setUpWaveEnemies(VBox root) {
		for (String enemyName: myEnemyTab.getEnemies()){
			if (myEnemyTotals.get(enemyName) == null)
				myEnemyFields.add(myHelper.setUpBasicUserInput(root, enemyName, 
						myResources.getString("DefaultEnemyValue")));
			else
				myEnemyFields.add(myHelper.setUpBasicUserInput(root, enemyName, 
						String.valueOf((int) myEnemyTotals.get(enemyName))));
		}
	}

}
