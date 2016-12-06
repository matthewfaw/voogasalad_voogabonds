package engine.model.components;

import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;

public class CreatorComponent implements IComponent {
	private IEntity myEntity;
	
	//TODO:
//	private ISpawningStrategy myEnemySpawningStrategy;
//	private ITargetingStrategy myTargetingStrategy;
	private EntityFactory myEntityFactory;
	
	//TODO:
	public void spawnEntity()
	{
		//myEnemySpawningStrategy.getEntityToSpawn();
		//Create it
		//set it's target
	}

	@Override
	public IEntity getEntity() {
		return myEntity;
	}
}
