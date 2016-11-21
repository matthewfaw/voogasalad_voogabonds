package engine.model.components;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

/**
 * This class encapsulates the fields corresponding to any health data of an entity
 * @author matthewfaw
 *
 */
public class HealthComponent extends AbstractComponent implements IObservable<HealthComponent> {
	private int myHealth;

	public HealthComponent(IEntity aEntity) {
		super(aEntity);
		// TODO Auto-generated constructor stub
	}
	
	public int getHealth()
	{
		return myHealth;
	}

	//*************************Observable interface*******************//
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
}