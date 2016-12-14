package engine.model.strategies;

import engine.IObservable;
import engine.model.playerinfo.IModifiablePlayer;

abstract public class AbstractLoseStrategy<A> extends AbstractWinLoseStrategy<A> {
	
	public AbstractLoseStrategy(IModifiablePlayer player) {
		super(player);
	}
	
	public AbstractLoseStrategy(IModifiablePlayer player, IObservable<A> toObserve) {
		super(player, toObserve);
	}

	@Override
	public void update(A observed) {
		if (checkCondition(observed)) {
			for (int i = 0 ; i < myPlayers.size(); i++) {
				myPlayers.get(i).lose();
			}
		}
	}

	abstract protected boolean checkCondition(A observed);
}
