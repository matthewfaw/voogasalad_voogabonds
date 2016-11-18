package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

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
	
	public WaveLevelTab(TabPane pane) {
		screenInfo();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.waveTab = new Tab(myResources.getString("Waves"));
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
		Button setWaveOrder = new Button(myResources.getString("WaveOrder"));
		waveButtons.getChildren().addAll(addWave, setWaveOrder);
		waveArea.getChildren().addAll(currentWaves, waveButtons);
		waveTab.setContent(waveArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
