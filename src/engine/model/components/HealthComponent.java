package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.machine.Health;
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
	private Health myHealth;
	private HealthSystem myHealthSystem;
	
	public HealthComponent(HealthSystem healthSystem, double maxHealth) {
		myHealthSystem = healthSystem;
		myHealthSystem.attachComponent(this);
	}
	
	public int getCurrentHealth()
	{
		return (int) myHealth.getHealth();
	}
	public void takeDamage(Damage dmg)
	{
		myHealth.takeDamage(dmg);
		
		//TODO: Manage death, perhaps with a strategy
	}

	/**********************IComponent Interface********/
	@Override
	public IEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
