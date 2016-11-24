package engine.model;

public interface ICollisionHandler {
	public void handleCollision(ICollidable moved, ICollidable unmoved);
}
