package engine.model.systems;

import java.util.List;

import engine.model.components.BountyComponent;
import engine.model.components.CollidableComponent;
import engine.model.components.IComponent;
import engine.model.entities.IEntity;
import engine.model.playerinfo.IModifiablePlayer;

public class RewardSystem implements ISystem{
	private List<BountyComponent> myBountyComponents;
	
	/**
	 * Given the input entity, returns the corresponding bounty component.
	 * 
	 * @param query: entity being queried
	 * @return bounty component that the input entity owns
	 */
	private BountyComponent findBountyComponent(IEntity query) {
		BountyComponent found = null;
		for (BountyComponent cc: myBountyComponents) {
			if (cc.getEntity() == query) {
				found = cc;
			}
		}
		return found;
	}
	
	/**
	 * Given a component, returns bounty that it gives when killed.
	 * @param component
	 * @return bounty on kill; 0 if not a bounty component
	 */
	private int getBountyFromComponent(IComponent component) {
		BountyComponent bountyComponent = findBountyComponent(component.getEntity());
		return bountyComponent == null ? 0 : bountyComponent.getBounty();
	}
	
	/**
	 * Updates player available money by bounty received from component.
	 * @param player
	 * @param component
	 */
	private void collectBounty(IModifiablePlayer player, IComponent component) {
		player.updateAvailableMoney(getBountyFromComponent(component));
	}
}
