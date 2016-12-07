package gamePlayerView.GUIPieces.MachineInfoView;

import authoring.model.TowerData;
import gamePlayerView.interfaces.IGUIPiece;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Guhan Muruganandam
 * 
 */

public class MachineInfo implements IGUIPiece {
	private VBox myDisplay;
	
	public MachineInfo(/*TowerData tower*/){
		myDisplay= new VBox();
		myDisplay=makeDisplay(/*tower*/);
	}

	private VBox makeDisplay(/*TowerData tower*/) {
		VBox vbox=new VBox();
		Image image= new Image(tower.getImagePath());
		ImageView imageView=new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(100);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		//imageView.setCache
		
		Label machineLabel=new Label("COW"/*tower.getName()*/);
		
		vbox.getChildren().add(imageView);
		vbox.getChildren().add(machineLabel);
		
		
		return vbox;
		
	}

	@Override
	public Node getView() {
		return myDisplay;
	}

}
