package engine.model.resourcestore;

import java.util.List;

import engine.model.machine.tower.Tower;

/**
 * Interface for viewable store
 */
public interface IViewableStore {
	public List<Tower> getAvailableTowers();
	public List<Tower> getAffordableTowers();
		
	
}
