package engine.model.components.concrete;

import authoring.model.ComponentData;
import engine.model.components.AbstractComponent;
import gamePlayerView.gamePlayerView.Router;

public class PurchasableComponentData extends AbstractComponent {
	private int myPurchaseValue;
	
	public PurchasableComponentData(ComponentData componentData, Router aRouter) {
		super(aRouter);
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

	@Override
	public void distributeInfo() {
		// do nothing
	}

	@Override
	public void delete() {
		// do nothing
	}
}
