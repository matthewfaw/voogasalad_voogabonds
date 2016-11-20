package gamePlayerView;

import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author Guhan Muruganandam
 * 
 */

public class GamePlayerScene {
	
	private Stage myStage;
	
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
	
}


