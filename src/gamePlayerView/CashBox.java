package gamePlayerView;

import engine.IObservable;
import engine.IObserver;

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
