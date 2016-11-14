package usecases;

import authoring.authoring_interfaces.ITower;
import engine.model.playerinfo.IModifiablePlayerInfo;

public class MockTower implements ITower {
	
	private IModifiablePlayerInfo myModifiablePlayerInfo;
	private int upgradeCost;
	
	public boolean upgrade() {
		// update tower to new upgrade tower
		return false;
	}
	
	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSellPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRank() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getUpgradeCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	public IModifiablePlayerInfo getModifiablePlayerInfo() {
		return myModifiablePlayerInfo;
	}

}
