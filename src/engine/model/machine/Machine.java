package engine.model.machine;

import engine.IViewable;
import engine.model.components.IComponent;
import engine.model.entities.IEntity;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.machine.weapon.DamageInfo;
import utility.Damage;
import utility.Point;

public abstract class Machine implements IViewable, IViewableMachine, IModifiableMachine, IEntity {

	private IModifiablePlayer myModifiablePlayer;
	private IHealth health;
	
	public Machine (int initialHealth) {
		health = new Health(initialHealth);
	}
	
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHeading() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IHealth getHealth() {
		return health;
	}
	
	@Override
	public void modifyHealth(IHealth deltaHealth) {
		health.updateHealth(deltaHealth);
	}
	
	public double getDistanceTo(Point p) {
		return getPosition().euclideanDistance(p); //Minus collision Radius
	}
	
	public IModifiablePlayer getModifiablePlayerInfo() {
		return myModifiablePlayer;
	}

	abstract public DamageInfo takeDamage(Damage toDeal);

	@Deprecated
	public int getId()
	{
		return 0;
	}

	@Deprecated
	public void addComponent(IComponent aComponent)
	{
		
	}
}
