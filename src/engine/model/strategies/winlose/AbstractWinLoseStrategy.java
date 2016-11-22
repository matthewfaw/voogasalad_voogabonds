package engine.model.strategies.winlose;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import engine.model.playerinfo.IModifiablePlayer;

abstract public class AbstractWinLoseStrategy implements Observer, IWinLoseStrategy {
	List<IModifiablePlayer> myPlayers;
	
	public AbstractWinLoseStrategy(IModifiablePlayer p){
		myPlayers = new ArrayList<IModifiablePlayer>();
		subscribe(p);
	}

	@Override
	public void subscribe(IModifiablePlayer p) {
		myPlayers.add(p);
	}

	@Override
	public void unsubscribe(IModifiablePlayer p) {
		myPlayers.remove(p);
		
	}


}
