package engine.model.playerinfo;

import engine.model.resourcestore.ResourceStore;

public interface IModifiablePlayer {
	public void updateLivesRemaining(int deltaLives);
	public void updateAvailableMoney(int deltaFunds);
	
	public void win();
	public void lose();
	
	public void addResourceStore(ResourceStore aResourceStore);
	public void removeResourceStore(ResourceStore aResourceStore);
}
