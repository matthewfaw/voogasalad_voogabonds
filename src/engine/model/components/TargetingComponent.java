package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.IPhysical;
import engine.model.strategies.ITargetingStrategy;

public class TargetingComponent implements IComponent, ITargeting {
	private IEntity myEntity;

	private double mySightRange;
	private double mySightWidth;
	private ITargetingStrategy myTargetingStrategy;
	
	@Override
	public IEntity getEntity() {
		return myEntity;
	}
	
	@Override
	public IPhysical getTarget(MapMediator map, IPhysical location) {
		return myTargetingStrategy.target(map, location, this);
	}

	@Override
	public double getTargetWedgeWidth() {
		return mySightWidth;
	}

	@Override
	public double getTargetWedgeRadius() {
		return mySightRange;
	}
	
}
