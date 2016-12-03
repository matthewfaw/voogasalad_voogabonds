package engine.model.components;

import engine.IObserver;
import engine.model.entities.IEntity;
import engine.model.strategies.IDamageStrategy;

public class DamageDealingComponent implements IComponent {
	private IDamageStrategy myDamageCalc;

	@Override
	public void attach(IObserver<IComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<IComponent> aObserver) {
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
