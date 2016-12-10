package engine.model.game_environment.distributor;

import engine.model.entities.EntityFactory;
import engine.model.game_environment.MapMediator;
import engine.model.resourcestore.IModifiableStore;
import engine.model.resourcestore.ResourceStore;
import utility.Point;

public class MapDistributor {
	
	private EntityFactory myEntityFactory;
	
	public MapDistributor(MapMediator aMapMediator, ResourceStore resourceStore, EntityFactory entityFactory)
	{
		myEntityFactory = entityFactory;
	}
	
	public void distribute(String aEntityName, String aPlayerID, Point aLocation)
	{
		
	}

}
