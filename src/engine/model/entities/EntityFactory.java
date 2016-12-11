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
import engine.model.data_stores.DataStore;
import engine.model.game_environment.MapMediator;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;
import utility.Point;
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
			IModifiableComponent component = myComponentFactory.constructComponent(compdata);
			entity.addComponent(component);	
		}
		return entity;
	}
	
	public IEntity constructEntity(EntityData aEntityData) 
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		System.out.println("Constructing entity: "+aEntityData.getName());
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
	
	private boolean hasPhysicalComponent(EntityData entityData) {
		Map<String, ComponentData> componentMap = entityData.getComponents();
		return componentMap.containsKey("PhysicalComponent");
	}
	
	public void distributeEntity(String entityDataName, Point aLocation) {
		if (myEntityDataStore.hasKey(entityDataName)) {
			distributeEntity(myEntityDataStore.getData(entityDataName), aLocation);
		} else {
			// Distribute error "No such entity name."
		}
	}
	
	private void distributeEntity(EntityData entityData, Point aLocation) {
		if (hasPhysicalComponent(entityData)) {
			String validTerrainsStr = entityData.getComponents().get("PhysicalComponent").getFields().get("myValidTerrains");
			System.out.println("Valid Terrains: "+validTerrainsStr);
			// parse valid terrains into list of terrain data
			List<String> validTerrains = Arrays.asList(validTerrainsStr.split(", "));
			
			boolean canPlace = myMapMediator.isAValidTerrain(aLocation, validTerrains);
			if (canPlace) {
				try {
					constructEntity(entityData);
				} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				deductCostFromPlayer(entityData.getBuyPrice());
			}
		}
	}

}
