package engine.model.strategies;

import java.util.List;

import utility.Point;

public interface IMovable extends IPhysical {
	
	abstract public Point getGoal();
	abstract public double getTurnSpeed();
	abstract public double getMoveSpeed();
	
}
