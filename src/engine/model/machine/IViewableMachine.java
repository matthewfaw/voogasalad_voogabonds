package engine.model.machine;

import utility.Point;
/**
 * Viewable machines
 *
 */
public interface IViewableMachine {
	public String getImagePath();
	public double getHeading();
	public Point getLocation();
	public IHealth getHealth();
}
