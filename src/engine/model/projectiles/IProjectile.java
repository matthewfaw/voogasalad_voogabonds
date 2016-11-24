package engine.model.projectiles;
import engine.model.machine.Machine;
import utility.Point;

/**
 * This interface provides the model with access to the advance method to that the projectile can move when the game ticks.
 * @author Weston
 */
public interface IProjectile {
	
	/**
	 * Advances the projectile, possibly exploding it if it runs into an enemy unit.
	 * @return the new location of the projectile
	 */
	public Point advance(); // returns the point that the projectile advanced to
	
	/**
	 * @return the machine this projectile is targeting
	 */
	public Machine getTargetMachine();

}
