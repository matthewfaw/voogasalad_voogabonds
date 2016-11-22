package engine.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.model.EnemyData;
import authoring.model.PlayerData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import authoring.model.serialization.DeserializeJSON;
import authoring.model.serialization.SerializeJSON;
import engine.model.game_environment.terrain.TerrainMap;

/**
 * A main controller whose primary purpose is to
 * create the frontend and backend objects and to manage 
 * communication from frontend to backend
 * 
 * Communication from backend to frontend will be established
 * by the Router object, and will be maintained using the 
 * Observer/Observable pattern of communication
 * 
 * @author matthewfaw
 *
 */
public class ApplicationController {
	/**
	 * XXX: For testing purposes
	 */
	public void init()
	{
		/*
		 * AppScene scene  = new AppScene()
		 * 
		 * myRouter = new Router(appScene)
		 * 
		 * void  createNewEnemy()
		 * {
		 * 		Enemy enemy = new Enemy();
		 * 		myRouter.distributeEnemy(enemy);
		 * }
		 * 
		 */
		
		
		
		DeserializeJSON derp = new DeserializeJSON(); 
		
		TowerData data = (TowerData)derp.DeserializeFromFile("towers/ActualTower", TowerData.class);
		System.out.println(data.getBuyPrice());

		SerializeJSON ser = new SerializeJSON();
		
		try {
			
			// Terrain Data 
			TerrainData terrain1 = new TerrainData("grass", 0, 0);
			TerrainData terrain2 = new TerrainData("water", 0, 1);
			// Tower Data
			TowerData tow = new TowerData();
			tow.setBuyPrice(40);
			tow.setCollisionRadius(2);
			tow.setImagePath("src/resources/boss.png");
			List<Map<Integer, Integer>> list = new ArrayList<Map<Integer, Integer>>();
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			map.put(100, 10);
			map.put(20, 50);
			list.add(map);
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
			md.addSpawnPoint(new Point(0,0));
			md.addSinkPoint(new Point(1,1));
			
			ser.SerializeToFile(md, "SampleGame/"+md.getClass().getSimpleName());
			ser.SerializeToFile(terrain1,"SampleGame/"+terrain1.getClass().getSimpleName()+"1");
			ser.SerializeToFile(terrain2, "SampleGame/"+terrain2.getClass().getSimpleName()+"2");
			ser.SerializeToFile(tow, "SampleGame/"+tow.getClass().getSimpleName());
			ser.SerializeToFile(ed, "SampleGame/"+ed.getClass().getSimpleName());
			ser.SerializeToFile(wd, "SampleGame/"+wd.getClass().getSimpleName());
			ser.SerializeToFile(pd, "SampleGame/"+pd.getClass().getSimpleName());
			ser.SerializeToFile(pdd, "SampleGame/"+pdd.getClass().getSimpleName());
			
			
			TerrainMap terrainMap = new TerrainMap(md);
			terrainMap.getDestination();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Helper method to create the backend map object
	 * from the GameData file
	 */
	private void constructMap()
	{
		
	}
	/**
	 * Helper method to create the backend resource store object
	 * from the GameData file
	 */
	private void constructResourceStore()
	{
		
	}
	/**
	 * Helper method to create the view object
	 * from the GameData file
	 */
	private void constructGUI()
	{
		
	}
	/*
	public static void main(String[] args)
	{
		ApplicationController appcont = new ApplicationController();
		appcont.init();
	}
	*/
}