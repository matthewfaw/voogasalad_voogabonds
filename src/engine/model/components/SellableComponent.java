package engine.model.components;

import engine.model.entities.IEntity;

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
