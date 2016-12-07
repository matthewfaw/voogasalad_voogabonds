package gamePlayerView.GUIPieces;

import engine.controller.ApplicationController;
import gamePlayerView.interfaces.IButtonMaker;
import gamePlayerView.interfaces.IGUIPiece;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GamePlayOptions implements IGUIPiece,IButtonMaker {
	private VBox myGamePlayOptions;
	private ApplicationController myAppController;
	
	public GamePlayOptions(ApplicationController applicationcontroller){
		myGamePlayOptions=makeGamePlayButtons();
		myAppController=applicationcontroller;
	}
	
	private VBox buildVbox() {
		VBox vbox=new VBox();
		//
		return vbox;
	}
	
	private VBox makeGamePlayButtons() {
		Button b1= makeButton("Play");
		b1.setOnAction(e->myAppController.onPlayButtonPressed());
		//b1.setOnAction(e->b1.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%)");
		Button b2= makeButton("Pause");
		b2.setOnAction(e->myAppController.onPauseButtonPressed());
		Button b3= makeButton("Fast-Forward");
		b3.setOnAction(e->myAppController.onFastButtonPressed());
		Button b4= makeButton("Save");
		b4.setOnAction(e->myAppController.onSavePressed());
		VBox vbox=new VBox();
		vbox.setPrefWidth(100);
		vbox.setMaxHeight(700);
	    vbox.setPadding(new Insets(10, 10,10, 10));
	    vbox.setSpacing(10);
	    vbox.setStyle("-fx-background-color: #336699;");
		vbox.getChildren().addAll(b1,b2,b3,b4);
		return vbox;
	}
	
	private Button makeButton(String string) {
		Button b= new Button(string);
		b.setPrefSize(80, 20);
		//b.setId(value);
		b.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%)");
		return b;
	}

	@Override
	public Node getView() {
		return myGamePlayOptions;
	}

	@Override
	public Button makeButton() {
		// TODO Auto-generated method stub
		return null;
	}
}
