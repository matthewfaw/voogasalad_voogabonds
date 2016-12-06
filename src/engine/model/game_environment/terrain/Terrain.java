package engine.model.game_environment.terrain;

import utility.Index;
import utility.Point;

public class Terrain {
	private int mySize;
	private Index myIndex;
	private String myTerrainType;
	
	public Terrain(int aXIndex, int aYIndex, String aTerrainType, int size)
	{
		mySize = size;
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
	
	public Point getCenter() {
		return new Point(mySize * (myIndex.getX() + 0.5), mySize * (myIndex.getY() + 0.5));
	}
	

}