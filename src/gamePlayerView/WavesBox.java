package gamePlayerView;

import engine.IObservable;
import engine.IObserver;

public class WavesBox extends InfoBox implements IObserver {
	
	public WavesBox(){
		myDisplay=makeDisplay("Waves: ");
	}

	public void giveObject(IObservable aObservable) {
		aObservable.attach(this);
	}

	@Override
	public void update(Object aChangedObject) {
		myOutput.setText(aChangedObject.toString());
	}

}
