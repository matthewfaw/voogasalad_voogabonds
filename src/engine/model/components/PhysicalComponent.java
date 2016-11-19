package engine.model.components;

import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;
import engine.model.game_environment.terrain.Terrain;
import utility.Point;

public class PhysicalComponent implements IComponent, IObservable {
	private List<Terrain> myValidTerrains;
	private Point myLocation;
	
	private IEntity myEntity;
	
	public PhysicalComponent(PhysicalComponentData aPhysicalComponentData)
	{
		//TODO
	}
	PhysicalComponent(IEntity aEntity, List<Terrain> aValidTerrainList, Point aLocation)
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
	public void attach(IObserver aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	//*******************IComponent interface***********//
	@Override
	public IEntity getEntity() {
		return myEntity;
	}
}
