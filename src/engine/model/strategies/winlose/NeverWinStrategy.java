package engine.model.strategies.winlose;

import engine.model.playerinfo.IModifiablePlayer;

public class NeverWinStrategy extends AbstractWinStrategy<Object> {

	public NeverWinStrategy(IModifiablePlayer player) {
		super(player);
	}

	@Override
	protected boolean checkCondition(Object o) {
		//Should never get called because this strat isn't observing anything.
		//Therefore, nothing should call its update method.
		return false;
	}
	
}