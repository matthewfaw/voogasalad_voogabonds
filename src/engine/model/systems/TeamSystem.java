package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.TeamComponent;

public class TeamSystem extends AbstractSystem<TeamComponent> {

	public boolean areEnemies(IComponent a, IComponent b) {
		return getComponent(a).getTeamID() != getComponent(b).getTeamID();
	}

	public boolean areAllies(IComponent a, IComponent b) {
		return !areEnemies(a, b);
	}
	

}
