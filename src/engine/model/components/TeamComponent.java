package engine.model.components;

import engine.model.entities.IEntity;

public class TeamComponent implements IComponent {
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
