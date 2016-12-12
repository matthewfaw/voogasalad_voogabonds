package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.strategies.IPosition;
import engine.model.weapons.DamageInfo;

public interface ICreator {

	abstract public void setTarget(IPosition target);
	abstract public IPosition getTarget();
	abstract public String getSpawnName();
	abstract boolean isParent(IEntity entity);
	abstract void updateStats(DamageInfo data);
	abstract DamageInfo getStats();

}
