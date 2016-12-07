package engine.model.strategies;

import utility.Point;

public interface IMovable {
	
	abstract public Point getGoal();
	abstract public double getTurnSpeed();
	abstract public double getMoveSpeed();
	
}
