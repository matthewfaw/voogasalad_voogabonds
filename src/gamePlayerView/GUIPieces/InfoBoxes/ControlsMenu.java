package gamePlayerView.GUIPieces.InfoBoxes;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import gamePlayerView.GUIPieces.InfoBoxes.Controls;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class ControlsMenu{
    private Pane myRoot;
    private Stage myStage;
    private Scene myWindow;
    private VBox buttons;
    private VBox labels;
    private Controls myControls;
    private String buttonCSS = "-fx-font: 22 arial; -fx-base: #6de894;";
    
    public ControlsMenu(){
        myRoot = new Pane();
        myStage = new Stage();
        myStage.setTitle("Select Controls");
        myWindow = new Scene(myRoot, 400,450);
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
        //can't make a new controls here... have to get from gameplayer
        myControls = new Controls();
        buttons = new VBox();
        labels = new VBox();
        myRoot.setStyle("-fx-base: #000000; -fx-stroke: #6de894;");
    }
    
    public void init(){
        displayFunctions();
        displayControls();
        addApplyButton();
        myStage.show();
    }


    private void addApplyButton () {
        Button apply = new Button();
        apply.setLayoutX(150);
        apply.setLayoutY(385);
        apply.setText("APPLY");
        apply.setStyle(buttonCSS);
        apply.setOnMouseClicked(e -> saveControls());

        myRoot.getChildren().add(apply);
    }
    
    
    private void saveControls () {
        
        myControls.setControls(myControls.getControls());
        kill();
//        System.out.println(myControls.getFunctions());
//        System.out.println(myControls.getControls());
    }
    
    public void displayFunctions(){
        ArrayList<String> functions = myControls.getFunctions();
        
        for(String str : functions){
            //System.out.println(resources.getString(e.nextElement()));
            Text functionText = new Text();
            functionText.setText(str + ": ");
            functionText.setStyle("-fx-fill: white; -fx-font: 22 arial;  ");
            labels.getChildren().add(functionText);
        }
        
        labels.setSpacing(35);
        labels.setLayoutX(50);
        labels.setLayoutY(30);
        myRoot.getChildren().add(labels);
    }
    
    public void kill(){
        myRoot.getChildren().clear();
        labels.getChildren().clear();
        buttons.getChildren().clear();
        myStage.close();
    }
    
    public void displayControls(){
        ArrayList<String> controls = myControls.getControls();
        
        for(String str : controls){
            //System.out.println(resources.getString(e.nextElement()));
            ToggleButton myButton = new ToggleButton();
            myButton.setText(str);
            myButton.setStyle(buttonCSS);
            myButton.setPrefWidth(200);
            myButton.setPrefHeight(50);
            myButton.setOnMouseClicked(e -> setControls(myButton));
            buttons.getChildren().add(myButton);
        }
        
        buttons.setSpacing(10);
        buttons.setLayoutX(150);
        buttons.setLayoutY(20);
        myRoot.getChildren().add(buttons);
    }
    private void setControls(ToggleButton myButton) {
            myWindow.setOnKeyPressed(e -> 
                handleKeyInput(e.getCode(), myButton));
    }
    
    private void handleKeyInput(KeyCode code, ToggleButton myButton){
       if(myButton.isSelected()){
           myControls.setControlFor(myControls.getFunctions()
                                 .get(myControls.getControls().
                                      indexOf(myButton.getText())), code.getName());
//           System.out.println(myControls.getControlFor(myControls.getFunctions()
//                              .get(myControls.getControls()
//                               .indexOf(myButton.getText()))));
           myButton.setText(code.getName());
           myButton.setSelected(false);
           
       }
    }

    public void getControlsToDisplay(Controls cont) {
        myControls = cont;
    }
}