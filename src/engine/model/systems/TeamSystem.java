package engine.model.systems;

import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.TeamComponent;

public class TeamSystem implements ISystem {
	private List<TeamComponent> myComponents;
	
	public TeamComponent get(IComponent c) {
		for (TeamComponent t : myComponents) {
			if (c.getEntity().equals(t.getEntity()))
					return t;
		}
		return null;
	}

	public boolean areEnemies(IComponent a, IComponent b) {
		return get(a).getTeamID() != get(b).getTeamID();
	}

	public boolean areAllies(IComponent a, IComponent b) {
		return !areEnemies(a, b);
	}
	
	
	/************ Attach and detach component methods ************/
	public void attachComponent(TeamComponent aComponent) {
		myComponents.add(aComponent);
	}
	public void detachComponent(TeamComponent aComponent) {
		myComponents.remove(aComponent);
	}

}
