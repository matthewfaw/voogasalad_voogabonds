package engine.model.game_environment.terrain;

import java.util.List;

import engine.model.entities.IEntity;
import utility.Point;

public class TerrainMap {
	private Terrain[][] myMap;
	
	public TerrainMap(int aWidth, int aHeight)
	{
		myMap = new Terrain[aWidth][aHeight];
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
