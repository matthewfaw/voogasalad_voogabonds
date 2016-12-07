package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.systems.RewardSystem;

/**
 * The purpose of this class is to manage the information
 * relevant to the reward associated with some action on an entity
 * For example, when this entity is killed, the bounty could represent
 * how much reward the other entity should receive 
 * 
 * @author matthewfaw
 *
 */
public class BountyComponent implements IComponent {
	private IEntity myEntity;
	private int myBountyValue;
	private RewardSystem myRewardSystem;
	
	public BountyComponent (RewardSystem rewardSystem) {
		myRewardSystem = rewardSystem;
		myRewardSystem.attachComponent(this);
	}
	/**
	 * A method to retrieve the bounty value associated with an entity
	 * 
	 * @return the bounty value
	 */
	public int getBounty()
	{
		return myBountyValue;
	}

	@Override
	public IEntity getEntity()
	{
		return myEntity;
	}
}
