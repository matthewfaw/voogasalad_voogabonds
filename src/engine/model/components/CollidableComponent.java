package engine.model.components;

import java.util.List;

import engine.IObserver;
import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.RewardSystem;

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
	
	private HealthSystem myHealthSystem;
	private DamageDealingSystem myDamageDealingSystem;
	private RewardSystem myRewardSystem;

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
		
		// Have both components deal damage to each other
		dealDamage(this, unmovedCollidable);
		dealDamage(unmovedCollidable, this);
		
		if (myHealthSystem.isDead(this.getEntity())) {
			/* Money system needs to know: 
			 * * who to give money to
			 * * how much money to give
			 */
//			myRewardSystem.giveMoneyTo(myPlayer, this);
			this.getEntity().delete();
		}
		if (myHealthSystem.isDead(unmovedCollidable.getEntity())) {
			/* Money system needs to know: 
			 * * who to give money to
			 * * how much money to give
			 */
			// myMoneySystem.giveMoneyTo(myPlayer, deltaMoney);
			unmovedCollidable.getEntity().delete();
		}
	}

	private void dealDamage(CollidableComponent a, CollidableComponent b) {
		// Get damage dealt by a
		int damage = myDamageDealingSystem.getDamageDealt(a.getEntity());
		// Deal damage to b
		myHealthSystem.takeDamage(b.getEntity(), damage);
	}
	
}
