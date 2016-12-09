package engine.model.game_environment.terrain;

import java.util.Stack;

import authoring.model.map.TerrainData;
import utility.Index;
import utility.Point;

public class Terrain {
	private int mySize;
	private Index myIndex;
	private Stack<TerrainData> myTerrainLevels;
	
	public Terrain(Stack<TerrainData> aTerrainStack)
	{
		TerrainData topTerrain = aTerrainStack.peek();
		mySize = aTerrainStack.peek().getSize();
		myIndex = new Index((int)topTerrain.getLoc().getX(), (int)topTerrain.getLoc().getY());
		myTerrainLevels = aTerrainStack;
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
	 * Returns the *top* terrain type of this Terrain object
	 * @return
	 */
	public String getTerrainType()
	{
		return myTerrainLevels.peek().getName();
	}
	
	public Point getCenter() {
		return new Point(mySize * (myIndex.getX() + 0.5), mySize * (myIndex.getY() + 0.5));
	}
	

}