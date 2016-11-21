package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

public class PurchasableComponent extends AbstractComponent implements IObservable<PurchasableComponent>  {

	public PurchasableComponent(IEntity aEntity) {
		super(aEntity);
		// TODO Auto-generated constructor stub
	}

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

}
