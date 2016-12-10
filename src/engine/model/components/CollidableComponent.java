package engine.model.components;

import authoring.model.Hide;
import engine.model.collision_detection.ICollidable;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.RewardSystem;
import utility.Damage;

/**
 * The purpose of this class is to encapsulate the information relevant
 * to entities that can collide
 * These entities will register with the CollisionDetectionSystem, and this
 * class will define what happens when a collision occurs
 * @author matthewfaw
 *
 */
public class CollidableComponent extends AbstractComponent implements ICollidable {
	private double myCollisionRadius;
	
	@Hide
	private HealthSystem myHealthSystem;
	@Hide
	private DamageDealingSystem myDamageDealingSystem;
	@Hide
	private RewardSystem myRewardSystem;
	@Hide
	private CollisionDetectionSystem myCollisionDetectionSystem;
	
	public CollidableComponent (CollisionDetectionSystem collisionDetectionSystem, 
			HealthSystem healthSystem,
			DamageDealingSystem damageDealingSystem, 
			RewardSystem rewardSystem) {
		
		myCollisionDetectionSystem = collisionDetectionSystem;
		myHealthSystem = healthSystem;
		myDamageDealingSystem = damageDealingSystem;
		myRewardSystem = rewardSystem;
		
		myCollisionDetectionSystem.attachComponent(this);
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
		
		//XXX: This feels like we're butting into health component/system's business
		if (myHealthSystem.isDead(this.getEntity())) {
			/* Money system needs to know: 
			 * * who to give money to
			 * * how much money to give
			 */
			//myRewardSystem.giveMoneyTo(myPlayer, this);
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
		
		//TODO: Check if any explosions happen.
	}

	private void dealDamage(CollidableComponent a, CollidableComponent b) {
		// Get damage dealt by a
		Damage damage = myDamageDealingSystem.getDamageDealtToTarget(a.getEntity());
		// Deal damage to b
		myHealthSystem.takeDamage(b.getEntity(), damage);
	}
	
}
