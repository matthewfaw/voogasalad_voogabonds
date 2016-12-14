package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.ICreator;
import engine.model.components.concrete.CreatorComponent;
import engine.model.entities.IEntity;
import engine.model.weapons.DamageInfo;
import engine.model.entities.EntityFactory;
import engine.model.strategies.ISpawningStrategy;
import engine.model.strategies.factories.SpawningStrategyFactory;

public class SpawningSystem implements ISystem<CreatorComponent>, IObserver<TimelineController>{
	private List<CreatorComponent> myComponents;
	private transient EntityFactory myEntityFactory;
	private transient SpawningStrategyFactory myStrategyFactory;
	
	public SpawningSystem(TimelineController time)
	{
		myComponents = new ArrayList<CreatorComponent>();
		myStrategyFactory = new SpawningStrategyFactory();
		time.attach(this);	
	}
	
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

	/***********ISystem interface*******/
	@Override
	public List<CreatorComponent> getComponents() {
		return myComponents;
	}
	@Override
	public CreatorComponent getComponent(IComponent component) {
		return getComponent(component.getEntity());
	}
	@Override
	public CreatorComponent getComponent(IEntity entity) {
		for (CreatorComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(CreatorComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(CreatorComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
