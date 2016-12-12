package gamePlayerView.ScenePanes;

import java.util.Collection;

import engine.controller.ApplicationController;
import gamePlayerView.GUIPieces.GamePlayOptions;
import gamePlayerView.GUIPieces.InfoBoxes.CashBox;
import gamePlayerView.GUIPieces.InfoBoxes.LivesBox;
//import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.IGUIPiece;
import gamePlayerView.interfaces.IViewPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Guhan Muruganandam
 */

public class LeftPane implements IGUIPiece,IViewPane {
	private VBox myLeftPane=new VBox();
	private CashBox myWallet;
	private LivesBox myLives;
	//private Pane myGamePlayer; 
	
	public LeftPane(){
		//myGamePlayer=gamePlayer;
		setUpPane();
	//	GamePlayOptions myGamePlayOptions=new GamePlayOptions(appcontrol);
	//	myWallet=new CashBox();
	//	myLives=new LivesBox();
	//	myLeftPane.getChildren().addAll(myGamePlayOptions.getView(),myWallet.getView(),myLives.getView()); ///////////////////// 
	}
	public void setUpPane() {
		//myLeftPane.setPrefWidth(myGamePlayer.getWidth()*0.1);
		//myLeftPane.setPrefHeight(myGamePlayer.getHeight());
		myLeftPane.setPrefWidth(100);
		myLeftPane.setMaxHeight(700);
	    myLeftPane.setPadding(new Insets(10, 10,10, 10));
	    myLeftPane.setSpacing(10);
	    myLeftPane.setStyle("-fx-background-color: #336699;");
	}
	
	public void add(Collection<Node> collection){
		for(Node n:collection){
			myLeftPane.getChildren().add(n);
		}
	}
	@Override
	public Node getView() {
		return myLeftPane;
	}
	//public CashBox getCash() {
		//return myWallet;
	//}
	//public LivesBox getLives() {
		//return myLives;
	//}
	@Override
	public void clear() {
		myLeftPane.getChildren().clear();
	}
	
}
