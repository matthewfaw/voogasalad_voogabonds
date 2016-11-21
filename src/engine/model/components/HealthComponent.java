package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

public class HealthComponent implements IComponent, IObservable<HealthComponent> {

	@Override
	public void attach(IObserver<HealthComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<HealthComponent> aObserver) {
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
