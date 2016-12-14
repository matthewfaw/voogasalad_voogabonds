package gamePlayerView.ScenePanes;

import java.util.Collection;

import gamePlayerView.interfaces.IGUIPiece;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TopPane implements IViewPane{
	private HBox myHBox;
	
	public TopPane () {
		myHBox = new HBox();
	}
	
	@Override
	public void setUpPane() {
		myHBox.setPrefWidth(900);
		myHBox.setPrefHeight(50);
		myHBox.setPadding(new Insets(10, 10,10, 10));
		myHBox.setSpacing(10);
		myHBox.setStyle("-fx-background-color: #914484;");
	}

	@Override
	public void add(Collection<Node> collection) {
		myHBox.getChildren().addAll(collection);
	}

	@Override
	public void clear() {
		myHBox.getChildren().clear();
	}

	@Deprecated
	@Override
	public Node getView() {
		return myHBox;
	}

	@Override
	public Node getNode() {
		return myHBox;
	}

}
