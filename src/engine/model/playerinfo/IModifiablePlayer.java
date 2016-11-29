package engine.model.playerinfo;

public interface IModifiablePlayer {
	public void updateLivesRemaining(int deltaLives);
	public void updateAvailableMoney(int deltaFunds);
	
	public void win();
	public void lose();
}
