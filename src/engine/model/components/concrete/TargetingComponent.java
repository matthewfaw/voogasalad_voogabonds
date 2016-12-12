package engine.model.components.concrete;

import engine.model.components.AbstractComponent;
import engine.model.components.ITargeting;
import engine.model.strategies.IPosition;
import engine.model.strategies.ITargetingStrategy;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TeamSystem;

public class TargetingComponent extends AbstractComponent implements ITargeting {

	private double mySightRange;
	private double mySightWidth;
	private boolean myTargetsEnemies;
	private ITargetingStrategy myTargetingStrategy;
	
	private PhysicalSystem myPhysical;
	private TeamSystem myTeams;
	
	public TargetingComponent(PhysicalSystem physical, TeamSystem teams) {
		myPhysical = physical;
		myTeams = teams;
	}
	
	@Override
	public IPosition getTarget() {
		if (myPhysical.get(this) != null)
			return myTargetingStrategy.target(myPhysical, myTeams, myPhysical.get(this), this);
		return null;
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
