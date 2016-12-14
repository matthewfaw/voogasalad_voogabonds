package engine.model.systems;

import engine.controller.PlayerController;
import engine.model.components.IComponent;
import engine.model.components.concrete.BountyComponent;
import engine.model.playerinfo.IModifiablePlayer;

public class BountySystem extends AbstractSystem<BountyComponent> {
	private IModifiablePlayer myPlayer;

	public BountySystem(PlayerController player) {
		myPlayer = player.getPlayer(0);
	}
	
	public int pillagePlayerBase(IComponent c) {
		BountyComponent b = getComponent(c);
		if (b == null)
			return 0;
		myPlayer.updateLivesRemaining(b.getLivesTaken());
		return b.getLivesTaken();
	}
	
	public int collectBounty(IComponent c) {
		BountyComponent b = getComponent(c);
		if (b == null)
			return 0;
		myPlayer.updateAvailableMoney(b.getBounty());
		myPlayer.updatePoints(b.getPoints());
		return b.getPoints();
		
	}
}
