package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.systems.HealthSystem;
import utility.Damage;

/**
 * The purpose of this class is to manage the health information
 * relevant to an entity
 * manages how an entity should take damage
 * @author matthewfaw
 *
 */
public class HealthComponent implements IComponent {
	private int myCurrentHealth;
	private HealthSystem myHealthSystem;
	
	public HealthComponent(HealthSystem healthSystem) {
		myHealthSystem = healthSystem;
		myHealthSystem.attachComponent(this);
	}
	
	public int getCurrentHealth()
	{
		return myCurrentHealth;
	}
	public void takeDamage(int damage)
	{
		myCurrentHealth -= damage;
		//TODO: Manage death, perhaps with a strategy
	}

	/**********************IComponent Interface********/
	@Override
	public IEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
