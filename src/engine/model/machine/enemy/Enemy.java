package engine.model.machine.enemy;

import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;
import utility.Point;

public class Enemy extends Machine {
	private IMoney myBounty;
	
	public Enemy(int initialHealth) {
	}
	@Override
	protected int die() {
		//TODO: Delete self
		return myBounty.getValue();
	}
	@Override
	public Point getGoal() {
		// TODO Auto-generated method stub
		return null;
	}
}