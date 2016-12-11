package authoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import engine.model.components.PurchasableComponentData;
import engine.model.components.SellableComponentData;
import engine.model.components.UpgradableComponentData;

/**
 * @author owenchung and alanguo
 */

public class EntityData implements IReadableData {
	private String myName;
	private Map<String, ComponentData> myComponents;
//	Optional<UpgradableComponentData> myUpgradeData;
//	Optional<SellableComponentData> mySellData;
//	Optional<PurchasableComponentData> myPurchaseData;
	private UpgradableComponentData myUpgradeData;
	private SellableComponentData mySellData;
	private PurchasableComponentData myPurchaseData;
	
	public EntityData()
	{
		myComponents = new HashMap<String, ComponentData>();
	}
	
	public String getName(){
		return myName;
	}
	
	public void setName(String s){
		this.myName = s;
	}
	
	public void addComponent(String aName, ComponentData comp){
		myComponents.put(aName, comp);
	}
	
	public Map<String, ComponentData> getComponents(){
		return myComponents;
	}

	public int getBuyPrice() {
		return myPurchaseData!=null ? myPurchaseData.getBuyPrice() : Integer.MAX_VALUE;
//		return myPurchaseData.isPresent() ? myPurchaseData.get().getBuyPrice() : Integer.MAX_VALUE;
	}

	public int getSellPrice() {
		return mySellData!=null ? mySellData.getSellValue() : 0;
	}

	public Map<String, Integer> getUpgrades() {
		return myUpgradeData!=null ? myUpgradeData.getUpgrades() : new HashMap<String, Integer>();
	}

}
