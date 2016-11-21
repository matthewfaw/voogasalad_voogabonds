package engine.model.components;

import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

/**
 * The purpose of this class is to capture the functionality of dispatching some sort of attack
 * For example, Towers would be constructed with a damage dealing component that can construct and shoot
 * projectiles
 * @author matthewfaw
 *
 */
public class DamageDealingComponent extends AbstractComponent implements IObservable<DamageDealingComponent> {
	private List<IObserver<DamageDealingComponent>> myObservers;

	public DamageDealingComponent(IEntity aEntity) {
		super(aEntity);
		// TODO Auto-generated constructor stub
	}

	//*************************Observable interface*******************//

	@Override
	public void attach(IObserver<DamageDealingComponent> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<DamageDealingComponent> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

}
