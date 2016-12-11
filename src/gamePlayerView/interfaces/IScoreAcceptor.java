package gamePlayerView.interfaces;

import engine.IObservable;
import engine.model.playerinfo.IViewablePlayer;


/**
 * @author Guhan Muruganandam
 */

/**
 * ScoreBox interface utilised by the router.
 */

public interface IScoreAcceptor{
	
 /**
 * Allows router to establish connection between backend and frontend object 
 */
	public void acceptScore(IObservable<IViewablePlayer> aPlayer);
	
}
