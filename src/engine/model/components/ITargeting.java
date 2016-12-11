package engine.model.components;

import engine.model.game_environment.MapMediator;
import engine.model.strategies.IPhysical;
import engine.model.systems.TeamSystem;

public interface ITargeting {
	abstract public double getTargetWedgeWidth();
	abstract public double getTargetWedgeRadius();
	abstract public IPhysical getTarget(MapMediator map, TeamSystem teams, IPhysical location);
	abstract public boolean targetsEnemies();
}
