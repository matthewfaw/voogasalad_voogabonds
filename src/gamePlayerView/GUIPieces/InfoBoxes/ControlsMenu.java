package gamePlayerView.GUIPieces.InfoBoxes;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Enumeration;
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
        myWindow = new Scene(myRoot, 400,400);
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
        //can't make a new controls here... have to get from gameplayer
        myControls = new Controls();
        buttons = new VBox();
        labels = new VBox();
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
        apply.setLayoutY(330);
        apply.setText("APPLY");
        apply.setStyle(buttonCSS);
        apply.setOnMouseClicked(e -> saveControls());

        myRoot.getChildren().add(apply);
    }
    
    
    private void saveControls () {
//        ArrayList<String> newControls = new ArrayList<String>();
//        for(int i = 0; i < buttons.getChildren().size(); i++){
//            newControls.add(buttons.getChildren().get(i).toString().substring(48));
//            newControls.set(i, newControls.get(i).substring(0, newControls.get(i).length()-1));
//            System.out.println(newControls.get(i));
//        }
//        myControls.setControls(newControls);
    }
    
    public void displayFunctions(){
        ArrayList<String> functions = myControls.getFunctions();
        
        for(String str : functions){
            //System.out.println(resources.getString(e.nextElement()));
            Text functionText = new Text();
            functionText.setText(str + ": ");
            functionText.setStyle(buttonCSS);
            labels.getChildren().add(functionText);
        }
        
        labels.setSpacing(35);
        labels.setLayoutX(50);
        labels.setLayoutY(30);
        myRoot.getChildren().add(labels);
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
           myButton.setText(code.getName());
           myButton.setSelected(false);
       }
    }
}