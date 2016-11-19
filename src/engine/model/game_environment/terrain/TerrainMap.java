package engine.model.game_environment.terrain;

import java.util.List;

import engine.model.entities.IEntity;
import engine.model.game_environment.paths.PathManager;
import utility.Point;

public class TerrainMap {
	private TerrainMapFactory  myMapFactory; 
	
	private Terrain[][] myMap;
	
	public TerrainMap(TerrainMapData aTerrainMapData)
	{
		myMapFactory = new TerrainMapFactory();
		myMap = myMapFactory.constructTerrainMap(aTerrainMapData);
	}
	
	/**
	 * A method to determine if the location on the map has one of the requested Terrains
	 * @param aValidTerrainList: valid terrains
	 * @param aLocation: location on the map
	 * @return true if the location has one of the valid terrains
	 */
	public boolean hasTerrain(List<Terrain> aValidTerrainList, Point aLocation)
	{
		return false;
	}
}
