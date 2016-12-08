package engine.model.strategies;

import engine.model.components.ICreator;
import engine.model.components.ITargeting;
import engine.model.game_environment.MapMediator;

public interface ITargetingStrategy {

	public IPhysical target(MapMediator map, IPhysical location, ITargeting targeter);

}
