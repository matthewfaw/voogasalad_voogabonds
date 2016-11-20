package engine.model.playerinfo;

import java.util.Observable;

/*
import java.util.List;
import engine.model.resourcestore.IMoney;
 */


public class Player extends Observable implements IViewablePlayer, IModifiablePlayer{
	private int myID;
	private int myLives;
	private int myFunds;
	/* TODO: Implement IMoney Interface
	  private List<IMoney> myFunds;
	
	  public Player(int ID, int initLives, List<IMoney> startingfunds){
		
	}
	 */
	
	public Player(int ID, int initLives, int startingfunds){
		myID = ID;
		myLives = initLives;
		myFunds = startingfunds;
	}
	/**
	 * 0 corresponds to live change
	 */
	@Override
	public void updateLivesRemaining(int deltaLives) {
		myLives = myLives + deltaLives;
		setChanged();
		notifyObservers(0);
		
	}
	/**
	 * 1 corresponds to funds change
	 */
	@Override
	public void updateAvailableFunds(int deltaFunds) {
		myFunds = myFunds + deltaFunds;
		setChanged();
		notifyObservers(1);
	}
	
	@Override
	public int getLivesRemaining() {
		return myLives;
	}
	
	@Override
	public int getAvailableFunds() {
		return myFunds;
	}
}
