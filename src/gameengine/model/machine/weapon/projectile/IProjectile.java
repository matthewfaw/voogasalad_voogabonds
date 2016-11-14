package gameengine.model.machine.weapon.projectile;
import gameengine.model.machine.Machine;
import utility.Point;

public interface IProjectile {
	public Point advance(); // returns the point that the projectile advanced to
	public void hitTarget();
	public Machine getTargetMachine();
	public double damageToDeal(Point damageLocation);

}
