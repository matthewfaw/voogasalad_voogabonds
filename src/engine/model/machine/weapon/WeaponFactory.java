package engine.model.machine.weapon;

import java.util.Map;

import authoring.model.ProjectileData;
import authoring.model.WeaponData;
import engine.IViewable;
import engine.Observer;
import engine.model.machine.weapon.projectile.ProjectileFactory;


/**
 * A class to crate new weapons without having to pass the same arguments many times.
 * @author Weston
 *
 */
public class WeaponFactory {
	ProjectileFactory myProjectileFactory;
	
	public WeaponFactory(
			Observer<IViewable> observer,
			Map<String, ProjectileData> projMap) {
		
		//matthewfaw: commented the  following line so that project compiles
		//myProjectileFactory = new ProjectileFactory(observer, projMap);
		
	}

	public Weapon newWeapon(WeaponData data, IKillerOwner owner) {
		return new Weapon(data, owner, myProjectileFactory);
	}
}