package engine.model.strategies.target;

import engine.model.components.PhysicalComponent;
import engine.model.components.TargetingComponent;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.AbstractTargetingStrategy;
import engine.model.strategies.IPhysical;
import engine.model.systems.TeamSystem;


public class BadTargetingStrategy extends AbstractTargetingStrategy {

	@Override
	public IPhysical target(MapMediator map, TeamSystem teams, IPhysical location, TargetingComponent targeter) {
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
