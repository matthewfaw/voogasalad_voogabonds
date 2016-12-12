package gamePlayerView.GUIPieces.InfoBoxes;
import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.MainInitializer;
import main.MainMenu;
public class PauseMenu {
    private Pane myRoot;
    private Stage myStage;
    private ControlsMenu controls;
    private Scene myWindow;
    private Stage stageToKill;
    
    public PauseMenu(){
        myRoot = new Pane();
        myStage = new Stage();
        myStage.setTitle("Pause");
        myWindow = new Scene(myRoot, 220,200);
        controls = new ControlsMenu();
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
        myRoot.setStyle("-fx-base: #000000; -fx-stroke: #6de894;");
    }
    

    
    private VBox addButtons(){
        VBox buttonList = new VBox();
        buttonList.setSpacing(10);
        buttonList.setLayoutX(10);
        buttonList.setLayoutY(10);
        buttonList.getChildren().add(makeButton("RESUME"));
        buttonList.getChildren().add(makeButton("CONTROLS"));
        buttonList.getChildren().add(makeButton("MAIN MENU"));
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
                kill();
                controls.init();
            case "MAIN MENU":
                stageToKill.close();
                kill();
                try {
                    MainMenu main = new MainMenu(new MainInitializer(myStage), myStage);
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            default:
                break;
        }
    }
    public void init(){
        myRoot.getChildren().addAll(addButtons());
        myStage.show();
    }
  
    public void getControls(Controls cont){
        controls.getControlsToDisplay(cont);
    }
    //method to get controls
    
    public void kill(){
        myStage.close();
    }



    public void getStage(Stage myStage2) {
        stageToKill = myStage2;
    }
}