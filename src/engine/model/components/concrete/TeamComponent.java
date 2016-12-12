package engine.model.components.concrete;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.systems.TeamSystem;

/**
 * The purpose of this class is to manage which team an entity belongs to
 * This information could be used to determine which entities should be targeted
 * since you may not want to target an entity on your own team
 * @author matthewfaw
 *
 */
public class TeamComponent extends AbstractComponent {
	private String myTeamID;
	
	@Hide
	private transient TeamSystem mySystem;
	
	public TeamComponent(TeamSystem teams, ComponentData componentData) {
		myTeamID = componentData.getFields().get("myTeamID");
	}
	
	public String getTeamID() {
		return myTeamID;
	}

	@Override
	public void delete() {
		mySystem.detachComponent(this);
	}
	

}
