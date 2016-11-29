package engine.model.machine.enemy;
import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;
public class Enemy extends Machine {
	private IMoney myBounty;
	
	public Enemy(int initialHealth) {
	}
	@Override
	protected int die() {
		//TODO: Delete self
		return myBounty.getValue();
	}
}