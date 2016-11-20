package engine.model.playerinfo;

public interface IModifiablePlayer {
	public void updateLivesRemaining(int deltaLives);
	public void updateAvailableFunds(int deltaFunds);
}
