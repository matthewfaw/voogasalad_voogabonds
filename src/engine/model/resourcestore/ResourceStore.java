package engine.model.resourcestore;

import java.util.List;

import authoring.model.TowerData;
<<<<<<< HEAD
import engine.model.collision_detection.ICollidable;
import engine.model.machine.tower.Tower;
||||||| merged common ancestors
import engine.model.machine.tower.Tower;
=======
>>>>>>> collision_detection
import engine.model.machine.tower.TowerUpgradeStore;

/**
 * This class is the store that keeps all available resources
 * @author Owen Chung and matthewfaw
 */
public class ResourceStore implements /*IModifiableStore, */ IViewableStore {

	private TowerUpgradeStore myUpgradeStore;
	
	public ResourceStore(List<TowerData> aTowerInfoList) {
		myUpgradeStore = new TowerUpgradeStore(aTowerInfoList);
	}
	
	@Override
	public List<TowerData> getAvailableTowers() {
		return myUpgradeStore.getBaseTowersData();
	}
	
<<<<<<< HEAD
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
	
||||||| merged common ancestors
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


=======
>>>>>>> collision_detection
}
