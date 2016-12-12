package engine.model.components.concrete;

import authoring.model.ComponentData;

public class PurchasableComponentData {
	private int myPurchaseValue;
	
	public PurchasableComponentData(ComponentData componentData) {
		myPurchaseValue = Integer.parseInt(componentData.getFields().get("myPurchaseValue"));
	}
	
	public int getBuyPrice()
	{
		return myPurchaseValue;
	}
	
	public void setBuyPrice(int aPrice)
	{
		myPurchaseValue = aPrice;
	}
}
