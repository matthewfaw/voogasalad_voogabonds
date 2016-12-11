package gamePlayerView.GUIPieces.InfoBoxes;

import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.IScoreAcceptor;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Cash text box and Label
 */
public class ScoreBox extends InfoBox implements IObserver<IViewablePlayer>,IScoreAcceptor {
	
	public ScoreBox(){
		myDisplay=makeDisplay("Score:  ");
	}

	public void acceptScore(IObservable<IViewablePlayer> aPlayer) {
		aPlayer.attach(this);
		//update(aPlayer);
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		myOutput.setText(Integer.toString(aChangedObject.getAvailableMoney()));
	}
}
