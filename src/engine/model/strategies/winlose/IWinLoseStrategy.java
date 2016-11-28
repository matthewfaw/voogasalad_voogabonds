package engine.model.strategies.winlose;

import engine.model.playerinfo.IModifiablePlayer;

public interface IWinLoseStrategy {
	
	abstract public void subscribe(IModifiablePlayer p);
	abstract public void unsubscribe(IModifiablePlayer p);

}
