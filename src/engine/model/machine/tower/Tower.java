package engine.model.machine.tower;

import engine.model.machine.Machine;

public class Tower extends Machine{
	
	private int upgradeCost;
	private int sellPrice;
	
	public int getUpgradeCost() {
		return upgradeCost;
	}
	
	public int getSellPrice() {
		return sellPrice;
	}
}
