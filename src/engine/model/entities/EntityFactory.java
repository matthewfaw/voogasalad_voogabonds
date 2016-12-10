package engine.model.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;


import authoring.model.ComponentData;
import authoring.model.EntityData;
import engine.controller.waves.PathFollowerData;
import engine.model.components.ComponentFactory;
import engine.model.components.IComponent;
import engine.model.components.IModifiableComponent;
import engine.model.data_stores.DataStore;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;
/**
 * Creates all the entities
 * @author owenchung and alanguo
 *
 */
public class EntityFactory {
	private ComponentFactory myComponentFactory;
	private List<ISystem> mySystems;
	private DataStore<EntityData> myEntityDataStore;
	private Router myRouter;

	public EntityFactory(List<ISystem> systems, DataStore<EntityData> entityDataStore, Router router) {
		mySystems = systems;
		myEntityDataStore = entityDataStore;
		myRouter = router;
		myComponentFactory = new ComponentFactory(systems, myRouter); // depends on router initialization
	}
	
	/**
	 * Using its name, fetch the Entity data out of a map (that the factory owns) of all the entity data.
	 * This way, you wan't need to know everything about an entity to make it, just the entity's name.
	 * @param entityName
	 * @return the constructed entity
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public IEntity constructEntity(String entityName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		IEntity entity = new ConcreteEntity();
		EntityData entityData = myEntityDataStore.getData(entityName);
		Collection<ComponentData> componentMap = entityData.getComponents().values();
		for (ComponentData compdata : componentMap) {
			IModifiableComponent component = myComponentFactory.constructComponent(compdata);
			entity.addComponent(component);	
		}
		return entity;
	}
	
	public IEntity constructEntity(EntityData aEntityData) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		IEntity entity = new ConcreteEntity();
		Collection<ComponentData> componentMap = aEntityData.getComponents().values();
		for (ComponentData compdata : componentMap) {
			IModifiableComponent component = myComponentFactory.constructComponent(compdata);
			entity.addComponent(component);	
		}
		
		//1. Construct the entity object
		//2. Construct each component using the component factory, and link this to the component object
		//2.5 Attach components to relevant systems?
		//3. return the fully constructed object

		return entity;
	}

}
