package gamePlayerView.GUIPieces.InfoBoxes;

import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.IPlayerAcceptor;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Waves text box and Label
 */

public class WavesBox extends InfoBox implements IObserver<IViewablePlayer>,IPlayerAcceptor {
	
	public WavesBox(){
		myDisplay=makeDisplay("Waves: ");
	}

	public void acceptPlayer(IObservable aObservable) {
		aObservable.attach(this);
	}
	@Override
	public void update(IViewablePlayer aChangedObject) {
		// TODO Auto-generated method stub
		
	}

}
