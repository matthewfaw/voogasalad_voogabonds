package engine.model.systems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.factories.MovementStrategyFactory;

public class MovementSystem extends AbstractSystem<MoveableComponent> implements IObserver<TimelineController> {
	private MovementStrategyFactory myStrategyFactory;
	
	
	public MovementSystem (MapMediator map, TimelineController time) {
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
}
