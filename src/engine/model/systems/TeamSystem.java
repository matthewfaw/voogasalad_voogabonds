package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.TeamComponent;

public class TeamSystem extends AbstractSystem<TeamComponent> {

	public boolean areEnemies(IComponent a, IComponent b) {
		TeamComponent aTeam = getComponent(a);
		TeamComponent bTeam = getComponent(b);
		if (aTeam != null && bTeam != null)
			return !getComponent(a).getTeamID().equals(getComponent(b).getTeamID());
		else {
			//If they aren't on teams they are everyone's enemy, I guess.
			return true;
		}
	}

	public boolean areAllies(IComponent a, IComponent b) {
		return !areEnemies(a, b);
	}
	

}
