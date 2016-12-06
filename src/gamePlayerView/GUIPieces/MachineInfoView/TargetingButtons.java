package gamePlayerView.GUIPieces.MachineInfoView;

import gamePlayerView.GUIPieces.IGUIPiece;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TargetingButtons implements IGUIPiece {
	private HBox targetOptions;
	public TargetingButtons(){
		targetOptions=makeTargetingButtons();
	}
	
	private HBox makeTargetingButtons() {
		Button b1= makeButton("First");
		//b1.setOnAction(e->myAppController.onFirstPressed());
		//b1.setOnAction(e->b1.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%)");
		Button b2= makeButton("Last");
		//b2.setOnAction(e->myAppController.onFirstPressed());
		Button b3= makeButton("Strongest");
		//b3.setOnAction(e->myAppController.onFirstPressed());
		Button b4= makeButton("Weakest");
		//b4.setOnAction(e->myAppController.onFirstPressed());
		HBox hbox=new HBox();
		hbox.getChildren().addAll(b1,b2,b3,b4);
		return hbox;
	}
	
	private Button makeButton(String string) {
		Button b= new Button(string);
		b.setPrefSize(50, 20);
		//b.setId(value);
		//b.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%)");
		return b;
	}

	@Override
	public Node getView() {
		return targetOptions;
	}
}
