package engine.model.playerinfo;

import java.util.List;

import authoring.model.TowerData;

public interface IViewablePlayer {
	/**
	 * Get the number of remaining lives of an object
	 * @return the number of lives a player has remaining
	 */
	public int getLivesRemaining();
	/**
	 * Get the cash the player has
	 * @return returns the value of the money
	 */
	public int getAvailableMoney();
	/**
	 * XXX: What is this?
	 * @return
	 */
	public int getID();
	
	/**
	 * Get all the towers that are available to the player
	 * @return the available towers
	 */
	public List<TowerData> getAvailableTowers();
	/**
	 * Get all towers that the player can afford
	 * @return all the affordable towers
	 */
	public List<TowerData> getAffordableTowers();
}
