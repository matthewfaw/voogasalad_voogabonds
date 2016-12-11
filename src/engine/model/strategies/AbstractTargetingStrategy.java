package engine.model.strategies;

import java.util.List;

import engine.model.components.ITargeting;
import engine.model.components.PhysicalComponent;
import engine.model.game_environment.MapMediator;
import engine.model.systems.PhysicalSystem;

abstract public class AbstractTargetingStrategy implements ITargetingStrategy{
	
	protected List<PhysicalComponent> getTargets(PhysicalSystem map, IPhysical location, ITargeting targeter) {
		//TODO: Allow the strategy to search in wedges instead of circles
		List<PhysicalComponent> targets = map.withinRange(location.getPosition(), location.getHeading(), targeter.getTargetWedgeRadius(), targeter.getTargetWedgeWidth());
		
		return targets;
	}
}
