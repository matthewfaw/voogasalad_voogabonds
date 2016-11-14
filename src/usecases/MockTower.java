package usecases;

import engine.model.machine.Machine;

public class MockTower extends Machine{
	private int upgradeCost;
	
	public int getUpgradeCost() {
		return upgradeCost;
	}
	
	public boolean upgrade() {
		// update tower to new upgrade tower
		return false;
	}
}
