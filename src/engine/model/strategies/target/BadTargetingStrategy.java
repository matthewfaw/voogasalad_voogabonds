package engine.model.strategies.target;

import engine.model.components.ICreator;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.AbstractTargetingStrategy;
import engine.model.strategies.IPhysical;


public class BadTargetingStrategy extends AbstractTargetingStrategy {

	public BadTargetingStrategy(MapMediator map) {
		super(map);
	}

	@Override
	public IPhysical target(IPhysical location, ICreator creator) {
		return getTargets(location, creator).get(0);
	}
}
