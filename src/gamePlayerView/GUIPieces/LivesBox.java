package gamePlayerView.GUIPieces;

import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.ILivesAcceptor;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Lives text box and Label
 */

public class LivesBox extends InfoBox implements IObserver<IViewablePlayer>, ILivesAcceptor {
	
	public LivesBox(){
		myDisplay=makeDisplay("Lives: ");
	}

	@Override
	public void acceptLives(IObservable<IViewablePlayer> aPlayer) {
		aPlayer.attach(this);
		//update(aPlayer);
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		myOutput.setText(Integer.toString(aChangedObject.getLivesRemaining()));
	}

}
