package engine.model.game_environment.terrain;

import java.util.Stack;

import authoring.model.map.TerrainData;
import utility.Index;
import utility.Point;

public class Terrain {
	private TerrainData myTerrainData;
	
	public Terrain(TerrainData aTerrainData)
	{
		myTerrainData = aTerrainData;
	}
	
	/**
	 * Returns the location of the Terrain inside of the map
	 * @return
	 */
	public Index getIndexInMap()
	{
		return new Index((int)myTerrainData.getLoc().getX(), (int)myTerrainData.getLoc().getY());
	}
	
	/**
	 * Returns the *top* terrain type of this Terrain object
	 * @return
	 */
	public String getTerrainType()
	{
		return myTerrainData.getName();
	}
	
	public Point getCenter() {
		int size = myTerrainData.getSize();
		int x = (int) myTerrainData.getLoc().getX();
		int y = (int) myTerrainData.getLoc().getY();
		return new Point(size * (x + 0.5), size * (y + 0.5));
	}
	

}