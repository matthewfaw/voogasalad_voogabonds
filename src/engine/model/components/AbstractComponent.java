package engine.model.components;

import engine.model.entities.IEntity;

public class AbstractComponent implements IComponent{
	private IEntity myEntity;

	public AbstractComponent(IEntity aEntity)
	{
		myEntity = aEntity;
	}

	@Override
	public IEntity getEntity() {
		return myEntity;
	}

}
