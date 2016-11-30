package engine.model.weapons;

import java.util.Map;

import authoring.model.ProjectileData;
import authoring.model.WeaponData;
import engine.controller.timeline.TimelineController;
import engine.model.game_environment.MapMediator;
import engine.model.projectiles.ProjectileFactory;

/**
 * A class to crate new weapons without having to pass the same arguments many times.
 * @author Weston
 *
 */
public class WeaponFactory {
	private ProjectileFactory myProjectileFactory;
	private MapMediator myMap;
	
	public WeaponFactory(Map<String, ProjectileData> projMap, TimelineController time, MapMediator map) {
		myProjectileFactory = new ProjectileFactory(projMap, time, myMap);
	}

	public Weapon newWeapon(WeaponData data, IKillerOwner owner) {
		return new Weapon(data, owner, myProjectileFactory, myMap);
	}
}
