package engine.model.machine;

import utility.Point;

public interface IViewableMachine {
	public String getImagePath();
	public double getHeading();
	public Point getLocation();
	public IHealth getHealth();
}
