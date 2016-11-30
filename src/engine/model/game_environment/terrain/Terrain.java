package engine.model.game_environment.terrain;

import utility.Index;

public class Terrain {
	private Index myIndex;
	private String myTerrainType;
	
	public Terrain(int aXIndex, int aYIndex, String aTerrainType)
	{
		myIndex = new Index(aXIndex, aYIndex);
		myTerrainType = aTerrainType;
	}
	
	/**
	 * Returns the location of the Terrain inside of the map
	 * @return
	 */
	public Index getIndexInMap()
	{
		return myIndex;
	}
	
	/**
	 * Returns the terrain type of this Terrain object
	 * @return
	 */
	public String getTerrainType()
	{
		return myTerrainType;
	}

}