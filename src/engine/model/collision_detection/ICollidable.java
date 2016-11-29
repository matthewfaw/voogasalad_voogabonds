package engine.model.collision_detection;

import engine.model.weapons.DamageInfo;
import utility.Damage;
import utility.Point;

public interface ICollidable{
	public Point getLocation();
	public double getRadius();
	public void collideInto(ICollidable unmovedCollidable);
	abstract public DamageInfo takeDamage(Damage damageDealt);
}
