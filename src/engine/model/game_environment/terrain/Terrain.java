package engine.model.game_environment.terrain;

import utility.Index;

public class Terrain {
	private Index myIndex;
	
	public Terrain(int aXIndex, int aYIndex)
	{
		myIndex = new Index(aXIndex, aYIndex);
	}
	
	public Index getIndexInMap()
	{
		return myIndex;
	}
}
