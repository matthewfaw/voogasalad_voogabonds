package engine.model.playerinfo;

import java.util.ArrayList;
import java.util.List;

import authoring.model.PlayerData;
import authoring.model.TowerData;
import engine.IObserver;
import engine.model.resourcestore.IMoney;
import engine.model.resourcestore.Money;
import engine.model.resourcestore.ResourceStore;
import engine.model.strategies.winlose.IWinLoseStrategy;
import engine.model.strategies.winlose.NeverLoseStrategy;
import engine.model.strategies.winlose.NeverWinStrategy;
 

//TODO: Implement all unimplemented methods
public class Player implements IViewablePlayer, IModifiablePlayer {
	private int myID;
	private int myLives;
	private IMoney myMoney;
	
	private List<ResourceStore> myResourceStores;
	
	private IWinLoseStrategy myWinCon;
	private IWinLoseStrategy myLoseCon;
	
	private List<IObserver<IViewablePlayer>> myObservers;
	
	public Player(PlayerData aPlayerData)
	{
		//TODO: Create Unique ID?
		this(0,aPlayerData.getStartingLives(), new Money(aPlayerData.getStartingCash()));
	}
	private Player(int ID, int initLives, IMoney startingMoney) {
		myID = ID;
		myLives = initLives;
		myMoney = startingMoney;
		
		//TODO: Get win and lose conditions from PlayerData
		myLoseCon = new NeverLoseStrategy(this);
		myWinCon = new NeverWinStrategy(this);
		
		myResourceStores = new ArrayList<ResourceStore>();
		myObservers = new ArrayList<IObserver<IViewablePlayer>>();
	}
	 
	/**
	 * 0 corresponds to live change
	 */
	@Override
	public void updateLivesRemaining(int deltaLives) {
		myLives = myLives + deltaLives;
		notifyObservers();
	}
	/**
	 * 1 corresponds to funds change
	 */
	@Override
	public void updateAvailableMoney(int deltaValue) {
		myMoney.updateValue(deltaValue);
		notifyObservers();
	}
	
	@Override
	public int getLivesRemaining() {
		return myLives;
	}
	
	@Override
	public int getAvailableMoney() {
		return myMoney.getValue();
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

	@Override
	public List<TowerData> getAvailableTowers() {
		List<TowerData> towerList = new ArrayList<TowerData>();
		for (ResourceStore resourceStore: myResourceStores) {
			towerList.addAll(resourceStore.getAvailableTowers());
		}
		return towerList;
	}

	@Override
	public List<TowerData> getAffordableTowers() {
		List<TowerData> towerList = new ArrayList<TowerData>();
		for (TowerData towerData: getAvailableTowers()) {
			if (this.getAvailableMoney() > towerData.getBuyPrice()) {
				towerList.add(towerData);
			}
		}
		return towerList;
	}
	
	@Override
	public boolean isAlly(IModifiablePlayer owner) {
		return equals(owner);
	}

	@Override
	public void addResourceStore(ResourceStore aResourceStore) {
		myResourceStores.add(aResourceStore);
		notifyObservers();
	}

	@Override
	public void removeResourceStore(ResourceStore aResourceStore) {
		myResourceStores.remove(aResourceStore);
	}

	//**********************Observable interface******************//
	@Override
	public void attach(IObserver<IViewablePlayer> aObserver) {
		myObservers.add(aObserver);
	}
	@Override
	public void detach(IObserver<IViewablePlayer> aObserver) {
		myObservers.remove(aObserver);
	}
	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

}
