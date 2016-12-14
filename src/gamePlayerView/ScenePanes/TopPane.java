package gamePlayerView.ScenePanes;

import java.util.Collection;

import gamePlayerView.interfaces.IGUIPiece;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TopPane implements IGUIPiece, IViewPane{
	private HBox myTopPane;
	
	public TopPane () {
		myTopPane = new HBox();
	}
	
	@Override
	public void setUpPane() {
		myTopPane.setPrefWidth(900);
		myTopPane.setPrefHeight(50);
		myTopPane.setPadding(new Insets(10, 10,10, 10));
		myTopPane.setSpacing(10);
		myTopPane.setStyle("-fx-background-color: #914484;");
	}

	@Override
	public void add(Collection<Node> collection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getView() {
		return myTopPane;
	}

}
