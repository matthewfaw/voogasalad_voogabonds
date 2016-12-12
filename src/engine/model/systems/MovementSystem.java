package engine.model.systems;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.concrete.MoveableComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.factories.MovementStrategyFactory;

public class MovementSystem extends AbstractSystem<MoveableComponent> implements IObserver<TimelineController> {
	private transient MovementStrategyFactory myStrategyFactory;
	
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
		for (MoveableComponent mc: getComponents()) {
			mc.move();
		}
	}

	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing.
	}
}
