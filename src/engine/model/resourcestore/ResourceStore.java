package engine.model.resourcestore;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import engine.model.machine.tower.Tower;
import engine.model.machine.tower.TowerUpgradeStore;
import engine.model.playerinfo.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is the store that keeps all available resources
 * @author Owen Chung
 */
public class ResourceStore implements IModifiableStore, IViewableStore, Observer {
	private TowerUpgradeStore myUpgradeStore;
	private ObservableList<Tower> myBaseTowers;
	private ObservableList<Tower> myAffordableTowers;
	private Player myPlayer;
	
	public ResourceStore(Player storecustomer) {
		myUpgradeStore = new TowerUpgradeStore();
		myBaseTowers = FXCollections.observableArrayList(myUpgradeStore.getBaseTowers());
		initAffordableTowers();
		myPlayer = storecustomer;
		myPlayer.addObserver(this);
	}
	
	private void initAffordableTowers() {
		myAffordableTowers = FXCollections.observableArrayList();
		for (Tower tower : myBaseTowers) {
			if (!myPlayer.getAvailableMoney().isLessThan(myUpgradeStore.getPrice(tower)) ) {
				myAffordableTowers.add(tower);
			}
		}
	}
	
	private void updateAffordableTowers() {
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
	}
	
	@Override
	public void updatePlayerMoney(int deltaMoney) {
		myPlayer.updateAvailableMoney(deltaMoney);
	}

	@Override
	public List<Tower> getAvailableTowers() {
		return myBaseTowers;
	}
	
	@Override
	public List<Tower> getAffordableTowers() {
		return myAffordableTowers;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals(1) && o.equals(myPlayer)) {
			updateAffordableTowers();
		}
		
	}
	
	@Override
	public void addBaseTowers(Tower toAdd) {
		myBaseTowers.add(toAdd);
	}
	
	@Override
	public void removeBaseTowers(Tower toRemove) {
		myBaseTowers.remove(toRemove);
	}


}
