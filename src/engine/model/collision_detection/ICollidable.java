package engine.model.collision_detection;

import engine.model.components.CollidableComponent;

public interface ICollidable {

	public void collideInto(CollidableComponent unmovedCollidable);
}
