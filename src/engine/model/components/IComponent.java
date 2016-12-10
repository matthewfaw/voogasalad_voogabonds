package engine.model.components;

import engine.IObservable;
import engine.model.entities.IEntity;

/**
 * An interface to capture the commonalities of all components.
 * 
 * See https://en.wikipedia.org/wiki/Entity-component-system
 * Component: the raw data for one aspect of the object, and how it 
 * interacts with the world. "Labels the Entity as possessing this particular aspect". 
 * Implementations typically use Structs, Classes, or Associative Arrays
 * 
 * @author matthewfaw
 *
 */
public interface IComponent {
	/**
	 * Gets the Entity object which owns this component
	 * Assumes that component is owned by a entity
	 * @return
	 */
	public IEntity getEntity();
}
