package authoring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import engine.model.components.PurchasableComponentData;
import engine.model.components.SellableComponentData;
import engine.model.components.UpgradableComponentData;

/**
 * @author owenchung and alanguo
 */

public class EntityData implements IReadableData {
	private String myName;
	List<ComponentData> myComponents;
	Optional<UpgradableComponentData> myUpgradeData;
	Optional<SellableComponentData> mySellData;
	Optional<PurchasableComponentData> myPurchaseData;
	
	public EntityData()
	{
		myComponents = new ArrayList<ComponentData>();
	}
	
	public String getName(){
		return myName;
	}
	
	public void setName(String s){
		this.myName = s;
	}
	
	public void addComponent(ComponentData comp){
		myComponents.add(comp);
	}
	
	public List<ComponentData> getComponents(){
		return myComponents;
	}
}
