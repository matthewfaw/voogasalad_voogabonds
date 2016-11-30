package engine.model.resourcestore;

import java.util.List;

import authoring.model.TowerData;

/**
 * Interface for viewable store
 */
public interface IViewableStore {
	public List<TowerData> getAvailableTowers();
}
