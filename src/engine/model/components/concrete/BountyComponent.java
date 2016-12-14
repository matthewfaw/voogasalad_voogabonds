package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;

import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewableBounty;
import engine.model.entities.IEntity;
import engine.model.systems.BountySystem;
import gamePlayerView.gamePlayerView.Router;

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
	private int myLivesToDestroy;
	private int myPointValue;
	
	@Hide
	private transient List<IObserver<IViewableBounty>> myObservers;
	
	@Hide
	private transient BountySystem myBountySystem;
	
	public BountyComponent(IEntity aEntity, BountySystem bountySystem, ComponentData data, Router router) {
		super(aEntity, router);
		myBountyValue = Integer.parseInt(data.getFields().get("myBountyValue"));
		myLivesToDestroy = Integer.parseInt(data.getFields().get("myLivesToDestroy"));
		myPointValue = Integer.parseInt(data.getFields().get("myPointValue"));
		
		myObservers = new ArrayList<IObserver<IViewableBounty>>();
		myBountySystem = bountySystem;
		
		myBountySystem.attachComponent(this);
	}
	/**
	 * A method to retrieve the bounty value associated with an entity
	 * 
	 * @return the bounty value
	 */
	@Override
	public int getBounty() {
		return myBountyValue;
	}
	
	@Override
	public int getLivesTaken() {
		return myLivesToDestroy;
	}
	
	@Override
	public int getPoints() {
		return myPointValue;
	}
	
	@Override
	public void distributeInfo() {
		getRouter().distributeViewableComponent(this);
	}
	
	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewableBounty> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewableBounty> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

	public void delete() {
		myBountySystem.detachComponent(this);
	}
}
