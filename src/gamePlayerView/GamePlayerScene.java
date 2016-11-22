package gamePlayerView;

import gamePlayerView.GUIPieces.CashBox;
import gamePlayerView.GUIPieces.LivesBox;
import gamePlayerView.GUIPieces.WavesBox;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * @author Guhan Muruganandam
 * 
 */

public class GamePlayerScene {
	
	private Stage myStage;
	private BuildGUI gui;
	
	public GamePlayerScene(){
		//myStage=stage;
		//init();
	}

	public void init(Stage s) {
		BuildGUI gui=new BuildGUI();
		Scene myScene=gui.build(s);
		setScene(s,myScene);
	}

	private void setScene(Stage s, Scene scene) { ///public or private
		s.setScene(scene);
		s.show();
	}

	public CashBox getCash() {
		return gui.getCash();
	}

	public LivesBox getLives() {
		return gui.getLives();
	}

	public WavesBox getWaves() {
		return gui.getWaves();
	}
	
}


