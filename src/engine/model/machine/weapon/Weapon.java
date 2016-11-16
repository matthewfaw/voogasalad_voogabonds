package engine.model.machine.weapon;

import java.util.List;

import authoring.model.ProjectileData;
import authoring.model.WeaponData;
import engine.model.machine.Machine;
import engine.model.machine.weapon.projectile.Projectile;
import engine.model.machine.weapon.projectile.ProjectileFactory;
import engine.model.strategies.ITargetStrategy;
import utility.Point;

public class Weapon implements IWeapon {
	ProjectileFactory myProjectileFactory;
	ProjectileData myProjectileData;
	ITargetStrategy myTargetStrategy;
	int myFireRate;
	int myTimeToFire;
	double myRange;
	
	public Weapon(WeaponData data, ProjectileFactory projFactory) {
		myRange = data.getRange();
		myFireRate = data.getFireRate();
		myTimeToFire = 0;
		
		myProjectileFactory = projFactory;
		
		//TODO: Init strategies
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
