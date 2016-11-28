package engine.model.collision_detection;

import engine.model.projectiles.IProjectile;

public class CollisionHandler implements ICollisionHandler{

	//matthewfaw: I'd prefer to avoid using instanceof here
	// instead, you can have polymorphic methods to hide the details
	// of what happens to a specific object when a collision occurs

	/**
	 * Handle collisions differently based on types that collided
	 */
	@Override
	public void handleCollision(ICollidable moved, ICollidable unmoved) {
		// projectile collision with non-projectile
		if (moved instanceof IProjectile && !(unmoved instanceof IProjectile)) {
			// explode moved
			// subtract health from unmoved
		} else if (unmoved instanceof IProjectile && !(moved instanceof IProjectile)) {
			// explode unmoved
			// subtract health from moved
		}
	}

}
