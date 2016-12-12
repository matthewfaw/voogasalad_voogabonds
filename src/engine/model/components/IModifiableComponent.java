package engine.model.components;

import engine.model.entities.IEntity;

/**
 * Allows for the setting of the entity the component is attached to, in addition to the normal component features.
 * @author Weston
 *
 */
public interface IModifiableComponent extends IComponent {
	abstract public void setEntity(IEntity e);

}
