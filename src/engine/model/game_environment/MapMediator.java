package engine.model.game_environment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import engine.model.components.PhysicalComponent;
import engine.model.game_environment.paths.PathFactory;
import engine.model.game_environment.paths.PathManager;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.machine.Machine;
import engine.model.machine.enemy.Enemy;
import engine.model.strategies.IPhysical;
import utility.Point;

public class MapMediator {
	private PathFactory myPathFactory;
	
	private TerrainMap myTerrainMap;
	private ArrayList<IPhysical> myEntityManager;
	private PathManager myPathManager;
	
	//TODO: Change this constructor so that it hides away the terrain map
	// so constructor could take in terrain map data instead of terrain map
	// this makes the ownership model more explicit
	public MapMediator(TerrainMap aTerrainMap)
	{
		myTerrainMap = aTerrainMap;
		myEntityManager = new ArrayList<IPhysical>();
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
			accept(aPhysicalComponent, aLocation);
			return true;
		}
		return false;
	}
	
	private void accept(PhysicalComponent aPhysicalComponent, Point aLocation) {
		// TODO Auto-generated method stub
	}

	public boolean attemptToPlaceEntity(Point aLocation, IPhysical aPhysicalComponent)
	{
		if (myTerrainMap.hasTerrain(aPhysicalComponent.getValidTerrains(), aLocation)) {
			aPhysicalComponent.setLocation(aLocation);
			accept(aPhysicalComponent, aLocation);
			return true;
		}
		return false;
	}
	
	public List<Machine> withinRange(Point p, double radius){
		Stream<IPhysical> s = myEntityManager.stream();
		
		s.filter(e -> isEnemy(e) && isInRadius(e, p, radius));
		
		return s.map(e -> (Machine) e).collect(Collectors.toList());
		
	}

	private boolean isInRadius(IPhysical e, Point p, double radius) {
		return e.getLocation().euclideanDistance(p) - e.getCollisionRadius() - radius >= 0;
	}

	private boolean isEnemy(IPhysical e) {
		return e instanceof Enemy;
	}

	/**
	 * Accepts entity to be tracked by map
	 * @param aEntityToTrack
	 */
	private void accept(IPhysical aEntityToTrack, Point aLocation)
	{
		myEntityManager.add(aEntityToTrack);
	}

}
