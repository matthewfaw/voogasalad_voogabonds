package engine.model.machine.enemy;

import authoring.model.EnemyData;
import engine.controller.timeline.TimelineController;
import engine.model.machine.Machine;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.weapons.WeaponFactory;
import utility.Point;

@Deprecated
public class Enemy /*extends Machine*/ {
	/*
	private int myBounty;
	
	public Enemy(TimelineController time, WeaponFactory armory, IModifiablePlayer owner, EnemyData data,
			Point initialPosition) {
		super(time, armory, owner, data, initialPosition);
		myBounty = data.getKillReward();
	}
	
	@Override
	protected int die() {
		//TODO: Delete self
		unregisterMyself();
		return myBounty;
	}
	@Override
	public Point getGoal() {
		//This is intended. (Contrast with projectile's getGoal() method.)
		return null;
	}
	*/
}