package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import authoring.controller.WaveDataController;
import authoring.view.input_menus.WaveMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WaveLevelTab extends Tab  {

	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab waveTab;
	private int screenWidth;
	private int screenHeight;
	private WaveMenu myMenu;
	private WaveDataController myController;
	
	public WaveLevelTab(TabPane pane, WaveDataController controller) {
		screenInfo();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		waveTab = new Tab(myResources.getString("Waves"));
		myMenu = new WaveMenu(myResources, this);
		myController = controller;
		waveTabOptions(waveTab);
		pane.getTabs().add(waveTab);
	}
	
	private void waveTabOptions(Tab waveTab) {
		VBox waveArea = new VBox(screenHeight*0.01);
		waveArea.setMaxHeight(screenHeight*0.88);
		ScrollPane currentWaves = new ScrollPane();
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
						myResources.getString("DefaultNumber"), true);
			}
		};
		return handler;
	}
	
	public WaveDataController getController(){
		return myController;
	}
	
}
