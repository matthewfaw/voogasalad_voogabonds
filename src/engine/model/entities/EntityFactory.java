package engine.model.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import authoring.model.ComponentData;
import authoring.model.EntityData;
import engine.controller.waves.PathFollowerData;
import engine.model.components.ComponentFactory;
import engine.model.components.IComponent;
import engine.model.components.IModifiableComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.data_stores.DataStore;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.IPosition;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;
import utility.Point;
/**
 * Creates all the entities
 * @author owenchung 
 * @author alanguo
 *
 */
public class EntityFactory {
	private static final Point DEFAULT_LOCATION = new Point(0,0);
	private ComponentFactory myComponentFactory;
	private List<ISystem> mySystems;
	private DataStore<EntityData> myEntityDataStore;
	private Router myRouter;
	private MapMediator myMapMediator;

	public EntityFactory(List<ISystem> systems, DataStore<EntityData> entityDataStore, Router router, MapMediator mapMediator) {
		mySystems = systems;
		myEntityDataStore = entityDataStore;
		myRouter = router;
		myComponentFactory = new ComponentFactory(systems, myRouter); // depends on router initialization
		myMapMediator = mapMediator;
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
			IModifiableComponent component = myComponentFactory.constructComponent(compdata, null);
			entity.addComponent(component);	
		}
		return entity;
	}
	
	public IEntity constructEntity(EntityData aEntityData) 
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return constructEntity(aEntityData, DEFAULT_LOCATION);
	}
	
	public IEntity constructEntity(EntityData aEntityData, Point aLocation) 
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		IEntity entity = new ConcreteEntity();
		Collection<ComponentData> componentMap = aEntityData.getComponents().values();
		for (ComponentData compdata : componentMap) {
			IModifiableComponent component = myComponentFactory.constructComponent(compdata, aLocation);
			entity.addComponent(component);	
		}
		
		//1. Construct the entity object
		//2. Construct each component using the component factory, and link this to the component object
		//2.5 Attach components to relevant systems?
		//3. return the fully constructed object

		return entity;
	}
	
	private boolean hasPhysicalComponent(EntityData entityData) {
		Map<String, ComponentData> componentMap = entityData.getComponents();
		return componentMap.containsKey("PhysicalComponent");
	}
	
	/**
	 * Tries to distribute an entity on the map by constructing it.
	 * @param entityDataName
	 * @param aLocation
	 * @return true if succeeded in creating entity; else false
	 */
	public boolean distributeEntity(String entityDataName, Point aLocation) {
		if (myEntityDataStore.hasKey(entityDataName)) {
			return distributeEntity(myEntityDataStore.getData(entityDataName), aLocation);
		} else {
			// Distribute error "No such entity name."
			return false;
		}
	}
	
	/**
	 * Tries to construct an entity with a physical component.
	 * @param entityData
	 * @param aLocation
	 * @return true if successfully constructed an entity; false if not
	 */
	private boolean distributeEntity(EntityData entityData, Point aLocation) {
		if (hasPhysicalComponent(entityData)) {
			String validTerrainsStr = entityData.getComponents().get("PhysicalComponent").getFields().get("myValidTerrains");
			// parse valid terrains into list of terrain data
			List<String> validTerrains = Arrays.asList(validTerrainsStr.split(", "));
			
			boolean canPlace = myMapMediator.isAValidTerrain(aLocation, validTerrains);
			if (canPlace) {
				try {
					constructEntity(entityData, aLocation);
					return true;
				} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
//				deductCostFromPlayer(entityData.getBuyPrice());
			}
		}
		return false;
	}

}
