package engine.model.components;

import java.util.ArrayList;
import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;
import engine.model.game_environment.terrain.Terrain;
import utility.Point;

public class PhysicalComponent extends AbstractComponent implements IObservable<PhysicalComponent> {
	private List<IObserver<PhysicalComponent>> myObservers;

	private List<Terrain> myValidTerrains;
	private Point myLocation;
	
	public PhysicalComponent(IEntity aEntity, PhysicalComponentData aPhysicalComponentData, List<Terrain> aValidTerrainList, Point aLocation)
	{
		super(aEntity);
		//TODO
		myObservers = new ArrayList<IObserver<PhysicalComponent>>();

		myValidTerrains = aValidTerrainList;
		myLocation = aLocation;
	}
	
	/**
	 * A method to get the valid terrains on which an entity may be placed
	 * This assumes that every entity may be placed on a location
	 * @return list of terrains on which the entity may be placed
	 */
	public List<Terrain> getValidTerrains()
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
	public void attach(IObserver<PhysicalComponent> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<PhysicalComponent> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(o -> o.update(this));
	}
}
