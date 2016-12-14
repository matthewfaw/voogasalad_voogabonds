package engine.model.components.concrete;

import java.util.List;

import authoring.model.ComponentData;
import engine.model.components.AbstractComponent;
import engine.model.entities.IEntity;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;

/**
 * The purpose of this class is to manage the information relevant
 * to selling an object, such as the value a player would obtain
 * when selling an entity
 * @author matthewfaw
 *
 */
public class SellableComponentData extends AbstractComponent {
	private int mySellValue;
	
	public SellableComponentData(IEntity aEntity, ComponentData componentData, Router aRouter) {
		super(aEntity, aRouter);
		updateComponentData(componentData);
	}
	public int getSellValue()
	{
		return mySellValue;
	}

	public void setSellPrice(int aPrice)
	{
		mySellValue = aPrice;
	}
	@Override
	public void distributeInfo() {
		// Do nothing
	}
	@Override
	public void delete() {
		// Do nothing
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
		mySellValue = Integer.parseInt(aComponentData.getFields().get("mySellValue"));
	}
}
