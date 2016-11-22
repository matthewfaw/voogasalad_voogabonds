package gamePlayerView;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @author Guhan Muruganandam
 */

/**
 * Temporary main
 */

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		GamePlayerScene gamePlayerScene = new GamePlayerScene(); 
    	gamePlayerScene.init(s);
    	//Router myRouter = new Router(gamePlayerScene);
  
	}

}