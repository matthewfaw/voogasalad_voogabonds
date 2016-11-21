package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

public class UpgradableComponent extends AbstractComponent implements IObservable<UpgradableComponent> {

	public UpgradableComponent(IEntity aEntity) {
		super(aEntity);
		// TODO Auto-generated constructor stub
	}

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

}
