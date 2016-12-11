package gamePlayerView.GUIPieces.InfoBoxes;
import java.awt.List;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        myWindow = new Scene(myRoot, 220,200);
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
    }
    
    public void handleKeyInput(KeyCode code) {
        switch(code){
            case P:
                init();
                break;
            case SPACE:
                ErrorPopup e = new ErrorPopup("Hey! It's a popup!");
                break;
            default:
                return;
        }
    }
    
    private VBox addButtons(){
        VBox buttonList = new VBox();
        buttonList.setSpacing(10);
        buttonList.setLayoutX(10);
        buttonList.setLayoutY(10);
        buttonList.getChildren().add(makeButton("RESUME"));
        buttonList.getChildren().add(makeButton("CONTROLS"));
        buttonList.getChildren().add(makeButton("HELP"));
        
        return buttonList;
    }
    
    public Button makeButton(String text){
        Button newButton = new Button(text);
        newButton.setPrefWidth(200);
        newButton.setPrefHeight(50);
        newButton.setStyle("-fx-font: 22 arial; -fx-base: #6de894;");
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