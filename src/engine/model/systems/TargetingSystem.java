package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.CreatorComponent;
import engine.model.components.IComponent;
import engine.model.components.MoveableComponent;
import engine.model.components.TargetingComponent;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;

public class TargetingSystem implements ISystem {
	private List<TargetingComponent> myTargetingComponents;
	private PhysicalSystem myPhysical;
	private TeamSystem myTeams;
	
	public TargetingSystem(PhysicalSystem physical, TeamSystem teams) {
		myTargetingComponents = new ArrayList<TargetingComponent>();
		myPhysical = physical;
		myTeams = teams;
	}
	
	public IPosition getTarget(MoveableComponent c) {
		TargetingComponent t = get(c);
		if (t != null)
			return t.getTarget(myPhysical, myTeams, myPhysical.get(t));
		return c.getGoal();
	}
	
	public IPosition getTarget(CreatorComponent c) {
		TargetingComponent t = get(c);
		if (t != null)
			return t.getTarget(myPhysical, myTeams, myPhysical.get(t));
		return c.getGoal();
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(TargetingComponent aComponent) {
		myTargetingComponents.add(aComponent);
	}
	public void detachComponent(TargetingComponent aComponent) {
		myTargetingComponents.remove(aComponent);
	}
	
	private TargetingComponent get(IComponent c) {
		for (TargetingComponent t: myTargetingComponents)
			if (t.getEntity().equals(c.getEntity()))
				return t;
		return null;
	}
	
	

}
