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
 * This class is the store that keep all available resources
 * @author Owen Chung
 */
public class ResourceStore implements IModifiableStore, IViewableStore, Observer{
	private TowerUpgradeStore myUpgradeStore;
	private ObservableList<Tower> myAvailableTowers;
	private ObservableList<Tower> myAffordableTowers;
	private Player myPlayer;
	
	public ResourceStore(Player storecustomer){
		myUpgradeStore = new TowerUpgradeStore();
		myAvailableTowers = FXCollections.observableArrayList(myUpgradeStore.getAvailableTowers());
		initAffordableTowers();
		myPlayer = storecustomer;
		myPlayer.addObserver(this);
	}
	private void initAffordableTowers(){
		myAffordableTowers = FXCollections.observableArrayList();
		for (Tower tower : myAvailableTowers){
			if (myUpgradeStore.getPrice(tower) <= myPlayer.getAvailableFunds()){
				myAffordableTowers.add(tower);
			}
		}
	}
	
	private void updateAffordableTowers() {
		for (Tower tower : myAvailableTowers){
			if (myUpgradeStore.getPrice(tower) <= myPlayer.getAvailableFunds() && !myAffordableTowers.contains(tower)){
				myAffordableTowers.add(tower);
			}
			else if (myUpgradeStore.getPrice(tower) > myPlayer.getAvailableFunds() && myAffordableTowers.contains(tower)){
				myAffordableTowers.remove(tower);
			}
		}
	}
	
	@Override
	public void updatePlayerMoney(int deltaFunds) {
		myPlayer.updateAvailableFunds(deltaFunds);
	}

	@Override
	public List<Tower> getAvailableTowers() {
		return myAvailableTowers;
	}
	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals(1) && o.equals(myPlayer)){
			updateAffordableTowers();
		}
		
		
	}


}
