package engine.model.game_environment.paths;

import java.util.HashMap;
import java.util.PriorityQueue;

import engine.model.game_environment.terrain.Terrain;
import engine.model.game_environment.terrain.TerrainMap;

public class PathFactory {
	
	private HashMap<Terrain, Integer> myDistanceMap;
	
	public PathFactory()
	{
	}
	
	/**
	 * This method runs the BFS shortest path algorithm to construct paths from the Terrain map
	 * @param aTerrainMap
	 * @return
	 */
	public PathManager constructPaths(TerrainMap aTerrainMap)
	{
		Terrain source = aTerrainMap.getSource();
		Terrain destination = aTerrainMap.getSource();
		
		PriorityQueue<Terrain> pq = new PriorityQueue<Terrain>(0, (a,b) -> getDistanceFromSource(a) - getDistanceFromSource(b));
		
		constructPaths(source, aTerrainMap, pq);
		
		return null;
	}
	private void constructPaths(Terrain aSource, TerrainMap aTerrainMap, PriorityQueue<Terrain> aPriorityQueue)
	{
		
	}
	
	/**
	 * Returns the distance from the source to this node
	 * @param aTerrain
	 * @return
	 */
	private int getDistanceFromSource(Terrain aTerrain)
	{
		if (myDistanceMap.containsKey(aTerrain)) {
			return myDistanceMap.get(aTerrain);
		} else {
			return 0;
		}
	}
}
