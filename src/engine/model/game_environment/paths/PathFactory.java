package engine.model.game_environment.paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import engine.model.game_environment.terrain.Terrain;
import engine.model.game_environment.terrain.TerrainMap;

public class PathFactory {
	
	private HashMap<Terrain, Integer> myDistanceMap;
	
	public PathFactory()
	{
		myDistanceMap = new HashMap<Terrain, Integer>();
	}
	
	/**
	 * This method runs the BFS shortest path algorithm to construct paths from the Terrain map
	 * @param aTerrainMap
	 * @return
	 */
	public PathManager constructPaths(TerrainMap aTerrainMap)
	{
		Terrain source = aTerrainMap.getSource();

		Queue<Terrain> terrainQueue = new LinkedList<Terrain>();
		
		myDistanceMap.put(source, 0);
		terrainQueue.add(source);

		HashMap<Terrain,Terrain> paths = constructPathsInGraph(terrainQueue, aTerrainMap);
		
		List<Terrain> shortestPath = constructShortestPath(paths, aTerrainMap.getDestination());
		
		PathManager pathManager = new PathManager(shortestPath);
		return pathManager;
	}
	/**
	 * Helper method 
	 * @param aQueue
	 * @param aTerrainMap
	 * @return a map from terrain nodes to their previous node on the path
	 */
	private HashMap<Terrain, Terrain> constructPathsInGraph(Queue<Terrain> aQueue, TerrainMap aTerrainMap)
	{
		HashMap<Terrain, Terrain> pathToFollow = new HashMap<Terrain, Terrain>();
		while (!aQueue.isEmpty()) {
			Terrain currentTerrain = aQueue.poll();
			for (Terrain neighbor: aTerrainMap.getNeighbors(currentTerrain)) {
				if (!pathToFollow.containsKey(neighbor)) {//node is unmarked
					if (hasSameTerrainType(aTerrainMap.getSource(), neighbor)) {
						pathToFollow.put(neighbor, currentTerrain);
						aQueue.add(neighbor);
						if (neighbor == aTerrainMap.getDestination()) { 
							return pathToFollow;
						}
					}
				}
			}
		}
		//TODO: Throw error-->No path from source to destination!
		return null;
	}
	private boolean hasSameTerrainType(Terrain t1, Terrain t2)
	{
		return t1.getTerrainType().equals(t2.getTerrainType());
	}
	private List<Terrain> constructShortestPath(Map<Terrain,Terrain> aPreviousPathMap, Terrain aEndingTerrain)
	{
		List<Terrain> shortestPath = new ArrayList<Terrain>();
		
		Terrain currentTerrain = aEndingTerrain;
		while(aPreviousPathMap.containsKey(currentTerrain)) {
			shortestPath.add(0, currentTerrain);
			currentTerrain = aPreviousPathMap.get(currentTerrain);
		}
		shortestPath.add(0,currentTerrain); //adds the source
		return shortestPath;
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
	public static void main(String[] args)
	{
		PathFactory p = new PathFactory();
		TerrainMap t = new TerrainMap(null);
		
		p.constructPaths(t);
	}
}
