package engine.model.machine.weapon.projectile;

import java.util.Map;

import authoring.model.ProjectileData;
import engine.IViewable;
import engine.Observer;
import engine.model.machine.Machine;
import utility.Point;

public class ProjectileFactory {
	Observer<IViewable> myViewObserver;
	ProjectileOwner myProjectileOwner;
	Map<String, ProjectileData> myProjectiles;
	
	public ProjectileFactory(
			Observer<IViewable> observer,
			ProjectileOwner owner,
			Map<String, ProjectileData> projMap) {
		
		myViewObserver = observer;
		myProjectileOwner = owner;
		myProjectiles = projMap;
		
	}

	public Projectile newProjectile(String name, Machine target, double heading, Point start) {
		if (myProjectiles.containsKey(name))
			return new Projectile(myProjectiles.get(name), target, myViewObserver, myProjectileOwner, heading, start);
		else
			throw new UnsupportedOperationException("No projectile called: " + name);
	}
}
