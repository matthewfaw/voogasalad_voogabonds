package gamePlayerView.GUIPieces.InfoBoxes;
import java.awt.List;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class PauseMenu {
    private Pane myRoot;
    private Stage myStage;
    private ControlsMenu controls;
    private Scene myWindow;
    
    public PauseMenu(){
        myRoot = new Pane();
        myStage = new Stage();
        myStage.setTitle("Pause");
        myWindow = new Scene(myRoot, 200,200);
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
    }
    
    public void handleKeyInput(KeyCode code) {
        switch(code){
            case P:
                init();
                break;
            default:
                return;
        }
    }
    
    private ArrayList<Button> addButtons(){
        ArrayList<Button> buttonList = new ArrayList<Button>();
        buttonList.add(makeButton("RESUME", 78, 20));
        buttonList.add(makeButton("CONTROLS", 78, 70));
        buttonList.add(makeButton("HELP", 90, 120));
        return buttonList;
    }
    
    public Button makeButton(String text, double x, double y){
        Button newButton = new Button(text);
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);
        newButton.setOnMouseClicked(e -> buttonFunction(text));
        return newButton;
    }
    
    private void buttonFunction(String text) {
        switch (text){
            case "RESUME": 
                kill();
                break;
            case "CONTROLS":
                controls = new ControlsMenu();
                kill();
                controls.init();
            default:
                break;
        }
    }
    public void init(){
        myRoot.getChildren().addAll(addButtons());
        myStage.show();
        
    }
    
    //method to get controls
    
    public void kill(){
        myStage.close();
    }
}