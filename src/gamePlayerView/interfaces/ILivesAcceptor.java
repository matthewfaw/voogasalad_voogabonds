package gamePlayerView.interfaces;

import engine.IObservable;
import engine.model.playerinfo.IViewablePlayer;


/**
 * @author Guhan Muruganandam
 */

/**
 * LivesBox interface utilised by the router.
 */

public interface ILivesAcceptor{
	
 /**
 * Allows router to establish connection between backend and frontend object 
 */
	public void acceptLives(IViewablePlayer aPlayer);
	
}