package engine.model.collision_detection;

public class CollisionHandler implements ICollisionHandler{

	/**
	 * Handle collisions differently based on types that collided
	 */
	@Override
	public void handleCollision(ICollidable moved, ICollidable unmoved) {
		moved.collideWith(unmoved);
	}

}
