package engine.model.entities;

import java.util.List;

import engine.model.components.IComponent;
import engine.model.game_environment.terrain.Terrain;
import utility.Point;

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
	 * Add a component to an entity
	 * This is how entities are given new functionality
	 * @param aComponent
	 */
	public void addComponent(IComponent aComponent);
	/**
	 * A method to set the current location of an entity on the map
	 * Assumes that all entities can be placed on the map
	 * 
	 * @param aLocation to place the entity
	 */
	public void setLocation(Point aLocation);
}
