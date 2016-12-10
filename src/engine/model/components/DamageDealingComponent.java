package engine.model.components;

import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.IPhysical;
import engine.model.systems.DamageDealingSystem;
import engine.model.weapons.DamageInfo;
import utility.Damage;

/**
 * The purpose of this class is to manage the information relevant to 
 * dealing damage to another entity
 * 
 * @author matthewfaw
 *
 */
public class DamageDealingComponent extends AbstractComponent {
	private IDamageStrategy myDamageStrategy;
	private int myDamage;
	private double myDamageRadius;
	
	private IEntity myEntity;

	private DamageDealingSystem myDamageDealingSystem;
	
	public DamageDealingComponent(DamageDealingSystem damageDealingSystem) {
		myDamageDealingSystem = damageDealingSystem;
		myDamageDealingSystem.attachComponent(this);
	}
	
	/**
	 * gets the amount of damage this entity inflicts on its target
	 * @return the damage value
	 */
	public Damage getTargetDamage()
	{
		return myDamageStrategy.getTargetDamage(myDamage);
	}
	
	public Damage getDamageTo(IPhysical dealer, IPhysical taker)
	{
		return myDamageStrategy.getAoEDamage(dealer, taker, myDamage);
	}
	
	/**
	 * gets the radius of effect of this entity
	 * @return the radius
	 */
	public double getDamageRadius()
	{
		return myDamageRadius;
	}

	/*
	@Override
	public void update(IComponent aChangedObject) {
		//TODO: Is there a better way to only update when called by a CollidableComponent?
		if (aChangedObject instanceof CollidableComponent) {
			CollidableComponent c = (CollidableComponent) aChangedObject;
			
			if (	myTargetsEnemies && !c.getOwner().isAlly(myOwner) ||
					!myTargetsEnemies && c.getOwner().isAlly(myOwner)) {
				explode(c.getCollidedWith());
			}
		}
		if (aChangedObject instanceof MoveableComponent) {
			explode();
		}
		
	}
	*/

	//TODO: This should envoke the damage dealing strategy
	private DamageInfo explode() {
		/*
		List<PhysicalComponent> targets = myMap.withinRange(getPosition(), myAoERadius);
		
		DamageInfo result = new DamageInfo();
		
		for (PhysicalComponent p: targets) {

			Damage toDeal;
			if (myOwner.isAlly(p.getOwner()))
				toDeal = myDamageCalc.getAoEAllyDamage(this, myTarget.getPosition(), myDamage);
			else
				toDeal = myDamageCalc.getAoEDamage(this, myTarget.getPosition(), myDamage);
			
			result = result.add(p.takeDamage(toDeal));
		}
		// destroySelf();
		// remove references
		unregisterMyself();
		return result;
		*/
		return null;
	}

	private DamageInfo explode(ICollidable collidedWith) {
		/*
		DamageInfo result = new DamageInfo();
		
		result.add(collidedWith.takeDamage(myDamageCalc.getTargetDamage(myDamage)));
		result.add(explode());
		
		myOwner.notifyDestroy(result);
		getOwner().updateAvailableMoney(result.getMoney());
		*/
		return null;
	}

}
