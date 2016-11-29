package gamePlayerView.interfaces;

import engine.IObservable;
import engine.model.playerinfo.IViewablePlayer;

/**
 * Just a temporary interface for an example
 * This interface will be replaced by something 
 * more meaningful in terms of the backend soon
 * @author matthewfaw
 *
 */
/*
public interface ICashAcceptor {
	public void acceptCash(IViewableCash aCash);
}
*/

/**
 * CashBox interface utilised by the router.
 */

public interface ICashAcceptor{
	
 /**
 * Allows router to establish connection between backend and frontend object 
 */
	public void acceptCash(IViewablePlayer aPlayer);
	
}
