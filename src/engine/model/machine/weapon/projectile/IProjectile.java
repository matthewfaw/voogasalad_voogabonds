package engine.model.machine.weapon.projectile;
import engine.model.machine.Machine;
import utility.Point;

public interface IProjectile {
	public Point advance(); // returns the point that the projectile advanced to
	public Machine getTargetMachine();

}
