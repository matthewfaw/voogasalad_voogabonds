package engine;

import utility.Point;

public interface IViewable extends IObservable<IViewable> {
	
	abstract public double getSize();
	abstract public double getHeading();
	abstract public Point getPosition();
	abstract public String getImagePath();

}
