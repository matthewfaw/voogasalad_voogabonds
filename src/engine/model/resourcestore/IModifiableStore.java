package engine.model.resourcestore;

import engine.model.machine.tower.Tower;

public interface IModifiableStore {
	public void updatePlayerMoney(int deltaMoney); 
	public void addAvailableTowers(Tower toAdd);
	public void removeAvailableTowers(Tower toRemove);
}
