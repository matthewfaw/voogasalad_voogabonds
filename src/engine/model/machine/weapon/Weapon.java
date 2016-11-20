package engine.model.machine.weapon;

import java.util.List;

import authoring.model.WeaponData;
import engine.model.machine.Machine;
import engine.model.machine.weapon.projectile.ProjectileFactory;
import engine.model.strategies.ITargetStrategy;
import engine.model.strategies.StrategyFactory;
import utility.Point;

public class Weapon implements IWeapon, IKillerOwner {
	IKillerOwner myMachine;
	ProjectileFactory myProjectileFactory;
	ITargetStrategy myTargetStrategy;
	String myProjectile;
	int myFireRate;
	int myTimeToFire;
	double myRange;
	
	double myCareerKills;
	double myCareerDamage;
	double myCareerEarnings;
	
	public Weapon(WeaponData data, IKillerOwner owner, ProjectileFactory projFactory) {
		myRange = data.getRange();
		myFireRate = data.getFireRate();
		myProjectile = data.getProjectileName();
		
		myMachine = owner;
		myProjectileFactory = projFactory;
		
		//TODO: Get strategy name from data
		myTargetStrategy = StrategyFactory.targetStrategy("");
		
		myCareerKills = 0;
		myCareerDamage = 0;
		myCareerEarnings = 0;
		myTimeToFire = 0;
	}

	@Override
	public void fire(List<Machine> targets, double heading, Point position) {
		if (myTimeToFire <= 0 && targets.size() > 0){
			
			Machine target = myTargetStrategy.target(targets, heading, position);
			
			myProjectileFactory.newProjectile(myProjectile, target, this);
			
			myTimeToFire = myFireRate;
		} else {
			myTimeToFire--;
		}
		
	}

	@Override
	public DamageInfo notifyDestroy(DamageInfo dmg) {
		myCareerKills += dmg.getKills();
		myCareerDamage += dmg.getDamage();
		myCareerEarnings += dmg.getMoney();
		
		return myMachine.notifyDestroy(dmg);
	}

	@Override
	public double getHeading() {
		return myMachine.getHeading();
	}

	@Override
	public Point getLocation() {
		return myMachine.getLocation();
	}

	@Override
	public double getKills() {
		return myCareerKills;
	}

	@Override
	public double getEarnings() {
		return myCareerEarnings;
	}

	@Override
	public double getDamage() {
		return myCareerDamage;
	}
	
	

}
