package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.factories.MovementStrategyFactory;

public class MovementSystem implements ISystem<MoveableComponent>, IObserver<TimelineController> {
	private List<MoveableComponent> myComponents;
	private transient MovementStrategyFactory myStrategyFactory;
	
	public MovementSystem (MapMediator map, TimelineController time) {
		myComponents = new ArrayList<MoveableComponent>();
		myStrategyFactory = new MovementStrategyFactory(map);
		time.attach(this);
	}
	
	public MoveableComponent get(IEntity entity) {
		return getComponent(entity);
	}
	
	public MovementStrategyFactory getStrategyFactory() {
		return myStrategyFactory;
	}
	
	/********* Observer interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		List<MoveableComponent> components = new ArrayList<MoveableComponent>();
		components.addAll(getComponents());
		for (MoveableComponent mc :components) {
			mc.move();
		}

	}

	public MoveableComponent get(IComponent c) {
		return getComponent(c);
	}
	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing.

	}
	/***********ISystem interface*******/
	@Override
	public List<MoveableComponent> getComponents() {
		return myComponents;
	}
	@Override
	public MoveableComponent getComponent(IComponent component) {
		return getComponent(component.getEntity());
	}
	@Override
	public MoveableComponent getComponent(IEntity entity) {
		for (MoveableComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(MoveableComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(MoveableComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
