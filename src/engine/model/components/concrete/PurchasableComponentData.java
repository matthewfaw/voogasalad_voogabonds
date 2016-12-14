package engine.model.components.concrete;

import java.util.List;

import authoring.model.ComponentData;
import engine.model.components.AbstractComponent;
import engine.model.entities.IEntity;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;

public class PurchasableComponentData extends AbstractComponent {
	private int myPurchaseValue;
	
	public PurchasableComponentData(IEntity aEntity, ComponentData componentData, Router aRouter) {
		super(aEntity, aRouter);
		updateComponentData(componentData);
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
	@Override
	public void setSystems(List<ISystem<?>> aSystemList) {
		// do nothing
	}
	@Override
	public void redistributeThroughRouter(Router aRouter) {
		super.setRouter(aRouter);
	}
	@Override
	public void updateComponentData(ComponentData aComponentData) {
		myPurchaseValue = Integer.parseInt(aComponentData.getFields().get("myPurchaseValue"));
	}
}
