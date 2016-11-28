package engine.model.strategies;

import utility.Point;

public interface IMovable extends IPhysical {

	abstract public Point getLocation();
	abstract public double getHeading();
	abstract public Point getGoal();

	abstract public double getTurnSpeed();
	abstract public double getMoveSpeed();
	double getCollisionRadius();
}
