package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.systems.HealthSystem;
import utility.Damage;

/**
 * The purpose of this class is to manage the health information
 * relevant to an entity
 * manages how an entity should take damage
 * @author matthewfaw
 * @author Weston
 * @author owenchung (edits)
 *
 */
public class HealthComponent implements IComponent {
	private static double DEFAULT_HEALTH = 0.0;
	
	//private HealthSystem myHealthSystem;
	private Double myCurrHealth;
	private Double myMaxHealth;
	private IEntity myEntity;
	
	public HealthComponent(IEntity entity, HealthSystem healthSystem, double maxHealth) {
		myEntity = entity;
		
		//myHealthSystem = healthSystem;
		healthSystem.attachComponent(this);
		
		myCurrHealth = maxHealth;
		myMaxHealth = maxHealth;
	}
	
	public HealthComponent(IEntity entity, HealthSystem healthSystem) {
		myEntity = entity;
		
		//myHealthSystem = healthSystem;
		healthSystem.attachComponent(this);
		
		myCurrHealth = DEFAULT_HEALTH;
		myMaxHealth = DEFAULT_HEALTH;
	}
		
	public int getCurrentHealth() {
		return myCurrHealth.intValue();
	}
	
	public double takeDamage(Damage dmg) {
		double startingHealth = myCurrHealth;
		myCurrHealth -= dmg.getDamage();
		
		if (myCurrHealth < 0) {
			myCurrHealth = 0.0;
		}
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
		
		//TODO: Manage death, perhaps with a strategy

		return myCurrHealth - startingHealth;		
	}
	
	public void setMaxHealth(double m) {
		myMaxHealth = m;
		
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
	}

	/**********************IComponent Interface********/
	@Override
	public IEntity getEntity() {
		return myEntity;
	}

}
