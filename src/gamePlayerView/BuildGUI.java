package gamePlayerView;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Guhan Muruganandam
 * 
 */

public class BuildGUI {
	
	private TowerColumn myTowerColumn;
	private StatisticsRow myStatisticsRow;
	private GridView myGridView;
	
	public BuildGUI(){
	///empty as of now
	}

	public Scene build(Stage stage) {
		Group gameplayer =new Group();
		gameplayer.getChildren().add(setScreen());
		Scene myScene =new Scene(gameplayer, 800, 800);
		return myScene;
	}
	
	public BorderPane setScreen(){
		myTowerColumn   = new TowerColumn();
		myStatisticsRow = new StatisticsRow();
		BorderPane borderpane=new BorderPane();
		borderpane.setRight(myTowerColumn.getView());
		borderpane.setBottom(myStatisticsRow.getView());
		//borderpane.setCenter(myGridView.getView());
		return borderpane;
	}
}
