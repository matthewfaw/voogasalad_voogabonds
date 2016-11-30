package gamePlayerView.GUIPieces;

import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.ICashAcceptor;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Cash text box and Label
 */
public class CashBox extends InfoBox implements IObserver<IViewablePlayer>,ICashAcceptor {
	
	public CashBox(){
		myDisplay=makeDisplay("Cash: ");
	}

	public void acceptCash(IObservable<IViewablePlayer> aPlayer) {
		aPlayer.attach(this);
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		myOutput.setText(Integer.toString(aChangedObject.getAvailableMoney()));
	}
}
