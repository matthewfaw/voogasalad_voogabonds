package player.map;

import gamePlayerView.MapDisplay;
import javafx.application.Application;
import javafx.stage.Stage;

public class MapMain extends Application{
    private MapDisplay md1;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        md1 = new MapDisplay();
        md1.init();
    }
    
    
}
