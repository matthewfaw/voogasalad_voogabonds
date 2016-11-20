package engine.model.machine.weapon;

import utility.Point;

public interface IKillerOwner {
	
	abstract public DamageInfo notifyDestroy(DamageInfo result);
	abstract public double getHeading();
	abstract public Point getLocation();
}
