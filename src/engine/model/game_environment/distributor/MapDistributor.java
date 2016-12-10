package engine.model.game_environment.distributor;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import authoring.model.EntityData;
import engine.controller.PlayerController;
import engine.model.components.PhysicalComponent;
import engine.model.entities.EntityFactory;
import engine.model.game_environment.MapMediator;
import engine.model.playerinfo.Player;
import engine.model.resourcestore.ResourceStore;
import utility.Point;

public class MapDistributor {
	
	private EntityFactory myEntityFactory;
	private ResourceStore myResourceStore;
	private MapMediator myMapMediator;
	private List<PhysicalComponent> myPhysicalComponents;
	private PlayerController myPlayerController;
	
	public MapDistributor(MapMediator mapMediator, ResourceStore resourceStore, 
			EntityFactory entityFactory, PlayerController playerController)
	{
		myEntityFactory = entityFactory;
		myMapMediator = mapMediator;
		myResourceStore = resourceStore;
		myPlayerController = playerController;
	}
	
	public void distribute(EntityData entityData, String playerID, Point aLocation) {
		String validTerrainsStr = entityData.getComponents().get("PhysicalComponent").getFields().get("myValidTerrains");
		
		// parse valid terrains into list of terrain data
		List<String> validTerrains = Arrays.asList(validTerrainsStr.split(", "));
		
		boolean canPlace = myMapMediator.attemptToPlaceEntity(aLocation, validTerrains);
		if (canPlace) {
			createEntity(entityData);
			deductCostFromPlayer(entityData.getBuyPrice());
		}
	}

	private void createEntity(EntityData entityData) {
		try {
			myEntityFactory.constructEntity(entityData.getName());
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			// Router.distributeError("Can't construct entity.");
		}
	}

	/**
	 * Changes active player's money by negative buy price.
	 * @param buyPrice
	 */
	private void deductCostFromPlayer(int buyPrice) {
		Player myPlayer = myPlayerController.getActivePlayer();
		myPlayer.updateAvailableMoney(-1*buyPrice);
	}
	
//	public void distribute(String aEntityName, String aPlayerID, Point aLocation)
//	{
//		
//		// find physical component from entity name
//		PhysicalComponent aPhysicalComponent = ;
//		
//		// ask map mediator if you can place
//		myMapMediator.attemptToPlaceEntity(aLocation, aPhysicalComponent);
//		// if yes
//			// deduct cost from player
//			// create new entity
//		
//	}

}
