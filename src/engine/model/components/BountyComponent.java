package engine.model.components;

import engine.model.entities.IEntity;

public class BountyComponent implements IComponent {
	private IEntity myEntity;
	private int myBountyValue;

	public int getBounty()
	{
		return myBountyValue;
	}

	public IEntity getEntity()
	{
		return myEntity;
	}
}
