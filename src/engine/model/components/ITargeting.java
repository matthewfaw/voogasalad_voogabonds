package engine.model.components;

import engine.model.strategies.IPosition;

public interface ITargeting {
	abstract public double getTargetWedgeWidth();
	abstract public double getTargetWedgeRadius();
	abstract public boolean targetsEnemies();
	IPosition getTarget();
}
