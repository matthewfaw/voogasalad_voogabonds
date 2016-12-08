package gamePlayerView;

import java.util.Collection;

import engine.controller.ApplicationController;
import gamePlayerView.GUIPieces.CashBox;
import gamePlayerView.GUIPieces.IGUIPiece;
import gamePlayerView.GUIPieces.LivesBox;
import gamePlayerView.interfaces.ICashAcceptor;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LeftPane implements IGUIPiece {
	private Pane myLeftPane;
	private CashBox myWallet;
	private LivesBox myLives;
	
	public LeftPane(ApplicationController appcontrol){
		myLeftPane = setUpPane();
		GamePlayOptions myGamePlayOptions=new GamePlayOptions(appcontrol);
		myWallet=new CashBox();
		myLives=new LivesBox();
		myLeftPane.getChildren().addAll(myGamePlayOptions.getView(),myWallet.getView(),myLives.getView());
	}
	private Pane setUpPane() {
		VBox vbox=new VBox();
		vbox.setPrefWidth(100);
		vbox.setMaxHeight(700);
	    vbox.setPadding(new Insets(10, 10,10, 10));
	    vbox.setSpacing(10);
	    vbox.setStyle("-fx-background-color: #336699;");
		return vbox;
	}
	@Override
	public Node getView() {
		return myLeftPane;
	}
	public CashBox getCash() {
		return myWallet;
	}
	public LivesBox getLives() {
		return myLives;
	}
	
}
