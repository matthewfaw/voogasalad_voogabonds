package authoring.model;

import java.util.HashMap;
import java.util.Map;

public class TowerData extends MachineData {

	/**
	 * Initial buying price for a tower. A positive number means this tower 
	 * can be bought without upgrading (essentially is a root). If buyPrice is -1 this 
	 *  means that this tower cannot be bought and must be upgraded to.
	 */
	private int myBuyPrice;
	
	/**
	 * Amount of money you get for selling.
	 */
	private int mySellPrice;
	
	/**
	 * Map of towers which this particular tower can upgrade from. The Integer is the cost of updating
	 * from the previous tower to this one.
	 */
	private Map<String, Integer> myUpgrades;
	
	public TowerData() {
		myUpgrades = new HashMap<String, Integer>();
	}
	
	public void setBuyPrice(int buyPrice) {
		myBuyPrice = buyPrice;
	}
	public int getBuyPrice(){
		return myBuyPrice;
	}
	
	public void setSellPrice(int sellPrice) {
		mySellPrice = sellPrice;
	}
	public int getSellPrice(){
		return mySellPrice;
	}
	
	public void addUpgrades(String name, Integer upgradeCost){
		//this.upgradeFrom = upgrades;
		myUpgrades.put(name, upgradeCost);
	}
	
	public Map<String, Integer> getUpgrades(){
		return myUpgrades;
	}
	
	/*
	 * This doesn't work at the moment, partially because uninitialized doubles/ints default to 0.
	 * 
	 * 
	public boolean allFieldsFilled() throws IllegalAccessException {
		boolean result = true;
		
		for (Field f: getClass().getDeclaredFields()) {
			if (f.get(this) == null) {
				result = false;
				break;
			} else {
				System.out.println("Here!");
			}
			System.out.println(f.get(this).toString());
		}
		
		return result;
		
	}
	*/
	
	
}
