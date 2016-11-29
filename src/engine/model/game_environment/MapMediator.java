package engine.model.game_environment;

import engine.model.components.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.paths.PathFactory;
import engine.model.game_environment.paths.PathManager;
import engine.model.game_environment.terrain.TerrainMap;
import utility.Point;

public class MapMediator {
	private PathFactory myPathFactory;
	
	private TerrainMap myTerrainMap;
	private EntityManager myEntityManager;
	private PathManager myPathManager;
	
	//TODO: Change this constructor so that it hides away the terrain map
	// so constructor could take in terrain map data instead of terrain map
	// this makes the ownership model more explicit
	public MapMediator(TerrainMap aTerrainMap)
	{
		myTerrainMap = aTerrainMap;
		myEntityManager = new EntityManager();
		myPathFactory = new PathFactory();
		myPathManager = myPathFactory.constructPaths(myTerrainMap);
	}
	
	/**
	 * Determines if an object can be placed on the map at the requested location
	 * Places the object if it can
	 * @param aLocation to place the object
	 * @param aValidTerrainList: a list of terrains the object can be placed on
	 * @return true if the entity was placed, false otherwise
	 */
	public boolean attemptToPlaceEntity(Point aLocation, PhysicalComponent aPhysicalComponent)
	{
		if (myTerrainMap.hasTerrain(aPhysicalComponent.getValidTerrains(), aLocation)) {
			aPhysicalComponent.setLocation(aLocation);
			accept(aPhysicalComponent.getEntity(), aLocation);
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
		myEntityManager.trackEntity(aEntityToTrack);
	}
}
