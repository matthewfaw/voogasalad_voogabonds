package gamePlayerView.interfaces;

import engine.IObservable;
import engine.model.playerinfo.IViewablePlayer;


/**
 * @author Guhan Muruganandam
 */

/**
 * CashBox interface utilised by the router.
 */

public interface ICashAcceptor{
	
 /**
 * Allows router to establish connection between backend and frontend object 
 */
	public void acceptCash(IObservable<IViewablePlayer> aPlayer);
	
}
