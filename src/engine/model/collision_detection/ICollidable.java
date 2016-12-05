package engine.model.collision_detection;

import engine.model.machine.Machine;
import engine.model.projectiles.Projectile;
import engine.model.strategies.IPhysical;

public interface ICollidable extends IPhysical {

	public void collideInto(ICollidable unmovedCollidable);
	public void collideInto(Machine unmovedCollidable);
	public void collideInto(Projectile unmovedCollidable);

}
