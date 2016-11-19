package engine.model.game_environment.distributors;

import engine.model.entities.EntityData;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import utility.Point;

/**
 * This class manages distributing entities to the map
 * given data to construct a entity
 * @author matthewfaw
 *
 */
public class MapDistributor implements IDistributor {
	private MapMediator myMapMediator;
	private EntityFactory myEntityFactory;
	
	public MapDistributor(MapMediator aMapMediator)
	{
		myMapMediator = aMapMediator;
		myEntityFactory = new EntityFactory();
	}

	/**
	 * Constructs the Entity object, and places it on the map, if the location
	 * is valid
	 */
	@Override
	public boolean distribute(EntityData aEntityData, Point aLocation)
	{
		//TODO: possibly check if machine can be placed before constructing the object?
		//1: construct object 
		//2: determine if the object can be placed on the map
		//3: distribute object to the map, if it can be placed there
		IEntity machine = myEntityFactory.constructEntity(aEntityData);
		return myMapMediator.attemptToPlaceEntity(aLocation, machine);
	}

}
