package engine.model.components;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.entities.IEntity;
import engine.model.systems.BountySystem;
import engine.model.systems.HealthSystem;
import engine.model.weapons.DamageInfo;
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
public class HealthComponent extends AbstractComponent {
	@Hide
	private static double DEFAULT_HEALTH = 0.0;
	@Hide
	private BountySystem myBounty;
	//private HealthSystem myHealthSystem;
	private Double myCurrHealth;
	private Double myMaxHealth;


	
	public HealthComponent(HealthSystem healthSystem, BountySystem bounty,  ComponentData componentdata) {
		myBounty = bounty;
		healthSystem.attachComponent(this);
		
		myCurrHealth = DEFAULT_HEALTH;
		myMaxHealth = DEFAULT_HEALTH;
	}
		
	public int getCurrentHealth() {
		return myCurrHealth.intValue();
	}
	
	public DamageInfo takeDamage(Damage dmg) {
		double startingHealth = myCurrHealth;
		myCurrHealth -= dmg.getDamage();
		
		if (myCurrHealth < 0) {
			myCurrHealth = 0.0;
		}
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
		
		double healthDelta = myCurrHealth - startingHealth;
		int died = myCurrHealth <= 0 ? 1 : 0;
		int bounty = myBounty.getBounty(this);
		
		return new DamageInfo(healthDelta, died, bounty);		
	}
	
	public void setMaxHealth(double m) {
		myMaxHealth = m;
		
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
	}
}
