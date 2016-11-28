package engine.model.playerinfo;

import engine.model.resourcestore.IMoney;

public interface IViewablePlayer {
	public int getLivesRemaining();
	public IMoney getAvailableMoney();
	public int getID();
}
