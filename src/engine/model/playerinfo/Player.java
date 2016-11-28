package engine.model.playerinfo;

import java.util.Observable;
import engine.model.resourcestore.IMoney;
import engine.model.strategies.winlose.IWinLoseStrategy;
import engine.model.strategies.winlose.NeverLoseStrategy;
import engine.model.strategies.winlose.NeverWinStrategy;
 


public class Player extends Observable implements IViewablePlayer, IModifiablePlayer {
	private int myID;
	private int myLives;
	private IMoney myMoney;
	
	private IWinLoseStrategy myWinCon;
	private IWinLoseStrategy myLoseCon;
	
	public Player(int ID, int initLives, IMoney startingMoney) {
		myID = ID;
		myLives = initLives;
		myMoney = startingMoney;
		
		myLoseCon = new NeverLoseStrategy(this);
		myWinCon = new NeverWinStrategy(this);
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

	@Override
	public void win() {
		// TODO Auto-generated method stub
		//Presumably tell anyone who cares that you won so they can end the game
		
	}

	@Override
	public void lose() {
		// TODO Auto-generated method stub
		//Presumably delete anything that belongs to you and tell anyone who cares that you lost
		
	}
}
