package engine.model.entities;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import authoring.model.ComponentData;
import authoring.model.EntityData;
import engine.model.components.ComponentFactory;
import engine.model.components.IComponent;
import engine.model.systems.ISystem;
/**
 * 
 * @author owenchung and alanguo
 *
 */
public class EntityFactory {
	private ComponentFactory myComponentFactory;
	private List<ISystem> mySystems;
	public EntityFactory(List<ISystem> systems ) {
		myComponentFactory = new ComponentFactory();
		mySystems = systems;
	}
	
	public IEntity constructEntity(EntityData aEntityData ) {
		IEntity entity = new ConcreteEntity();
		Map<String, ComponentData> componentMap = aEntityData.getComponents();
		for (Entry<String, ComponentData> mapEntry : componentMap.entrySet()) {
			String className = mapEntry.getKey();
			IComponent component = myComponentFactory.constructComponent(entity, mapEntry.getKey(), mapEntry.getValue());
			entity.addComponent(component);
			
		}
		//1. Construct the entity object
		//2. Construct each component using the component factory, and link this to the component object
		//3. return the fully constructed object
		return entity;
	}
}
