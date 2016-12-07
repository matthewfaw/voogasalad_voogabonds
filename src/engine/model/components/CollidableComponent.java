package engine.model.components;

import java.util.List;

import engine.IObserver;
import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.systems.HealthSystem;
import javafx.util.Pair;
import utility.Point;

/**
 * The purpose of this class is to encapsulate the information relevant
 * to entities that can collide
 * These entities will register with the CollisionDetectionSystem, and this
 * class will define what happens when a collision occurs
 * @author matthewfaw
 *
 */
public class CollidableComponent implements IComponent, ICollidable {
	private List<IObserver<IComponent>> myObservers;
	private PhysicalComponent myPhysical;
	
	private double myCollisionRadius;
	
	private boolean myDestroyOnCollision;
//	private HealthSystem myHealthSystem;
//	private MoneySystem myMoneySystem;

	//*******************IComponent interface***********//
	@Override
	public IEntity getEntity() {
		return myPhysical.getEntity();
	}

	//*******************ICollidable interface***********//
	public double getCollisionRadius()
	{
		return myCollisionRadius;
	}
	
	@Override
	public void collideInto(CollidableComponent unmovedCollidable) {
		/*
		 * Health System needs to know:
		 * * how much health to change
		 * * which entity's health to change 
		 */
		// healthSystem.changeHealth(this, deltaHealth);
		myObservers.forEach(o -> o.update(this));
		if (myDestroyOnCollision) {
			/* Money system needs to know: 
			 * * who to give money to
			 * * how much money to give
			 */
			// myMoneySystem.giveMoneyTo(myPlayer, deltaMoney);
			getEntity().delete();
		}
	}
	
}
