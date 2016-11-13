package voogasalad_voogabonds;

/**
 * This interface is responsible for setting up the battlefield terrain, assuming that we will be using a grid.
 * I believe that Terrain should be the individual cells in the ovverall grid so the user can set the overall environment to greater precision.
 * @author Christopher LU
 * @author Niklas
 *
 */

public interface Terrain {

	/**
	 * Determines the name of the terrain.
	 */
	public int getName();
	
	/**
	 * Determines the x dimensions of the terrain.
	 */
	public int getXSize();
	
	/**
	 * Determines the y dimensions of the terrain.
	 */
	public int getYSize();
	
	/**
	 * Type of terrain determines how fast an enemy can move across it.
	 */
	public int getType();
	
	/**
	 * Determines the terrain's color.
	 */
	public int getColor();
	

	
}
