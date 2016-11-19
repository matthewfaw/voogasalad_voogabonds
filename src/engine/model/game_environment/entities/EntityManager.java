package engine.model.game_environment.entities;

import java.util.ArrayList;

import engine.model.IEntity;

public class EntityManager {
	private ArrayList<IEntity> myEntities;

	public EntityManager()
	{
		myEntities = new ArrayList<IEntity>();
	}
	
	/**
	 * Adds the entity to the list of entities to track
	 * @param aEntityToTrack
	 */
	public void trackEntity(IEntity aEntityToTrack)
	{
		myEntities.add(aEntityToTrack);
	}
}
