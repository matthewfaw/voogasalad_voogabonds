package gamePlayerView.ScenePanes;

import java.util.Collection;


//import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.IGUIPiece;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * @author Guhan Muruganandam
 */

public class LeftPane implements IViewPane {
	private VBox myVBox;
	
	public LeftPane(){
		myVBox = new VBox();
	}
	public void setUpPane() {
		myVBox.setPrefWidth(100);
		myVBox.setMaxHeight(700);
		myVBox.setPadding(new Insets(10, 10,10, 10));
		myVBox.setSpacing(10);
		myVBox.setStyle("-fx-background-color: #336699;");
	}
	
	public void add(Collection<Node> collection){
		myVBox.getChildren().addAll(collection);
	}
	
	@Override
	public Node getView() {
		return myVBox;
	}
	
	@Override
	public void clear() {
		myVBox.getChildren().clear();
	}
	
	@Override
	public Node getNode() {
		return myVBox;
	}
	
}
