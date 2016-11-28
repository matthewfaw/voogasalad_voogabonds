package engine.model.strategies.winlose;

import java.util.Observable;

import engine.model.playerinfo.IModifiablePlayer;

abstract public class AbstractLoseStrategy extends AbstractWinLoseStrategy {
	IModifiablePlayer myPlayer;
	
	public AbstractLoseStrategy(IModifiablePlayer player) {
		super(player);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (checkCondition(o, arg))
			myPlayer.lose();
	}
	

	abstract protected boolean checkCondition(Observable o, Object arg);
}
