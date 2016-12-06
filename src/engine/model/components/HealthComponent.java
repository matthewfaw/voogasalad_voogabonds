package engine.model.components;

import engine.model.entities.IEntity;
import utility.Damage;

public class HealthComponent implements IComponent {
	private int myCurrentHealth;
	
	public int getCurrentHealth()
	{
		return myCurrentHealth;
	}
	public void takeDamage(Damage aDamage)
	{
		myCurrentHealth -= aDamage.getDamage();
		//TODO: Manage death, perhaps with a strategy
	}

	/**********************IComponent Interface********/
	@Override
	public IEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
