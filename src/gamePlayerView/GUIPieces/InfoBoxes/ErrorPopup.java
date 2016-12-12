package gamePlayerView.GUIPieces.InfoBoxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorPopup {
    private Pane myRoot;
    private Stage myStage;
    private ControlsMenu controls;
    private Scene myWindow;
    private String paneCSS = "-fx-font: 22 arial; -fx-base: #6de894;";
    
    public ErrorPopup(String aErrorMessage){
        myRoot = new Pane();
        myStage = new Stage();
        myStage.setTitle("ERROR");
        myWindow = new Scene(myRoot, 220,100);
        myWindow.setFill(Color.DODGERBLUE);
        myStage.setScene(myWindow);
        addText(aErrorMessage);
        addReturnButton();
        init();
    }
    
    public void init(){
        myStage.show();
    }
    
    public void addText(String aErrorMessage){
        Text error = new Text();
        error.setText(aErrorMessage);
        error.setX(15);
        error.setY(25);
        error.setStyle(paneCSS);
        myRoot.getChildren().add(error);
    }
    
    public void addReturnButton(){
        Button myButton = new Button();
        myButton.setText("MAIN MENU");
        myButton.setPrefWidth(200);
        myButton.setPrefHeight(50);
        myButton.setLayoutY(40);
        myButton.setStyle(paneCSS);
        myButton.setAlignment(Pos.CENTER);
        myButton.layoutXProperty().bind(myWindow.widthProperty().divide(2).subtract(myButton.getPrefWidth()/2));
        myButton.layoutYProperty().bind(myWindow.heightProperty().subtract(myButton.getPrefHeight()+10));

        myRoot.getChildren().add(myButton);
    }
}
