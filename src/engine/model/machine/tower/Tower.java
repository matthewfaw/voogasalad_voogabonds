package engine.model.machine.tower;

import authoring.model.TowerData;
import engine.controller.timeline.TimelineController;
import engine.model.machine.Machine;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.weapons.WeaponFactory;
import utility.Point;

public class Tower extends Machine implements IUpgradable {
	

	public Tower(TimelineController time, WeaponFactory armory, IModifiablePlayer owner, TowerData data,
			Point initialPosition) {
		super(time, armory, owner, data, initialPosition);
	}
	
	@Override
	protected int die() {
		//TODO: Delete self
		unregisterMyself();
		return 0;
	}
	
	@Override
	public void upgrade(TowerData upgradeData) {
		super.upgrade(upgradeData);
	}
	
	@Override
	public Point getGoal() {
		//This is intended. (Contrast with projectile's getGoal() method.)
		return null;
	}

}