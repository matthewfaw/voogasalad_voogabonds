package engine.model.components;

import engine.model.strategies.IPhysical;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TeamSystem;

public interface ITargeting {
	abstract public double getTargetWedgeWidth();
	abstract public double getTargetWedgeRadius();
	abstract public boolean targetsEnemies();
	IPhysical getTarget(PhysicalSystem map, TeamSystem teams, IPhysical location);
}
