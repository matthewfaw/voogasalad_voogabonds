package engine.model.strategies;

import engine.model.components.ICreator;
import engine.model.components.ITargeting;

public interface ITargetingStrategy {

	public IPhysical target(IPhysical location, ITargeting targeter);

}
