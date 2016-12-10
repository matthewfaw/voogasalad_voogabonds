package engine.model.game_environment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import authoring.controller.MapDataContainer;
import authoring.model.map.TerrainData;
import engine.model.components.PhysicalComponent;
import engine.model.game_environment.paths.PathFactory;
import engine.model.game_environment.paths.PathManager;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.machine.Machine;
import engine.model.machine.MachineFactory;
import engine.model.strategies.IPhysical;
import javafx.util.Pair;
import utility.Point;

//TODO: update this class to ECS
public class MapMediator {
//	private PathFactory myPathFactory;
	
	private TerrainMap myTerrainMap;
	private List<PhysicalComponent> myPhysicalComponents;
	
	private MapDataContainer myMapData;
	
	//TODO: Change this constructor so that it hides away the terrain map
	// so constructor could take in terrain map data instead of terrain map
	// this makes the ownership model more explicit
	public MapMediator(MapDataContainer mapData) {
		myMapData = mapData;
		myPhysicalComponents = new ArrayList<PhysicalComponent>();
//		myPathFactory = new PathFactory(myTerrainMap);

	}
	/**
	 * Determines if an object can be placed on the map at the requested location.
	 * @param aLocation
	 * @param validTerrains
	 * @return
	 */
	public boolean attemptToPlaceEntity(Point aLocation, List<String> validTerrains) {
		for (TerrainData terrainData: myMapData.getTerrainList()) {
			if (terrainData.getLoc().equals(aLocation)) {
				for (String validTerrain: validTerrains) {
					if (validTerrain.equals(terrainData.getName())) {
						return true;
					}
				}
			}
		}
		return false;
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
		/*
		if (myTerrainMap.hasTerrain(aPhysicalComponent.getValidTerrains(), aLocation)) {
			aPhysicalComponent.setPosition(new Pair<Double, Point>(0.0, aLocation));
			accept(aPhysicalComponent, aLocation);
			return true;
		}
		*/
		
		
		/*
		 * Find terrain data at input point, then see if that data is a valid terrain for the 
		 * physical component. If so, place physical component.
		 */
		
		List<String> physicalComponentTerrains = aPhysicalComponent.getValidTerrains();
		Collection<TerrainData> terrainList = myMapData.getTerrainList();
		for (TerrainData terrainData: terrainList) {
			if (terrainData.getLoc().equals(aLocation)) {
				// found the terrain data at input location
				for (String physicalTerrain: physicalComponentTerrains) {
					if (physicalTerrain.equals(terrainData.getName())) {
						// terrain data at input location matches valid terrain type
						
						// Set heading to 0 and position as input point
						aPhysicalComponent.setPosition(new Pair<Double, Point>(0.0, aLocation));
//						accept(aPhysicalComponent, aLocation);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private void accept(PhysicalComponent aPhysicalComponent, Point aLocation) {
		// TODO Auto-generated method stub
	}

	@Deprecated // ECS doesn't use Machines
	public boolean attemptToPlaceEntity(Point aLocation, Machine aPhysicalComponent)
	{
		/*
		if (myTerrainMap.hasTerrain(aPhysicalComponent.getValidTerrains(), aLocation)) {
			aPhysicalComponent.setPosition(new Pair<Double, Point>(0.0, aLocation));
			accept(aPhysicalComponent, aLocation);
			return true;
		}
		*/
		return false;
	}
	
//	@Deprecated
//	public List<PhysicalComponent> withinRange(Point p, double radius){
//		Stream<PhysicalComponent> s = myEntityManager.stream();
//		
//		s.filter(e -> isEnemy(e) && isInRadius(e, p, radius));
//		
//		return s.collect(Collectors.toList());
//		
//	}

	private boolean isInRadius(IPhysical e, Point p, double radius) {
		/*
		return e.getPosition().euclideanDistance(p) - e.getCollisionRadius() - radius >= 0;
		*/
		return false;
	}

	@Deprecated //please don't use instanceof unless completely unavoidable. If necessary, please document why
	private boolean isEnemy(IPhysical e) {
		/*
		return e instanceof Enemy;
		*/
		return false;
	}


}
