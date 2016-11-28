package gamePlayerView.GUIPieces;

import engine.IObservable;
import engine.IObserver;
import gamePlayerView.interfaces.ICashAcceptor;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Cash text box and Label
 */
public class CashBox extends InfoBox implements IObserver,ICashAcceptor {
	
	public CashBox(){
		myDisplay=makeDisplay("Cash: ");
	}

	public void acceptCash(IObservable aObservable) {
		aObservable.attach(this);
	}

	@Override
	public void update(Object aChangedObject) {
		myOutput.setText(aChangedObject.toString());
	}

}
