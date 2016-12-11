package engine.model.systems;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.ICreator;
import engine.model.components.concrete.CreatorComponent;

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
}
