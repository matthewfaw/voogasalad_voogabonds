package engine.model.game_environment.distributors;

import engine.model.components.PhysicalComponentData;
import engine.model.entities.EntityData;
import utility.Point;

public interface IDistributor {

	/**
	 * attemts to construct the object specified by EnemyData and distribute it to a destination
	 * @param aEntityData: the data corresponding to the object to distribute
	 * @param aPhysicalComponentData: the map-embedding specific data
	 * @param aLocation: a location to place the item
	 * @return true if the item was successfully placed, false otherwise
	 */
	public boolean distribute(EntityData aEntityData, PhysicalComponentData aPhysicalComponentData, Point aLocation);
}