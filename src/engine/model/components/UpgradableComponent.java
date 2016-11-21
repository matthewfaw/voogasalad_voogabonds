package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

public class UpgradableComponent implements IComponent, IObservable<UpgradableComponent> {

	@Override
	public void attach(IObserver<UpgradableComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<UpgradableComponent> aObserver) {
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
