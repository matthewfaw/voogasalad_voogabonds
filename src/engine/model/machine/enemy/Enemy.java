package engine.model.machine.enemy;

import engine.model.machine.IHealth;
import engine.model.resourcestore.IMoney;

public class Enemy implements IEnemy{

	private IMoney killValue;
	
	@Override
	public IMoney getKillValue() {
		return killValue;
	}

}
