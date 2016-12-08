package engine.model.strategies;

import java.util.List;

import engine.model.components.ITargeting;
import engine.model.components.PhysicalComponent;
import engine.model.game_environment.MapMediator;

abstract public class AbstractTargetingStrategy implements ITargetingStrategy{
	MapMediator myMap;

	public AbstractTargetingStrategy(MapMediator map) {
		myMap = map;
	}
	
	protected List<PhysicalComponent> getTargets(IPhysical location, ITargeting targeter) {
		//TODO: Allow the strategy to search in wedges instead of circles
		List<PhysicalComponent> targets = myMap.withinRange(location.getPosition(), location.getHeading());
		
		return targets;
	}
}
