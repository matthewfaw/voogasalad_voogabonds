package engine.model.components;

import engine.model.entities.IEntity;

/**
 * The purpose of this class is to manage which team an entity belongs to
 * This information could be used to determine which entities should be targeted
 * since you may not want to target an entity on your own team
 * @author matthewfaw
 *
 */
public class TeamComponent extends AbstractComponent {
	private IEntity myEntity;
	private String myTeamID;
	
	public String getTeamID()
	{
		return myTeamID;
	}
	
	@Override
	public IEntity getEntity() {
		return myEntity;
	}
}
