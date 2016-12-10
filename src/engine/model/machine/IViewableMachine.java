package engine.model.machine;

import engine.IViewable;
import utility.Point;

@Deprecated
public interface IViewableMachine extends IViewable {
	abstract public double getHealth();
	
	@Override
	abstract public double getHeading();
	@Override
	abstract public String getImagePath();
	@Override
	abstract public double getSize();
	@Override
	abstract public Point getPosition();
}
