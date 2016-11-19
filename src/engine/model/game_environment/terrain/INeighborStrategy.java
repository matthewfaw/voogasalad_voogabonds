package engine.model.game_environment.terrain;

import java.util.List;

import utility.Index;

public interface INeighborStrategy<A> {
	/**
	 * Given a terrain object in the map, determines the neighboring cells
	 * 
	 * TODO: perhaps add error checking to make sure it's in the map?
	 * @param aTerrain
	 * @return a list of the neighbors of the corresponding Terrain object
	 */
	public List<A> getNeighbors(A aNode);
}
