package gamePlayerView.GUIPieces;

import engine.IObservable;
import engine.IObserver;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Cash text box and Label
 */
public class CashBox extends InfoBox implements IObserver {
	
	public CashBox(){
		myDisplay=makeDisplay("Cash: ");
	}

	public void giveObject(IObservable aObservable) {
		aObservable.attach(this);
	}

	@Override
	public void update(Object aChangedObject) {
		myOutput.setText(aChangedObject.toString());
	}

}
