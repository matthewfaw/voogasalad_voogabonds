package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

public class DamageDealingComponent implements IComponent, IObservable<DamageDealingComponent> {

	@Override
	public void attach(IObserver<DamageDealingComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<DamageDealingComponent> aObserver) {
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
