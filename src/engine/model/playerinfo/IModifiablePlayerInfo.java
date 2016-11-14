package engine.model.playerinfo;

public interface IModifiablePlayerInfo {
	public void updateLivesRemaining(int deltaLives);
	public void updateAvailableFunds(double deltaFunds);
}
