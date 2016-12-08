package engine.model.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import authoring.model.ComponentData;
import authoring.model.EntityData;
import engine.model.components.ComponentFactory;
import engine.model.components.IComponent;
import engine.model.systems.ISystem;
/**
 * Creates all the entities
 * @author owenchung and alanguo
 *
 */
public class EntityFactory {
	private ComponentFactory myComponentFactory;
	private List<ISystem> mySystems;
	
	public EntityFactory(List<ISystem> systems ) {
		myComponentFactory = new ComponentFactory(systems);
		mySystems = systems;
	}
	//1. Construct the entity object
	//2. Construct each component using the component factory, and link this to the component object
	//3. return the fully constructed object
	public IEntity constructEntity(EntityData aEntityData ) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		IEntity entity = new ConcreteEntity();
		List<ComponentData> componentMap = aEntityData.getComponents();
		for (ComponentData compdata : componentMap) {
			IComponent component = myComponentFactory.constructComponent(compdata);
			entity.addComponent(component);	
		}

		return entity;
	}
}
