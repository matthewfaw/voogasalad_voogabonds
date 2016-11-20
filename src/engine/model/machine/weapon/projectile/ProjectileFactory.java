package engine.model.machine.weapon.projectile;

import java.util.Map;

import authoring.model.ProjectileData;
import engine.IViewable;
import engine.Observer;
import engine.model.machine.Machine;
import engine.model.machine.weapon.IKillerOwner;

/**
 * A class to make projectiles and store everything that all the projectiles will have in common, so the common
 * data won't need to be passed around. 
 * @author Weston
 *
 */
public class ProjectileFactory {
	Observer<IViewable> myViewObserver;
	Map<String, ProjectileData> myProjectiles;
	
	public ProjectileFactory(
			Observer<IViewable> observer,
			Map<String, ProjectileData> projMap) {
		
		myViewObserver = observer;
		myProjectiles = projMap;
		
	}

	/**
	 * Makes a projectile from the data for name, targeting target owned by owner.
	 * @param name
	 * @param target
	 * @param owner
	 * @return new Projectile
	 */
	public Projectile newProjectile(String name, Machine target, IKillerOwner owner) {
		if (myProjectiles.containsKey(name))
			return new Projectile(myProjectiles.get(name), target, owner, myViewObserver);
		else
			//TODO: ResourceFile error message
			throw new UnsupportedOperationException("No projectile called: " + name);
	}
}
