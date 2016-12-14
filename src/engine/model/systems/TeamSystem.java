package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.concrete.TeamComponent;
import engine.model.entities.IEntity;

public class TeamSystem implements ISystem<TeamComponent> {
	private List<TeamComponent> myComponents;
	
	public TeamSystem()
	{
		myComponents = new ArrayList<TeamComponent>();
	}

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
	
	/***********ISystem interface*******/
	@Override
	public List<TeamComponent> getComponents() {
		return myComponents;
	}
	@Override
	public TeamComponent getComponent(IComponent component) {
		return component == null ? null : getComponent(component.getEntity());
	}
	@Override
	public TeamComponent getComponent(IEntity entity) {
		for (TeamComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(TeamComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(TeamComponent aComponent) {
		myComponents.remove(aComponent);
	}

}
