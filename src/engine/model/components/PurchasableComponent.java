package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

public class PurchasableComponent implements IComponent, IObservable<PurchasableComponent>  {

	@Override
	public void attach(IObserver<PurchasableComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<PurchasableComponent> aObserver) {
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
