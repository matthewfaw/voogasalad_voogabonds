package engine.model.components;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.machine.Machine;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.projectiles.Projectile;
import engine.model.strategies.IPhysical;
import javafx.util.Pair;
import utility.Point;

public class PhysicalComponent implements IComponent, IPhysical {
	private List<IObserver<IComponent>> myObservers;

	private IModifiablePlayer myOwner;
	private List<String> myValidTerrains;
	private Point myLocation;
	private double myHeading;

	private IEntity myEntity;
	
	public PhysicalComponent(PhysicalComponentData aPhysicalComponentData)
	{
		//TODO
		myObservers = new ArrayList<IObserver<IComponent>>();
	}
	PhysicalComponent(IEntity aEntity, List<String> aValidTerrainList, Point aLocation)
	{
		myEntity = aEntity;

		myValidTerrains = aValidTerrainList;
		myLocation = aLocation;
	}
	


	//********************IObservable interface***********//
	@Override
	public void attach(IObserver<IComponent> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IComponent> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(o -> o.update(this));
	}

	//*******************IComponent interface***********//
	@Override
	public IEntity getEntity() {
		return myEntity;
	}
	
	//*******************IPhysical interface***********//
	/**
	 * A method to get the valid terrains on which an entity may be placed
	 * This assumes that every entity may be placed on a location
	 * @return list of terrains on which the entity may be placed
	 */
	public List<String> getValidTerrains()
	{
		return myValidTerrains;
	}
	/**
	 * A method to get the current location of the
	 * corresponding entity
	 * @return
	 */
	@Override
	public Point getPosition() {
		return myLocation;
	}
	@Override
	public double getHeading() {
		return myHeading;
	}
	@Override
	public double getCollisionRadius() {
		return 0;
	}
	
	/**
	 * A method to set the current location of an entity on the map
	 * Assumes that all entities can be placed on the map
	 * 
	 * @param aLocation to place the entity
	 */
	@Override
	public void setPosition(Pair<Double, Point> p) {
		myLocation = p.getValue();
		myHeading = p.getKey();
		
	}
	@Override
	public IModifiablePlayer getOwner() {
		return myOwner;
	}

}
