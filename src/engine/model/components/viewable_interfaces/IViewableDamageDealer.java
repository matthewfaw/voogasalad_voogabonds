package engine.model.components.viewable_interfaces;

public interface IViewableDamageDealer extends IViewable {
	/**
	 * @return damage dealt by this damage dealer.
	 */
	public int getDamage();
	/**
	 * 
	 * @return damage arc of this damage dealer
	 */
	public double getDamageArc();
	/**
	 * 
	 * @return damage radius of this damage dealer.
	 */
	public double getDamageRadius();
}
