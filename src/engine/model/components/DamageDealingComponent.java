package engine.model.components;

import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.strategies.IDamageStrategy;
import engine.model.weapons.DamageInfo;

public class DamageDealingComponent implements IComponent {
	private IDamageStrategy myDamageStrategy;
	private int myDamage;
	private double myDamageRadius;
	
	private IEntity myEntity;

	public DamageDealingComponent() {
	}
	
	public int getDamage()
	{
		return myDamage;
	}
	
	public double getDamageRadius()
	{
		return myDamageRadius;
	}
	
	@Override
	public IEntity getEntity() {
		return myEntity;
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
