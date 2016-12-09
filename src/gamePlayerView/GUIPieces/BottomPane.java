package gamePlayerView.GUIPieces;

import engine.controller.ApplicationController;
import gamePlayerView.Styles;
import gamePlayerView.interfaces.IViewPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Guhan Muruganandam
 * 
 */

public class BottomPane implements IGUIPiece,IViewPane {
	private ApplicationController myAppController;
	private Pane myBottomPane;
	private ApplicationController myApplicationController;
	
	public BottomPane(ApplicationController aAppController){
    	myAppController = aAppController;
		myBottomPane=setUpPane();
		myBottomPane.setPrefHeight(110);
		//myApplicationController=applicationController;
	}
	
	/**
	 * Creates the object that will actually be returned
	 */
	public Pane setUpPane() {
		HBox hbox = new HBox();
		hbox.setPrefWidth(900);
		hbox.setMaxHeight(100);
	    hbox.setPadding(new Insets(10, 10,10, 10));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #993384;");
	    
	    //TODO: hbox.getChildren().addAll();

	    return hbox;
	}
	
	public Node getView() {
		return myBottomPane;
	}
}