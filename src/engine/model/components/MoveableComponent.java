package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

/**
 * A component that defines an entities ability to move
 * @author matthewfaw
 *
 */
public class MoveableComponent implements IComponent, IObservable<MoveableComponent>  {

	@Override
	public void attach(IObserver<MoveableComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<MoveableComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
