package engine.model.strategies.winlose;

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
		if (checkCondition(observed))
			for (IModifiablePlayer p: myPlayers)
				p.lose();
	}

	abstract protected boolean checkCondition(A observed);
}
