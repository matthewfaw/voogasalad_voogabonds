package engine.model.components;

import engine.model.entities.IEntity;

/**
 * The purpose of this class is to manage the information relevant
 * to selling an object, such as the value a player would obtain
 * when selling an entity
 * @author matthewfaw
 *
 */
public class SellableComponent implements IComponent {
	private IEntity myEntity;
	private int mySellValue;
	
	public int getSellValue()
	{
		return mySellValue;
	}

	@Override
	public IEntity getEntity() {
		return myEntity;
	}
}
