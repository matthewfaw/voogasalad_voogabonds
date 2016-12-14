package engine.controller.system;

import java.util.ArrayList;
import java.util.List;

import authoring.model.EntityData;
import engine.controller.PlayerController;
import engine.controller.timeline.TimelineController;
import engine.model.components.AbstractComponent;
import engine.model.components.IComponent;
import engine.model.components.concrete.CollidableComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.entities.EntityManager;
import engine.model.game_environment.MapMediator;
import engine.model.systems.BountySystem;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.ControllableSystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.ISystem;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.SpawningSystem;
import engine.model.systems.TargetingSystem;
import engine.model.systems.TeamSystem;
import gamePlayerView.gamePlayerView.Router;

public class SystemsController {
	private transient List<ISystem<?>> mySystems;

	private CollisionDetectionSystem myCollisionDetectionSystem;
	private DamageDealingSystem myDamageDealingSystem;
	private HealthSystem myHealthSystem;
	private MovementSystem myMovementSystem;
	private PhysicalSystem myPhysicalSystem;
	private BountySystem myBountySystem;
	private SpawningSystem mySpawningSystem;
	private TargetingSystem myTargetingSystem;
	private TeamSystem myTeamSystem;
	private ControllableSystem myControllableSystem;
	
	public SystemsController()
	{
		mySystems = new ArrayList<ISystem<?>>();
	}
	
	public void initializeSystems(PlayerController aPlayerController, MapMediator aMapMediator, TimelineController aTimelineController) 
	{
		myTeamSystem = new TeamSystem();
		myHealthSystem = new HealthSystem();
		myBountySystem = new BountySystem(aPlayerController);
		myDamageDealingSystem = new DamageDealingSystem();
		
		// ORDERING MATTERS for physical -> targeting -> collision -> movement
		myPhysicalSystem = new PhysicalSystem(aMapMediator);
		
		myTargetingSystem = new TargetingSystem();
		myCollisionDetectionSystem = new CollisionDetectionSystem();
		
		myMovementSystem = new MovementSystem(aMapMediator, aTimelineController);
		mySpawningSystem = new SpawningSystem(aTimelineController);
		
		myControllableSystem = new ControllableSystem();
		
		createSystemsList();
	}
	
	public void reinitializeSystems(PlayerController aPlayerController, MapMediator aMapMediator, TimelineController aTimelineController)
	{
		myBountySystem.setPlayerController(aPlayerController);
		myPhysicalSystem.setMapMediator(aMapMediator);
		myMovementSystem.setStrategyFactory(aMapMediator);
		aTimelineController.attach(myMovementSystem);
		aTimelineController.attach(mySpawningSystem);
		createSystemsList();
	}
	
	private void reinitializeComponent(Router aRouter, List<? extends AbstractComponent> aComponentList, EntityManager aEntityManager, DataStore<EntityData> aEntityDataStore)
	{
		for (AbstractComponent c: aComponentList) {
			c.setSystems(mySystems);
			c.reconnectToEntity(aEntityManager);
			c.updateComponentData(aEntityDataStore.getData(c.getEntity().getId()).getComponent(c.getClass().getName()));
			c.redistributeThroughRouter(aRouter);
		}
	}
	
	public void reinitializeComponents(Router aRouter, EntityManager aEntityManager, DataStore<EntityData> aEntityDataStore)
	{
		for (ISystem<?> system: mySystems) {
			reinitializeComponent(aRouter, system.getComponents(), aEntityManager, aEntityDataStore);
		}
	}
	
	public void setEntityFactory(EntityFactory aEntityFactory)
	{
		mySpawningSystem.setEntityFactory(aEntityFactory);
	}
	
	private void createSystemsList()
	{
		mySystems.add(myCollisionDetectionSystem);
		mySystems.add(myDamageDealingSystem);
		mySystems.add(myHealthSystem);
		mySystems.add(myMovementSystem);
		mySystems.add(myPhysicalSystem);
		mySystems.add(myBountySystem);
		mySystems.add(mySpawningSystem);
		mySystems.add(myTargetingSystem);
		mySystems.add(myTeamSystem);
		mySystems.add(myControllableSystem);
	}
	
	/**
	 * a method to send the keyboard control signal to the correct systems
	 * @param aSignal
	 */
	public void sendControlSignal(String aMovementSignal)
	{
		System.out.println("moving at the back end");
		myControllableSystem.move(aMovementSignal);
	}
	
	public List<ISystem<?>> getSystems()
	{
		return mySystems;
	}
	
	public PhysicalSystem getPhysicalSystem()
	{
		return myPhysicalSystem;
	}
	
	public MovementSystem getMovementSystem()
	{
		return myMovementSystem;
	}
	
	/*
	public static void main(String[] args)
	{
		SystemsController c = new SystemsController();
		c.initializeSystems(null, null, null);
		c.createSystemsList();
	}
	*/
}
