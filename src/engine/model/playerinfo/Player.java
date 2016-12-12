package engine.model.playerinfo;

import java.util.ArrayList;
import java.util.List;

import authoring.model.EntityData;
import authoring.model.PlayerData;
import authoring.model.TowerData;
import engine.IObserver;
import engine.model.resourcestore.IMoney;
import engine.model.resourcestore.Money;
import engine.model.resourcestore.ResourceStore;
import engine.model.strategies.winlose.IWinLoseStrategy;
import engine.model.strategies.winlose.NeverLoseStrategy;
import engine.model.strategies.winlose.NeverWinStrategy;
 
/**
 * 
 * @author owenchung 
 * @author matthewfaw
 *
 */
//TODO: Implement all unimplemented methods
public class Player implements IModifiablePlayer, IViewablePlayer {
	private int myID;
	private int myLives;
	private Money myMoney;
	
	private transient List<ResourceStore> myResourceStores;
	
	private transient IWinLoseStrategy myWinCon;
	private transient IWinLoseStrategy myLoseCon;
	
	private transient List<IObserver<IViewablePlayer>> myObservers;
	
	public Player(PlayerData aPlayerData)
	{
		//TODO: Create Unique ID?
		this(0,aPlayerData.getStartingLives(), new Money(aPlayerData.getStartingCash()));
	}
	private Player(int ID, int initLives, Money startingMoney) {
		myID = ID;
		myLives = initLives;
		myMoney = startingMoney;
		
		//TODO: Get win and lose conditions from PlayerData
		myLoseCon = new NeverLoseStrategy(this);
		myWinCon = new NeverWinStrategy(this);
		
		myResourceStores = new ArrayList<ResourceStore>();
		myObservers = new ArrayList<IObserver<IViewablePlayer>>();
	}
	 
	@Override
	public void updateLivesRemaining(int deltaLives) {
		myLives = myLives + deltaLives;
		notifyObservers();
	}
	
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
	@Deprecated
	public List<EntityData> getAvailableTowers() {
		List<EntityData> towerList = new ArrayList<EntityData>();
		for (ResourceStore resourceStore: myResourceStores) {
			towerList.addAll(resourceStore.getAvailableEntities());
		}
		return towerList;
	}

	@Override
	@Deprecated
	public List<EntityData> getAffordableTowers() {
		List<EntityData> towerList = new ArrayList<EntityData>();
		for (EntityData towerData: getAvailableTowers()) {
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
