package engine.model.strategies.target;

import engine.model.components.concrete.PhysicalComponent;
import engine.model.components.concrete.TargetingComponent;
import engine.model.strategies.AbstractTargetingStrategy;
import engine.model.strategies.IPhysical;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TeamSystem;


public class BadTargetingStrategy extends AbstractTargetingStrategy {

	@Override
	public IPhysical target(PhysicalSystem map, TeamSystem teams, IPhysical location, TargetingComponent targeter) {
		IPhysical result = null;
		for (PhysicalComponent target: getTargets(map, location, targeter)) {
			if (
					teams.areEnemies(targeter, target) && targeter.targetsEnemies() ||
					teams.areAllies(targeter, target) && !targeter.targetsEnemies()) {
				result = target;
				break;
			}
			
		}
		return result;
	}
}
