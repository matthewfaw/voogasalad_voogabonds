package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.concrete.CreatorComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.TargetingComponent;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;

public class TargetingSystem extends AbstractSystem<TargetingComponent> {
	private PhysicalSystem myPhysical;
	private TeamSystem myTeams;
	
	public TargetingSystem(PhysicalSystem physical, TeamSystem teams) {
		myPhysical = physical;
		myTeams = teams;
	}
	
	public IPosition getTarget(MoveableComponent c) {
		TargetingComponent t = getComponent(c);
		if (t != null)
			return t.getTarget(myPhysical, myTeams, myPhysical.get(t));
		return c.getGoal();
	}
	
	public IPosition getTarget(CreatorComponent c) {
		TargetingComponent t = getComponent(c);
		if (t != null)
			return t.getTarget(myPhysical, myTeams, myPhysical.get(t));
		return c.getGoal();
	}
}
