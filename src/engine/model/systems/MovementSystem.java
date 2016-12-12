package engine.model.systems;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.concrete.MoveableComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.StrategyFactory;

public class MovementSystem extends AbstractSystem<MoveableComponent> implements IObserver<TimelineController> {
	private StrategyFactory myStrategyFactory;
	
	
	public MovementSystem (MapMediator map, TimelineController time) {
		myStrategyFactory = new StrategyFactory(map);
		time.attach(this);
	}
	
	public MoveableComponent get(IEntity entity) {
		return getComponent(entity);
	}
	
	public StrategyFactory getStrategyFactory() {
		return myStrategyFactory;
	}
	
	/********* Observer interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		for (MoveableComponent mc: getComponents()) {
			mc.move();
		}
	}
}
