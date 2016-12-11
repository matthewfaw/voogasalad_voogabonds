package engine.model.strategies;

import engine.model.components.TargetingComponent;
import engine.model.game_environment.MapMediator;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TeamSystem;

public interface ITargetingStrategy {

	public IPhysical target(PhysicalSystem map, TeamSystem teams, IPhysical location, TargetingComponent targeter);

}
