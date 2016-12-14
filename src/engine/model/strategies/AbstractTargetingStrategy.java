package engine.model.strategies;

import java.util.List;

import engine.model.components.ITargeting;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.systems.PhysicalSystem;

abstract public class AbstractTargetingStrategy implements ITargetingStrategy{
	
	protected List<PhysicalComponent> getTargets(PhysicalSystem map, IPhysical location, ITargeting targeter) {
		List<PhysicalComponent> targets = map.withinRange(
				location.getPosition(),
				targeter.getTargetWedgeRadius(),
				location.getHeading(),
				targeter.getTargetWedgeWidth());
		
		return targets;
	}
}
