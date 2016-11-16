package engine.model.machine.weapon;

import java.util.List;
import java.util.Map;

import engine.model.machine.Machine;
import engine.model.machine.weapon.projectile.IProjectile;
import engine.model.machine.weapon.projectile.Projectile;
import engine.model.strategies.ITargetStrategy;
import utility.Point;

public class Weapon implements IWeapon {
	ProjectileData myProjectileData;
	ITargetStrategy myTargetStrategy;
	int myFireRate;
	int myTimeToFire;
	double myRange;
	
	public Weapon(WeaponData data, Map<String, ProjectileData> projectiles) {
		
	}

	@Override
	public void fire(List<Machine> targets, double heading, Point position) {
		if (myTimeToFire <= 0 && targets.size() > 0){
			
			Machine target = myTargetStrategy.target(targets, heading, position);
			
			new Projectile(myProjectileData, target, heading, position);
			
			myTimeToFire = myFireRate;
		} else {
			myTimeToFire--;
		}
		
	}
	
	

}
