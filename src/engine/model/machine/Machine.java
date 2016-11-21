package engine.model.machine;

import engine.model.machine.weapon.DamageInfo;
import engine.model.playerinfo.IModifiablePlayerInfo;
import utility.Damage;
import utility.Point;

public abstract class Machine implements IViewableMachine {

	private IModifiablePlayerInfo myModifiablePlayerInfo;
	
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
	
	public double getDistanceTo(Point p) {
		return getLocation().euclideanDistance(p); //Minus collision Radius
	}
	
	public IModifiablePlayerInfo getModifiablePlayerInfo() {
		return myModifiablePlayerInfo;
	}

	abstract public DamageInfo takeDamage(Damage toDeal);

}
