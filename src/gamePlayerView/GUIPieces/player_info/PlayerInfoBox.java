package gamePlayerView.GUIPieces.player_info;

import java.util.ResourceBundle;

import gamePlayerView.GUIPieces.InfoBoxes.DisplayBoxFactory;
import gamePlayerView.GUIPieces.InfoBoxes.InfoBox;
import gamePlayerView.interfaces.IGUIPiece;
import gamePlayerView.interfaces.IPlayerAcceptor;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class will contain player info statistics such as
 * lives left, cash, player ID, etc.
 * @author alanguo
 *
 */
public class PlayerInfoBox extends VBox implements IGUIPiece{
//	InfoBox myWallet=myDisplayBoxFactory.createBox(myResourceBundle.getString("Cash"));
//	InfoBox myLife=myDisplayBoxFactory.createBox(myResourceBundle.getString("Lives"));
	
	private InfoBox myWallet;
	private InfoBox myLife;
	private ResourceBundle myResourceBundle;
	private DisplayBoxFactory myDisplayBoxFactory;
	
	public PlayerInfoBox(ResourceBundle resources, DisplayBoxFactory displayBoxFactory) {
		myResourceBundle = resources;
		myDisplayBoxFactory = displayBoxFactory;
		constructPlayerInfoBox();
	}
	
	private void constructPlayerInfoBox() {
		getChildren().add(createWalletInfo().getNode());
		getChildren().add(createLivesInfo().getNode());
	}

	public InfoBox createWalletInfo() {
		return myDisplayBoxFactory.createBox(myResourceBundle.getString("Cash"));
	}
	
	public InfoBox createLivesInfo() {
		return myDisplayBoxFactory.createBox(myResourceBundle.getString("Lives"));
	}
	
	public InfoBox getMyWallet() {
		return myWallet;
	}
	
	public InfoBox getMyLives() {
		return myLife;
	}

	@Override
	public Node getNode() {
		return this;
	}
}
