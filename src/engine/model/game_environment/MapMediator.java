package engine.model.game_environment;

import java.util.List;

import engine.model.IEntity;
import engine.model.game_environment.entities.EntityManager;
import engine.model.game_environment.terrain.Terrain;
import engine.model.game_environment.terrain.TerrainMap;
import utility.Point;

public class MapMediator {
	
	private TerrainMap myTerrainMap;
	private EntityManager myEntityManager;
	
	/**
	 * Determines if an object can be placed on the map at the requested location
	 * Places the object if it can
	 * @param aLocation to place the object
	 * @param aValidTerrainList: a list of terrains the object can be placed on
	 * @return true if the entity was placed, false otherwise
	 */
	public boolean attemptToPlaceEntity(Point aLocation, IEntity aEntity)
	{
		if (myTerrainMap.hasTerrain(aEntity.getValidTerrains(), aLocation)) {
			aEntity.setLocation(aLocation);
			accept(aEntity, aLocation);
			return true;
		}
		return false;
	}
	/**
	 * Accepts entity to be tracked by map
	 * @param aEntityToTrack
	 */
	private void accept(IEntity aEntityToTrack, Point aLocation)
	{
		myEntityManager.trackEntity(aEntityToTrack)
	}
}
