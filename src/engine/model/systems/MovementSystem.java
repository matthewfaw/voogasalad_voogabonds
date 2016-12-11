package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.MoveableComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.StrategyFactory;

public class MovementSystem implements IObserver<TimelineController>, ISystem {
	private List<MoveableComponent> myMoveableComponents;
	private StrategyFactory myStrategyFactory;
	
	
	public MovementSystem (MapMediator map, 
			TimelineController time) {
		myMoveableComponents = new ArrayList<MoveableComponent>();
		myStrategyFactory = new StrategyFactory(map);
		
		time.attach(this);
		
	}
	
	public MoveableComponent get(IEntity entity) {
		for (MoveableComponent m: myMoveableComponents)
			if (m.getEntity().equals(entity))
				return m;
		return null;
	}
	
	private void updateNextMoves() {
		for (MoveableComponent mc: myMoveableComponents) {
			mc.move();
		}
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(MoveableComponent aComponent) {
		myMoveableComponents.add(aComponent);
	}
	public void detachComponent(MoveableComponent aComponent) {
		myMoveableComponents.remove(aComponent);
	}
	
	/********* Observer interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		updateNextMoves();
	}

	public StrategyFactory getStrategyFactory() {
		return myStrategyFactory;
	}
}
