package engine.model.playerinfo;
/*
import java.util.List;
import engine.model.resourcestore.IMoney;
 */


public class Player implements IViewablePlayer, IModifiablePlayer{
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
	@Override
	public void updateLivesRemaining(int deltaLives) {
		myLives = myLives + deltaLives;
		
	}
	@Override
	public void updateAvailableFunds(int deltaFunds) {
		myFunds = myFunds + deltaFunds;
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
