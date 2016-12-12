package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewableBounty;
import engine.model.systems.BountySystem;

/**
 * The purpose of this class is to manage the information
 * relevant to the reward associated with some action on an entity
 * For example, when this entity is killed, the bounty could represent
 * how much reward the other entity should receive 
 * 
 * @author matthewfaw
 *
 */
public class BountyComponent extends AbstractComponent implements IViewableBounty {
	private int myBountyValue;
	
	@Hide
	private List<IObserver<IViewable>> myObservers;
	
	public BountyComponent (BountySystem bountySystem, ComponentData data) {
		myBountyValue = Integer.parseInt(data.getFields().get("myBountyValue"));
		myObservers = new ArrayList<IObserver<IViewable>>();
		bountySystem.attachComponent(this);
	}
	/**
	 * A method to retrieve the bounty value associated with an entity
	 * 
	 * @return the bounty value
	 */
	@Override
	public int getBounty()
	{
		return myBountyValue;
	}
	
	@Override
	public void distributeInfo() {
		// TODO Auto-generated method stub
		
	}
	
	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

}
