package engine.model.machine.tower;

import authoring.model.TowerData;
import engine.model.machine.Machine;
import utility.Point;

public class Tower extends Machine implements IUpgradable {
	
	public Tower(){
	}
	public Tower(String name, int maxHealth) {
		super(name, maxHealth);
	}

	@Override
	protected int die() {
		//TODO: Delete self
		return 0;
	}
	@Override
	public void upgrade(TowerData upgradeData) {
		super.upgrade(upgradeData);
	}
	@Override
	public Point getGoal() {
		// TODO Auto-generated method stub
		return null;
	}

	
}