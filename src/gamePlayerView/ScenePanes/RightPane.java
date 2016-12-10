package gamePlayerView.ScenePanes;

import java.util.Collection;

import gamePlayerView.GUIPieces.TowerColumn;
import gamePlayerView.GUIPieces.InfoBoxes.CashBox;
import gamePlayerView.GUIPieces.InfoBoxes.LivesBox;
import gamePlayerView.interfaces.IGUIPiece;
import gamePlayerView.interfaces.IViewPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Guhan Muruganandam
 * 
 */

public class RightPane implements IGUIPiece,IViewPane {
	private VBox myRightPane=new VBox();
	private TowerColumn myTowerColumn;
	
	public RightPane(){
		setUpPane();
		//myTowerColumn=new TowerColumn();
		//myRightPane.getChildren().add(myTowerColumn.getView());
	}

	@Override
	public void setUpPane() {
		myRightPane.setPrefWidth(200);
		myRightPane.setPrefHeight(600);
		myRightPane.setPadding(new Insets(10));
		myRightPane.setSpacing(8);
	    //TODO:Export CSS to other part
		myRightPane.setStyle("-fx-background-color: #778899;");
	}

	@Override
	public Node getView() {
		return myRightPane;
	}
	//public TowerColumn getTowerColumn(){
		//return myTowerColumn;
	//}

	@Override
	public void add(Collection<Node> collection) {
		for(Node n:collection){
			myRightPane.getChildren().add(n);
		}
	}

	@Override
	public void clear() {
		myRightPane.getChildren().clear();
	}
}
