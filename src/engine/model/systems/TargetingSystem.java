package engine.model.systems;

import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.TargetingComponent;
import engine.model.strategies.IPhysical;

public class TargetingSystem implements ISystem {
	
	List<TargetingComponent> myTargetingComponents;
	private PhysicalSystem myPhysical;
	private TeamSystem myTeams;
	
	public IPhysical getTarget(IComponent c) {
		TargetingComponent t = get(c);
		if (t != null)
			return t.getTarget(myPhysical.getMap(), myTeams, myPhysical.get(t));
		return null;
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
