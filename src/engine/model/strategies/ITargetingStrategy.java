package engine.model.strategies;

import engine.model.components.ICreator;

public interface ITargetingStrategy {

	public IPhysical target(IPhysical location, ICreator creator);

}
