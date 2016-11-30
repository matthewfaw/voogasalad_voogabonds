package engine.model.components;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.model.entities.IEntity;
import utility.Point;

public class PhysicalComponent implements IComponent {
	private List<IObserver<IComponent>> myObservers;

	private List<String> myValidTerrains;
	private Point myLocation;
	
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
	 * A method to set the current location of an entity on the map
	 * Assumes that all entities can be placed on the map
	 * 
	 * @param aLocation to place the entity
	 */
	public void setLocation(Point aLocation)
	{
		myLocation = aLocation;
	}
	/**
	 * A method to get the current location of the
	 * corresponding entity
	 * @return
	 */
	public Point getCurrentLocation()
	{
		return myLocation;
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
}
