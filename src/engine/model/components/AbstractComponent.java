package engine.model.components;

import engine.model.entities.IEntity;

abstract public class AbstractComponent implements IModifiableComponent{
	private IEntity myEntity;

	@Override
	public IEntity getEntity() {
		return myEntity;
	}

	@Override
	public void setEntity(IEntity e) {
		myEntity = e;
	}

}
