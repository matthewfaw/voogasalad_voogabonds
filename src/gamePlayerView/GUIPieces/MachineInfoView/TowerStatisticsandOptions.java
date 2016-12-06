package gamePlayerView.GUIPieces.MachineInfoView;

import authoring.model.TowerData;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TowerStatisticsandOptions {
	private VBox myDisplay;
	
	public TowerStatisticsandOptions(TowerData tower){
		myDisplay= new VBox();
		myDisplay=makeDisplay(tower);
	}

	private VBox makeDisplay(TowerData tower) {
		VBox vbox=new VBox();
		HBox towerStats=makeTowerStats();
		TargetingButtons targetingButtons=new TargetingButtons();
		vbox.getChildren().add(towerStats);
		vbox.getChildren().add(targetingButtons.getView());
		return vbox;
		
	}

	private HBox makeTowerStats() {
		HBox towerStats=new HBox();
		EnemiesKilledBox enemieskilled=new EnemiesKilledBox();
		//OTHER STATS
		towerStats.getChildren().addAll(enemieskilled.getView()/*,valueofTower*/);
		return towerStats;
	}
}
