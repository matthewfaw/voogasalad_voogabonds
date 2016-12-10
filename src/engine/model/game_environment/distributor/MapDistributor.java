package engine.model.game_environment.distributor;

import engine.model.entities.EntityFactory;
import engine.model.game_environment.MapMediator;
import utility.Point;

public class MapDistributor {
	
	private EntityFactory myEntityFactory;
	
	public MapDistributor(MapMediator aMapMediator, EntityFactory entityFactory)
	{
		myEntityFactory = entityFactory;
	}
	
	public void distribute(String aEntityName, String aPlayerID, Point aLocation)
	{
		
	}

}
