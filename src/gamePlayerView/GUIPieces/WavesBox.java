package gamePlayerView.GUIPieces;

import engine.IObservable;
import engine.IObserver;
import gamePlayerView.interfaces.IWavesAcceptor;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Waves text box and Label
 */

public class WavesBox extends InfoBox implements IObserver,IWavesAcceptor {
	
	public WavesBox(){
		myDisplay=makeDisplay("Waves: ");
	}

	public void acceptWaves(IObservable aObservable) {
		aObservable.attach(this);
	}

	@Override
	public void update(Object aChangedObject) {
		myOutput.setText(aChangedObject.toString());
	}

}
