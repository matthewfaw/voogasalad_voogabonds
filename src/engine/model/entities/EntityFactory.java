package engine.model.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import authoring.model.ComponentData;
import authoring.model.EntityData;
import engine.model.components.ComponentFactory;
import engine.model.components.IComponent;
import engine.model.strategies.IPhysical;
import engine.model.systems.ISystem;
import utility.Point;
/**
 * Creates all the entities
 * @author owenchung and alanguo
 *
 */
public class EntityFactory {
	private ComponentFactory myComponentFactory;
	private List<ISystem> mySystems;

	public EntityFactory(List<ISystem> systems) {
		myComponentFactory = new ComponentFactory(systems);
		mySystems = systems;
	}
	
	/**
	 * Using its name, fetch the Entity data out of a map (that the factory owns) of all the entity data.
	 * This way, you wan't need to know everything about an entity to make it, just the entity's name.
	 * @param entityName
	 * @return the constructed entity
	 */
	public IEntity constructEntity(String entityName, Point start, double heading, IPhysical target) {
		return null;
	}
	
	/*
	 * This needs more information. EntityData won't have a starting location, starting heading, or a target, so whoever
	 * calls constructEntity will need to supply them.
	 */
	@Deprecated
	public IEntity constructEntity(EntityData aEntityData ) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		IEntity entity = new ConcreteEntity();
		List<ComponentData> componentMap = aEntityData.getComponents();
		for (ComponentData compdata : componentMap) {
			IComponent component = myComponentFactory.constructComponent(compdata);
			entity.addComponent(component);	
		}
		
		//1. Construct the entity object
		//2. Construct each component using the component factory, and link this to the component object
		//2.5 Attach components to relevant systems?
		//3. return the fully constructed object

		return entity;
	}
}
