package engine.model.components;

import engine.model.strategies.IPosition;

public interface ICreator {

	abstract public void setTarget(IPosition target);
	abstract public IPosition getTarget();
	abstract public String getSpawnName();

}
