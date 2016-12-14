package engine.controller.system;

import java.util.ArrayList;
import java.util.List;

import engine.controller.PlayerController;
import engine.controller.timeline.TimelineController;
import engine.model.entities.EntityFactory;
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

public class SystemsController {
	List<ISystem<?>> mySystems;

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
	
	public void initializeSystems(PlayerController aPlayerController, MapMediator aMapMediator, TimelineController aTimelineController, EntityFactory aEntityFactory) 
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
		mySpawningSystem = new SpawningSystem(aTimelineController, aEntityFactory);
		
		myControllableSystem = new ControllableSystem();
		
		createSystemsList();
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
