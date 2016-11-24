package engine.model.collision_detection;

import engine.model.machine.weapon.projectile.IProjectile;

public class CollisionHandler implements ICollisionHandler{

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
