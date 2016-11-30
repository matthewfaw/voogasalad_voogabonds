package engine.controller;

import utility.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import authoring.model.EnemyData;
import authoring.model.PlayerData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import authoring.model.serialization.JSONDeserializer;
import authoring.model.serialization.JSONSerializer;
import engine.exceptions.SerializationException;
import engine.model.game_environment.terrain.TerrainMap;

/**
 * A class to construct some mock game data for testing
 * This class contains examples of how to serialize and deserialize objects using Gson
 * @author matthewfaw
 *
 */
class MockGameDataConstructor {
	MockGameDataConstructor()
	{
	}
	
	void constructMockData() throws SerializationException
	{
		JSONDeserializer derp = new JSONDeserializer(); 
		
//		TowerData data = (TowerData)derp.DeserializeFromFile("towers/ActualTower", TowerData.class);
//		System.out.println(data.getBuyPrice());

		JSONSerializer ser = new JSONSerializer();
		
		try {
			
			// Terrain Data 
			TerrainData terrain1 = new TerrainData("grass", 0, 0);
			TerrainData terrain2 = new TerrainData("water", 0, 1);
			// Tower Data
			TowerData tow = new TowerData();
			tow.setWeaponName("weapon 1");
			tow.setBuyPrice(40);
			tow.setCollisionRadius(2);
			tow.setImagePath("src/resources/boss.png");
			List<Point> list = new ArrayList<Point>();
			list.add(new Point(100, 100));
			list.add(new Point(20, 50));
			tow.setInitialLocations(list);
			tow.setMaxHealth(1000);
			tow.setMovementStrategy("fast as fuck");
			tow.setName("tower 1");
			tow.setSellPrice(100);
			
			// Enemy Data
			EnemyData ed = new EnemyData();
			ed.setCollisionRadius(5);
			ed.setImagePath("src/resources/boss.png");
			ed.setKillReward(100);
			ed.setMaxHealth(100);
			ed.setName("enemy 1");
			ed.setSpeed(10);
			ed.setWeaponName("weapon 1");
			
			// Weapon Data
			WeaponData wd = new WeaponData();
			wd.setFireRate(10);
			wd.setName("weapon 1");
			wd.setProjectileName("projectile 1");
			wd.setRange(100);
			
			// Projectile Data
			ProjectileData pd = new ProjectileData();
			pd.setAreaOfEffectRadius(10);
			pd.setDamageStrategy("damage");
			pd.setImagePath("src/resources/boss.png");
			pd.setMaxRange(10);
			pd.setMovementStrategy("fast as fuck");
			pd.setName("projectile 1");
			
			// Player Data
			PlayerData pdd = new PlayerData();
			pdd.setLoseStrategyName("lose strategy 1");
			pdd.setStartingCash(2000);
			pdd.setWinStrategyName("win strategy 1");
			pdd.setStartingLives(5);
			
			// Map Data
			MapData md = new MapData();
			md.setNumXCells(2);
			md.setNumYCells(2);
			md.addTerrainData(terrain1);
			md.addTerrainData(terrain2);
			ArrayList<Point> spawnPoints = new ArrayList<Point>();
			spawnPoints.add(new Point(1, 1));
			md.addSpawnPoints("spawnPoint", spawnPoints);
			md.addSinkPoint(new Point(1, 0));
			md.addValidTerrain(terrain1.getName(), "exampleColor 1");
			md.addValidTerrain(terrain2.getName(), "exampleColor 2");
			
			ser.serializeToFile(md, "map/"+md.getClass().getSimpleName());
			ser.serializeToFile(terrain1,"terrain/"+terrain1.getClass().getSimpleName()+"1");
			ser.serializeToFile(terrain2, "terrain/"+terrain2.getClass().getSimpleName()+"2");
			ser.serializeToFile(tow, "towers/"+tow.getClass().getSimpleName());
			ser.serializeToFile(ed, "enemies/"+ed.getClass().getSimpleName());
			ser.serializeToFile(wd, "weapon/"+wd.getClass().getSimpleName());
			ser.serializeToFile(pd, "projectiles/"+pd.getClass().getSimpleName());
			ser.serializeToFile(pdd, "players/"+pdd.getClass().getSimpleName());
			
			
			TerrainMap terrainMap = new TerrainMap(md);
			terrainMap.getDestination();
		} catch (Exception e) {
			//TODO add more meaningful error message
			throw new SerializationException("Could not serialize");
		}
	}
	public static void main(String[] args)
	{
		JSONSerializer ser = new JSONSerializer();
	}
}
