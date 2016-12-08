package engine.model.systems;

import java.util.ArrayList;
import java.util.List;


import engine.IObservable;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.MoveableComponent;

@Deprecated
public class MovementSystem implements IObservable<MovementSystem>, IObserver<TimelineController>, ISystem{
	private List<IObserver<MovementSystem>> myObservers;
	private List<MoveableComponent> myMoveableComponents;
	
	public MovementSystem () {
		myMoveableComponents = new ArrayList<MoveableComponent>();
	}
	
	private void updateNextMoves() {
		// get next move for all registered movable components
		// update new position for all registered movable components
		for (MoveableComponent mc: myMoveableComponents) {
			// physComponent.setPosition(mc.getNextMove());
		}
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(MoveableComponent aComponent) {
		myMoveableComponents.add(aComponent);
	}
	public void detachComponent(MoveableComponent aComponent) {
		myMoveableComponents.remove(aComponent);
	}
	
	/********* Observable interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		updateNextMoves();
	}

	/********* Observer interface ***********/
	@Override
	public void attach(IObserver<MovementSystem> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<MovementSystem> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(o -> o.update(this));
	}

}
