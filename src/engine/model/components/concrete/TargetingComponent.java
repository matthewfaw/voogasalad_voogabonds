package engine.model.components.concrete;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.components.ITargeting;
import engine.model.strategies.IPosition;
import engine.model.strategies.ITargetingStrategy;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TargetingSystem;
import engine.model.systems.TeamSystem;

public class TargetingComponent extends AbstractComponent implements ITargeting {

	private double mySightRange;
	private double mySightWidth;
	private boolean myTargetsEnemies;
	private transient ITargetingStrategy myTargetingStrategy;
	
	@Hide
	private transient PhysicalSystem myPhysical;
	@Hide
	private transient TargetingSystem myTargeting;
	@Hide
	private transient TeamSystem myTeams;
	
	public TargetingComponent(TargetingSystem target, PhysicalSystem physical, TeamSystem teams, ComponentData componentData) {
		myTargeting = target;
		myPhysical = physical;
		myTeams = teams;
		
		mySightRange = Double.parseDouble(componentData.getFields().get("mySightRange"));
		mySightWidth = Double.parseDouble(componentData.getFields().get("mySightWidth"));
		myTargetsEnemies = Boolean.parseBoolean(componentData.getFields().get("myTargestEnemies"));
		myTargetingStrategy = target.newStrategy(componentData.getFields().get("myTargetingStrategy"));
				
		target.attachComponent(this);
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

	@Override
	public void delete() {
		myTargeting.detachComponent(this);
	}	
}
