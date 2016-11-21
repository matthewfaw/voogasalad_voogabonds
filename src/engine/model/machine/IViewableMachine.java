package engine.model.machine;

import utility.Point;
/**
 * Viewable machines
 * TODO: This interface is a duplicate of the more inclusively named "IViewable". Do we need both of them?
 *
 */
public interface IViewableMachine {
	public String getImagePath();
	public double getHeading();
	public Point getLocation();
	public IHealth getHealth();
}
