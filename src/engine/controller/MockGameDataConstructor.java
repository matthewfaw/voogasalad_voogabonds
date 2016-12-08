package engine.controller;

import java.util.ArrayList;
import java.util.List;

import authoring.controller.MapDataContainer;
import authoring.model.ComponentData;
import authoring.model.EnemyData;
import authoring.model.EntityData;
import authoring.model.LevelData;
import authoring.model.PlayerData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WaveData;
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
	
	MapDataContainer getMockMapData(TerrainData terrain1, TerrainData terrain2)
	{
		MapDataContainer mapData = new MapDataContainer();
		try {
			int x = 14;
			int y = 12;
			int xmin = x/3;
			int xmax = 2*x/3;
			int ymax = y/2;
			mapData.setDimensions(x,y);
			for (int i=0; i<x; ++i) {
				for (int j=0; j<y; ++j) {
					TerrainData terrain;
					if (i > xmin && i < xmax && j < ymax) {
						terrain = terrain1;
					} else {
						terrain = terrain2;
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

			//Map data
			MapDataContainer md = this.getMockMapData(terrain1, terrain2);
			
			// Entity data
			EntityData ed  = new EntityData();
			ed.setName("Awesome Tower");
			ComponentData cd = new ComponentData();
			cd.setComponentName("PhysicalComponent");
			
			// Player Data
			PlayerData pdd = new PlayerData();
			pdd.setLoseStrategyName("lose strategy 1");
			pdd.setStartingCash(2000);
			pdd.setWinStrategyName("win strategy 1");
			pdd.setStartingLives(5);
			
			//Level data
			WaveData wad1 = new WaveData();
			wad1.setName("Cool wave");
			wad1.setNumEnemies(10);
			wad1.setSpawnPointName("Cool spawn point");
			wad1.setTimeBetweenEnemy(20);
			wad1.setTimeForWave(0);
			wad1.setWaveEnemy("Cool enemy");
			WaveData wad2 = new WaveData();
			wad2.setName("Awesome wave");
			wad2.setNumEnemies(20);
			wad2.setSpawnPointName("Awesome spawn point");
			wad2.setTimeBetweenEnemy(50);
			wad2.setTimeForWave(1);
			wad2.setWaveEnemy("Awesome enemy");
			WaveData wad3 = new WaveData();
			wad3.setName("Dumb wave");
			wad3.setNumEnemies(30);
			wad3.setSpawnPointName("Dumb spawn point");
			wad3.setTimeBetweenEnemy(60);
			wad3.setTimeForWave(6);
			wad3.setWaveEnemy("Dumb enemy");
			LevelData ld = new LevelData();
			ld.addWaveDataListToList(wad1);
			ld.addWaveDataListToList(wad2);
			ld.addWaveDataListToList(wad3);
			
			ser.serializeToFile(md, "exampleGame/MapData/"+md.getClass().getSimpleName());
			ser.serializeToFile(terrain1, "exampleGame/TerrainData/"+terrain1.getClass().getSimpleName()+"1");
			ser.serializeToFile(terrain2, "exampleGame/TerrainData/"+terrain2.getClass().getSimpleName()+"2");
			ser.serializeToFile(pdd, "exampleGame/PlayerData/"+pdd.getClass().getSimpleName());
			ser.serializeToFile(pdd, "exampleGame/LevelData/"+pdd.getClass().getSimpleName());
			
			
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
