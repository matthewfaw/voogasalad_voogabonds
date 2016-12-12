package engine.model.strategies;

import engine.model.components.concrete.TargetingComponent;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TeamSystem;

public interface ITargetingStrategy {

	public IPhysical target(PhysicalSystem map, TeamSystem teams, IPhysical location, TargetingComponent targeter);

}
