package engine.model.strategies;

import utility.Point;

public interface IDamageStrategy {

	Damage getAoEDamage(Point myLocation, double myHeading, Point location);

	Damage getTargetDamage();

}
