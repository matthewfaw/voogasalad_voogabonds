package gamePlayerView.interfaces;

import engine.IObservable;

/**
 * LivesBox interface utilised by the router.
 */

public interface ILivesAcceptor{
	
 /**
 * Allows router to establish connection between backend and frontend object 
 */
	public void acceptLives(IObservable aObservable);
	
}