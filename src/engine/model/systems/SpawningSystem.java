package engine.model.systems;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.ICreator;
import engine.model.components.concrete.CreatorComponent;
import engine.model.weapons.DamageInfo;

public class SpawningSystem extends AbstractSystem<CreatorComponent> implements IObserver<TimelineController>{
	
	public ICreator get(IComponent c) {
		return getComponent(c);
	}
	
	/************Observer methods ************/
	@Override
	public void update(TimelineController aChangedObject) {
		for (CreatorComponent c: getComponents())
			c.spawnIfReady();
	}

	public void updateStats(IComponent c, DamageInfo data) {
		for (CreatorComponent parent : getComponents())
			if (parent.isParent(c.getEntity()))
				parent.updateStats(data);
	}
}
