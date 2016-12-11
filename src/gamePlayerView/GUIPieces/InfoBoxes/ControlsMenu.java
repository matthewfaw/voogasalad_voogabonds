package gamePlayerView.GUIPieces.InfoBoxes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControlsMenu {
    private Pane myRoot;
    private Stage myStage;
    private Scene myWindow;
    
    public ControlsMenu(){
        myRoot = new Pane();
        myStage = new Stage();
        myStage.setTitle("Select Controls");
        myWindow = new Scene(myRoot, 400,400);
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
    }
    
    public void init(){
        myStage.show();
    }
}
