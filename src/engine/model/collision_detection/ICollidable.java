package engine.model.collision_detection;

import engine.model.strategies.IPhysical;

public interface ICollidable extends IPhysical {
	abstract public void collide(ICollidable unmoved);
}
