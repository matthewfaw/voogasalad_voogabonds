package gameengine.playerinfo;

public interface IModifiablePlayerInfo {
	public void updateLivesRemaining(int deltaLives);
	public void updateAvailableFunds(int deltaFunds);

}
