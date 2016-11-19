package engine.model;

import java.util.List;

import engine.model.game_environment.terrain.Terrain;

/**
 * an interface to capture the commonalities among each object that can be added
 * to the game map
 * 
 * https://en.wikipedia.org/wiki/Entity%E2%80%93component%E2%80%93system
 * Entity: The entity is a general purpose object. Usually, it only consists of a unique id. They "tag every 
 * coarse gameobject as a separate item". Implementations typically use a plain integer for this.
 * 
 * @author matthewfaw
 *
 */
public interface IEntity {

	/**
	 * a method to get the unique identifier corresponding to the entity
	 * @return Unique object ID
	 */
	public int getId();
	/**
	 * A method to get the valid terrains on which an entity may be placed
	 * This assumes that every entity may be placed on a location
	 * @return list of terrains on which the entity may be placed
	 */
	public List<Terrain> getValidTerrains();

	/**
	 * 
	 * @param aComponent
	 */
	public void addComponent(IComponent aComponent);
	public void setLocation(Point aLocation);
}
