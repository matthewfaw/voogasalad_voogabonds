package engine.model.strategies;

import java.util.List;

import utility.Point;

public interface IMovable extends IPhysical {
	
	abstract public Point getGoal();
	abstract public double getTurnSpeed();
	abstract public double getMoveSpeed();

	@Override
	double getCollisionRadius();
	@Override
	abstract public Point getPosition();
	@Override
	abstract public double getHeading();
	@Override
	abstract public List<String> getValidTerrains();
	@Override
	abstract public void setPosition(Point p);
	
}
