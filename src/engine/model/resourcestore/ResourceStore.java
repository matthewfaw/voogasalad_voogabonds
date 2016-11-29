package engine.model.resourcestore;

import java.util.ArrayList;
import java.util.List;

import authoring.model.TowerData;
import engine.model.machine.tower.Tower;
import engine.model.machine.tower.TowerUpgradeStore;
import engine.model.playerinfo.Player;

/**
 * This class is the store that keeps all available resources
 * @author Owen Chung and Matthew Faw
 */
public class ResourceStore implements IModifiableStore, IViewableStore/*, What is this observing??? IObserver*/	{

	private TowerUpgradeStore myUpgradeStore;
	private List<TowerData> myBaseTowers;
	
	//TODO matthewfaw: set up the store with a tower data list
	public ResourceStore(List<TowerData> aTowerInfoList) {
		//This needs to be completely changed. The data needs to come from the
		// tower data list
		myUpgradeStore = new TowerUpgradeStore(aTowerInfoList);
		//myBaseTowers = myUpgradeStore.getBaseTowers();
		initAffordableTowers();
	}
	
	// XXX: This should be removed. A resource store shouldn't have a concept of
	// affordable towers... It's the guy selling the towers, not buying them
	private void initAffordableTowers() {
		/*
		myAffordableTowers = new ArrayList<Tower>();
		for (Tower tower : myBaseTowers) {
			if (!myPlayer.getAvailableMoney().isLessThan(myUpgradeStore.getPrice(tower)) ) {
				myAffordableTowers.add(tower);
			}
		}
		*/
	}
	
	//XXX matthewfaw: Affordable towers aren't a property of the Resource store
	// This is player specific
	// This should be moved elsewhere
	@Deprecated
	private void updateAffordableTowers() {
		/*
		for (Tower tower : myBaseTowers) {
			if (myPlayer.getAvailableMoney().isLessThan(myUpgradeStore.getPrice(tower))) {
				if (myAffordableTowers.contains(tower)){
					myAffordableTowers.remove(tower);
				}	
			}
			else {
				if (!myAffordableTowers.contains(tower)){
					myAffordableTowers.add(tower);
				}
			}
		}
		*/
	}
	
	@Override
	public void updatePlayerMoney(int deltaMoney) {
//		myPlayer.updateAvailableMoney(deltaMoney);
	}

	@Override
	public List<TowerData> getAvailableTowers() {
		return myBaseTowers;
	}
	
	@Override
	public void addBaseTowers(Tower toAdd) {
//		myBaseTowers.add(toAdd);
	}
	
	@Override
	public void removeBaseTowers(Tower toRemove) {
		myBaseTowers.remove(toRemove);
	}

	/* TODO matthewfaw: This should probably be moved elsewhere
	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals(1) && o.equals(myPlayer)) {
			updateAffordableTowers();
		}
		
	}
	@Override
	public void update(Object aChangedObject) {
		// TODO Auto-generated method stub
		
	}
	*/

}
