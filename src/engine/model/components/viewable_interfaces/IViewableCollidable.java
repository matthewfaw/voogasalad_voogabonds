package engine.model.components.viewable_interfaces;

public interface IViewableCollidable extends IViewable {
	/**
	 * Gets the collision radius for a collidable entity.
	 * @return
	 */
	public double getCollisionRadius();
}
