package authoring.view.input_menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

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
	private TextField myNameField;
	private TextField myTimeBetweenField;
	private TextField myTimeForField;
	private TextField myNumberField;
	private TextField mySpawnField;
	private boolean myIsDefault;
	
	public WaveMenu(ResourceBundle resources, WaveLevelTab tab){
		myResources = resources;
		myTab = tab;
		myHelper = new MenuHelper(myResources);
	}
	
	public void createWaveWindow(String nameVal, String timeBetweenVal, String timeForVal, String numberVal,
			boolean isDefault){
		myIsDefault = isDefault;
		myWaveWindow = new Stage();
		myWaveWindow.initModality(Modality.APPLICATION_MODAL);
		VBox root = new VBox();
		setUpWaveScreen(root, nameVal, timeBetweenVal, timeForVal, numberVal);
		Scene scene = new Scene(root, SIZE, SIZE);
		myWaveWindow.setTitle(myResources.getString("AddWave"));
		myWaveWindow.setScene(scene);
		myWaveWindow.show();
	}

	private void setUpWaveScreen(VBox root, String nameVal, String timeBetweenVal, String timeForVal, 
			String numberVal) {
		myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
		myTimeBetweenField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterTimeBetween"),
				timeBetweenVal);
		myTimeForField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterTimeFor"), 
				timeForVal);
		myNumberField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterNumber"), numberVal);
	}



}
