package engine.model.components.concrete;

import engine.model.components.AbstractComponent;
import engine.model.components.ITargeting;
import engine.model.strategies.IPhysical;
import engine.model.strategies.ITargetingStrategy;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TeamSystem;

public class TargetingComponent extends AbstractComponent implements ITargeting {

	private double mySightRange;
	private double mySightWidth;
	private boolean myTargetsEnemies;
	private ITargetingStrategy myTargetingStrategy;
	
	@Override
	public IPhysical getTarget(PhysicalSystem map, TeamSystem teams, IPhysical location) {
		return myTargetingStrategy.target(map, teams, location, this);
	}

	@Override
	public double getTargetWedgeWidth() {
		return mySightWidth;
	}

	@Override
	public double getTargetWedgeRadius() {
		return mySightRange;
	}
	
	@Override
	public boolean targetsEnemies() {
		return myTargetsEnemies;
	}
	
}
