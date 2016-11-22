package engine.model.strategies.winlose;

import java.util.Observable;

import engine.model.playerinfo.IModifiablePlayer;

public class NeverWinStrategy extends AbstractWinStrategy {

	public NeverWinStrategy(IModifiablePlayer player) {
		super(player);
	}

	@Override
	protected boolean checkCondition(Observable o, Object arg) {
		return false;
	}
	
}
