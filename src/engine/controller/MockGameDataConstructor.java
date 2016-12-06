package engine.controller;

import java.util.ArrayList;
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
import utility.Point;

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
	
	MapData getMockMapData()
	{
		MapData mapData = new MapData();
		try {
			int x = 14;
			int y = 12;
			int xmin = x/3;
			int xmax = 2*x/3;
			int ymax = y/2;
			mapData.setNumXCells(x);
			mapData.setNumYCells(y);
			for (int i=0; i<x; ++i) {
				for (int j=0; j<y; ++j) {
					TerrainData terrain;
					if (i > xmin && i < xmax && j < ymax) {
						terrain = new TerrainData("grass", i, j, 50, "0x008000");
					} else {
						terrain = new TerrainData("water", i, j, 50, "0x0000ff");
					}
					mapData.addTerrainData(terrain);
				}
			}
			ArrayList<Point> spawnPoints = new ArrayList<Point>();
			spawnPoints.add(new Point(1, 1));
			mapData.addSpawnPoints("spawnPoint", spawnPoints);
			mapData.addSinkPoint(new Point(x-1, 1));
			mapData.cellSize(50);
//			mapData.addValidTerrain(terrain1.getName(), "exampleColor 1");
//			mapData.addValidTerrain(terrain2.getName(), "exampleColor 2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapData;
	}
	
	private void constructMockData() throws SerializationException
	{
		JSONDeserializer derp = new JSONDeserializer(); 
		
//		TowerData data = (TowerData)derp.DeserializeFromFile("towers/ActualTower", TowerData.class);
//		System.out.println(data.getBuyPrice());

		JSONSerializer ser = new JSONSerializer();
		
		try {
			
			// Terrain Data 
			TerrainData terrain1 = new TerrainData("grass", 0, 0, 50, "0x008000");
			TerrainData terrain2 = new TerrainData("water", 0, 1, 50, "0x0000ff");
			List<String> terrainList = new ArrayList<String>();
			terrainList.add(terrain1.getName());
			terrainList.add(terrain2.getName());
			// Tower Data
			TowerData tow = new TowerData();
			tow.setTerrainList(terrainList);
			tow.setWeaponName("weapon 1");
			tow.setBuyPrice(40);
			tow.setCollisionRadius(2);
			tow.setImagePath("src/resources/boss.png");
			tow.setMaxHealth(1000);
			tow.setMoveStrategyName("fast as fuck");
			tow.setName("tower 1");
			tow.setSellPrice(100);
			
			// Enemy Data
			EnemyData ed = new EnemyData();
			ed.setMoveStrategyName("derp");
			ed.setTerrainList(terrainList);
			ed.setCollisionRadius(5);
			ed.setImagePath("src/resources/boss.png");
			ed.setKillReward(100);
			ed.setMaxHealth(100);
			ed.setName("enemy 1");
			ed.setMoveSpeed(10);
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
			
//			ser.serializeToFile(md, "exampleGame/MapData/"+md.getClass().getSimpleName());
//			ser.serializeToFile(terrain1, "exampleGame/TerrainData/"+terrain1.getClass().getSimpleName()+"1");
//			ser.serializeToFile(terrain2, "exampleGame/TerrainData/"+terrain2.getClass().getSimpleName()+"2");
//			ser.serializeToFile(tow, "exampleGame/TowerData/"+tow.getClass().getSimpleName());
			ser.serializeToFile(ed, "exampleGame/EnemyData/"+ed.getClass().getSimpleName());
//			ser.serializeToFile(wd, "exampleGame/WeaponData/"+wd.getClass().getSimpleName());
//			ser.serializeToFile(pd, "exampleGame/ProjectileData/"+pd.getClass().getSimpleName());
//			ser.serializeToFile(pdd, "exampleGame/PlayerData/"+pdd.getClass().getSimpleName());
			
			
			TerrainMap terrainMap = new TerrainMap(md);
			terrainMap.getDestination();
		} catch (Exception e) {
			//TODO add more meaningful error message
			throw new SerializationException("Could not serialize");
		}
	}
	public static void main(String[] args) throws SerializationException
	{
		JSONSerializer ser = new JSONSerializer();
		MockGameDataConstructor m = new MockGameDataConstructor();
		m.constructMockData();
	}
}
