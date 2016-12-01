package engine.model.machine;

import authoring.model.EnemyData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.game_environment.MapMediator;
import engine.model.machine.enemy.Enemy;
import engine.model.machine.tower.Tower;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.resourcestore.ResourceStore;
import engine.model.weapons.WeaponFactory;
import utility.Point;
import utility.ResouceAccess;

public class MachineFactory {
	private static final String INVALID_TOWER_ERROR = "NoSuchTower";
	private static final String INVALID_ENEMY_ERROR = "NoSuchTower";
	
	private TimelineController myTime;
	private WeaponFactory myArmory;
	private DataStore<TowerData> myTowers;
	private DataStore<EnemyData> myEnemies;
	
	public MachineFactory(
			TimelineController time,
			ResourceStore aResourceStore,
			DataStore<EnemyData> enemies,
			DataStore<WeaponData> weapons,
			DataStore<ProjectileData> projectiles,
			MapMediator map) {
		myTime = time;
		myTowers = new DataStore<TowerData>(aResourceStore.getAvailableTowers());
		myEnemies = enemies;
		myArmory = new WeaponFactory(weapons, projectiles, time, map);
	}
	
	public Tower newTower(String name, IModifiablePlayer owner, Point intitialPosition) {
		if (myTowers.hasKey(name)) {
			Tower tower = new Tower(myArmory, owner, myTowers.getData(name), intitialPosition);
			myTime.attach(tower);
			return tower;
		}
		else
			throw new IllegalArgumentException(ResouceAccess.getError(INVALID_TOWER_ERROR) + name);
	}
	
	public Enemy newEnemy(String name, IModifiablePlayer owner, Point intitialPosition) {
		if (myEnemies.hasKey(name)) {
			Enemy enemy = new Enemy(myArmory, owner, myEnemies.getData(name), intitialPosition);
			myTime.attach(enemy);
			return enemy;
		}
		else
			throw new IllegalArgumentException(ResouceAccess.getError(INVALID_ENEMY_ERROR) + name);
	}

}
