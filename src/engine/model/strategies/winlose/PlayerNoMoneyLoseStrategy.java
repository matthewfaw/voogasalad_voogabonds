package engine.model.strategies.winlose;

import engine.model.playerinfo.IModifiablePlayer;

public class PlayerNoMoneyLoseStrategy extends AbstractLoseStrategy<IModifiablePlayer> {
	public PlayerNoMoneyLoseStrategy(IModifiablePlayer player) {
		super(player);
	}

	@Override
	protected boolean checkCondition(IModifiablePlayer observed) {
		return observed.getAvailableMoney() <= 0;
	}

}
