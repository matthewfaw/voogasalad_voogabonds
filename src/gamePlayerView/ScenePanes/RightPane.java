package gamePlayerView.ScenePanes;

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
	private Pane myRightPane;
	private TowerColumn myTowerColumn;
	
	public RightPane(){
		myRightPane = setUpPane();
		myTowerColumn=new TowerColumn();
		myRightPane.getChildren().add(myTowerColumn.getView());
	}

	@Override
	public Pane setUpPane() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(200);
		vbox.setPrefHeight(600);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    //TODO:Export CSS to other part
	    vbox.setStyle("-fx-background-color: #778899;");
	    return vbox;
	}

	@Override
	public Node getView() {
		return myRightPane;
	}
	public TowerColumn getTowerColumn(){
		return myTowerColumn;
	}
}
