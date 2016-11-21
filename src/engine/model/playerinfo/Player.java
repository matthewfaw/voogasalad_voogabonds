package engine.model.playerinfo;

import java.util.Observable;
import engine.model.resourcestore.IMoney;
 


public class Player extends Observable implements IViewablePlayer, IModifiablePlayer{
	private int myID;
	private int myLives;
	private IMoney myMoney;
	
	public Player(int ID, int initLives, IMoney startingMoney) {
		myID = ID;
		myLives = initLives;
		myMoney = startingMoney;
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
	public void updateAvailableMoney(int deltaValue) {
		myMoney.updateValue(deltaValue);
		setChanged();
		notifyObservers(1);
	}
	
	@Override
	public int getLivesRemaining() {
		return myLives;
	}
	
	@Override
	public IMoney getAvailableMoney() {
		return myMoney;
	}
	@Override
	public int getID(){
		return myID;
	}
}
