package engine.model.collision_detection;

public interface ICollisionHandler {
	public void handleCollision(ICollidable moved, ICollidable unmoved);
}
