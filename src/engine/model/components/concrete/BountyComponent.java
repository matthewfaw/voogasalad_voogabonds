package engine.model.components.concrete;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.systems.BountySystem;

/**
 * The purpose of this class is to manage the information
 * relevant to the reward associated with some action on an entity
 * For example, when this entity is killed, the bounty could represent
 * how much reward the other entity should receive 
 * 
 * @author matthewfaw
 *
 */
public class BountyComponent extends AbstractComponent {
	private int myBountyValue;
	
	@Hide
	private BountySystem myBounty;
	
	public BountyComponent (BountySystem bountySystem, ComponentData data) {
		myBountyValue = Integer.parseInt(data.getFields().get("myBountyValue"));
		
		bountySystem.attachComponent(this);
	}
	/**
	 * A method to retrieve the bounty value associated with an entity
	 * 
	 * @return the bounty value
	 */
	public int getBounty() {
		return myBountyValue;
	}
	
	@Override
	public void delete() {
		myBounty.detachComponent(this);
	}
	
	

}
