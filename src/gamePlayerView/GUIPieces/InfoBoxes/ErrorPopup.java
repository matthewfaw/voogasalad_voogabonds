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
    private ControlsMenu myControlsMenu;
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
        Button button = new Button();
        button.setText("MAIN MENU");
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setLayoutY(40);
        button.setStyle(paneCSS);
        button.setAlignment(Pos.CENTER);
        button.layoutXProperty().bind(myWindow.widthProperty().divide(2).subtract(button.getPrefWidth()/2));
        button.layoutYProperty().bind(myWindow.heightProperty().subtract(button.getPrefHeight()+10));

        myRoot.getChildren().add(button);
    }
}
