package engine.model.strategies.target;

import engine.model.components.ITargeting;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.AbstractTargetingStrategy;
import engine.model.strategies.IPhysical;


public class BadTargetingStrategy extends AbstractTargetingStrategy {

	@Override
	public IPhysical target(MapMediator map, IPhysical location, ITargeting targeter) {
		return getTargets(map, location, targeter).get(0);
	}
}
