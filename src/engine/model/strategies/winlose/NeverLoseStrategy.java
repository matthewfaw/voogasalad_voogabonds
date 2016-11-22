package engine.model.strategies.winlose;

import java.util.Observable;

import engine.model.playerinfo.IModifiablePlayer;

public class NeverLoseStrategy extends AbstractLoseStrategy {
	
	public NeverLoseStrategy(IModifiablePlayer player) {
		super(player);
	}

	@Override
	protected boolean checkCondition(Observable o, Object arg) {
		return false;
	}
	
}
