package engine.model.systems;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.ICreator;
import engine.model.components.concrete.CreatorComponent;
import engine.model.entities.EntityFactory;
import engine.model.strategies.ISpawningStrategy;
import engine.model.strategies.factories.SpawningStrategyFactory;
import engine.model.weapons.DamageInfo;

public class SpawningSystem extends AbstractSystem<CreatorComponent> implements IObserver<TimelineController>{
	private EntityFactory myEntityFactory;
	private SpawningStrategyFactory myStrategyFactory;
	
	public SpawningSystem(TimelineController time) {
		myStrategyFactory = new SpawningStrategyFactory();
		time.attach(this);	}
	
	public void setEntityFactory(EntityFactory e) {
		myEntityFactory = e;
	}

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

	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing.
	}
	
	public EntityFactory getFactory() {
		return myEntityFactory;
	}

	public ISpawningStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}
}
