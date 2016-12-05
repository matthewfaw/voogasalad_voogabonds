package engine.model.components;

import java.util.List;

import engine.IObserver;
import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.strategies.IDamageStrategy;
import engine.model.weapons.DamageInfo;

public class DamageDealingComponent implements IComponent, IObserver<IComponent> {
	private IDamageStrategy myDamageCalc;
	private boolean myTargetsEnemies;
	private IModifiablePlayer myOwner;
	private double myDamage;

	public DamageDealingComponent(CollidableComponent c) {
		c.attach(this);
	}
	
	@Override
	public void attach(IObserver<IComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<IComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

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

	public double getDamage() {
		return myDamage;
	}


}
