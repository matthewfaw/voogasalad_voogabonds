package authoring_interfaces;

/**
 * @author Christopher Lu
 * This interface provides the controller with access to the traits of the wave such as wave number, wave type, size of wave, etc.
 */

public interface IWave {
	
	/**
	 * Gives the number of the current wave.
	 */
	public int getWaveNumber();
	
	/**
	 * Gives the number of enemies in the wave.
	 */
	public int getWaveSize();
	
	/**
	 * Gives an array of enemy types such as [air, ground] which will be a wave that contains air and ground units.
	 */
	public String[] getWaveEnemyTypes();
	
	/**
	 * Gives the type of arrangement pattern the enemies will be in. For example, alternating, random, solid, etc.
	 */
	public String getWavePattern();
	
}
