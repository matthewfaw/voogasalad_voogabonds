package engine.model.components.concrete;

import authoring.model.ComponentData;
import engine.model.entities.IEntity;

/**
 * The purpose of this class is to manage the information relevant
 * to selling an object, such as the value a player would obtain
 * when selling an entity
 * @author matthewfaw
 *
 */
public class SellableComponentData {
	private int mySellValue;
	
	public SellableComponentData(/*ComponentData componentData*/) {
//		mySellValue = Integer.parseInt(componentData.getFields().get("mySellValue"));
	}
	public int getSellValue()
	{
		return mySellValue;
	}

	public void setSellPrice(int aPrice)
	{
		mySellValue = aPrice;
	}
}
