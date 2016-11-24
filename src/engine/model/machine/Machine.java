package engine.model.machine;

import engine.model.components.IComponent;
import engine.model.entities.IEntity;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.weapons.DamageInfo;
import utility.Damage;
import utility.Point;

public abstract class Machine implements IViewableMachine, IEntity {

	private IModifiablePlayer myModifiablePlayer;
	private IHealth health;
	
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
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IHealth getHealth() {
		return health;
	}
	
	public double getDistanceTo(Point p) {
		return getLocation().euclideanDistance(p); //Minus collision Radius
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
