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
public class ResourceStore implements IModifiableStore, IViewableStore, Observer{
	private TowerUpgradeStore myUpgradeStore;
	private ObservableList<Tower> myBaseTowers;
	private ObservableList<Tower> myAffordableTowers;
	private Player myPlayer;
	
	public ResourceStore(Player storecustomer){
		myUpgradeStore = new TowerUpgradeStore();
		myBaseTowers = FXCollections.observableArrayList(myUpgradeStore.getBaseTowers());
		initAffordableTowers();
		myPlayer = storecustomer;
		myPlayer.addObserver(this);
	}
	
	private void initAffordableTowers(){
		myAffordableTowers = FXCollections.observableArrayList();
		for (Tower tower : myBaseTowers){
			if (myUpgradeStore.getPrice(tower) <= myPlayer.getAvailableFunds()){
				myAffordableTowers.add(tower);
			}
		}
	}
	
	private void updateAffordableTowers() {
		for (Tower tower : myBaseTowers){
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
		return myBaseTowers;
	}
	
	@Override
	public List<Tower> getAffordableTowers() {
		return myAffordableTowers;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals(1) && o.equals(myPlayer)){
			updateAffordableTowers();
		}
		
	}
	
	@Override
	public void addBaseTowers(Tower toAdd){
		myBaseTowers.add(toAdd);
	}
	
	@Override
	public void removeBaseTowers(Tower toRemove){
		myBaseTowers.remove(toRemove);
	}


}
