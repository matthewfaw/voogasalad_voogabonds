package engine.model.strategies.winlose;

import engine.model.playerinfo.IModifiablePlayer;
import engine.model.strategies.AbstractLoseStrategy;

public class PlayerHpLoseStrategy extends AbstractLoseStrategy<IModifiablePlayer> {
	
	public PlayerHpLoseStrategy(IModifiablePlayer player) {
		super(player);
	}

	@Override
	protected boolean checkCondition(IModifiablePlayer observed) {
		return observed.getLivesRemaining() <= 0;
	}

}
