package engine.model.resourcestore;

import engine.model.machine.tower.Tower;

public interface IModifiableStore {
	public void updatePlayerMoney(int deltaMoney); 
	public void addBaseTowers(Tower toAdd);
	public void removeBaseTowers(Tower toRemove);
}
