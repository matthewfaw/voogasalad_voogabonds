package engine.controller;

import java.util.ArrayList;
import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.ComponentData;
import authoring.model.EntityData;
import authoring.model.LevelData;
import authoring.model.PlayerData;
import authoring.model.WaveData;
import authoring.model.map.TerrainData;
import authoring.model.serialization.JSONDeserializer;
import authoring.model.serialization.JSONSerializer;
import engine.exceptions.SerializationException;
import engine.model.components.concrete.PurchasableComponentData;
import engine.model.components.concrete.SellableComponentData;
import engine.model.game_environment.terrain.TerrainMap;
import utility.Point;
import utility.ResouceAccess;

/**
 * A class to construct some mock game data for testing
 * This class contains examples of how to serialize and deserialize objects using Gson
 * @author matthewfaw
 *
 */
public class AlanGameDataConstructor {
	
	public MapDataContainer getMockMapData () {
		MapDataContainer mapData = new MapDataContainer();
		int x = 10;
		int y = 10;
		int xmin = x/3;
		int xmax = 2*x/3;
		int ymin = y/3;
		int ymax = 2*y/3;
		mapData.setDimensions(x,y);
		for (int i=0; i<x; ++i) {
			for (int j=0; j<y; ++j) {
				TerrainData terrain;
				if (i > xmin && i < xmax && j < ymax && j > ymin) {
					terrain = new TerrainData("water", i, j, 50, "0x0000ff");
				} else {
					terrain = new TerrainData("grass", i, j, 50, "0x008000");
				}
				mapData.addTerrainData(terrain);
			}
		}
		
		ArrayList<Point> spawnPoints = new ArrayList<Point>();
		spawnPoints.add(new Point(1, 1));
		mapData.addSpawnPoints("spawnPoint", spawnPoints);
		
		ArrayList<Point> sinkPoints = new ArrayList<Point>();
		sinkPoints.add(new Point(4, 4));
		mapData.addSinkPoints("sinkPoint", sinkPoints);
		
		mapData.cellSize(50);

		return mapData;
	}
	
	public void constructMockData() throws SerializationException {
		
		JSONDeserializer derp = new JSONDeserializer(); 
		JSONSerializer ser = new JSONSerializer();
		
		try {
			
			//Map data
			MapDataContainer md = this.getMockMapData();
			
			//Sell/Buy data
			ComponentData pcd = new ComponentData();
			pcd.setComponentName("PurchasableComponentData");
			pcd.addField("myPurchasePrice", "100");
			
			ComponentData scd = new ComponentData();
			scd.setComponentName("SellableComponentData");
			scd.addField("mySellPrice", "50");
			
			
			
			// ********* Tower Entity Data ********* //
			EntityData tower  = new EntityData();
			
			tower.addComponent("PurchasableComponentData", pcd);
			tower.addComponent("SellableComponentData", scd);
			
			// Name
			tower.setName("tower");
			ComponentData tower_physical_component = new ComponentData();
			tower_physical_component.setComponentName("PhysicalComponent");
			tower_physical_component.addField("myHeading", "0");
			tower_physical_component.addField("myImagePath", "src/resources/cow.png");
			tower_physical_component.addField("myImageSize", "50");
			tower_physical_component.addField("myValidTerrains", "grass");

			ComponentData tower_moveable_component = new ComponentData();
			tower_moveable_component.setComponentName("MoveableComponent");
			tower_moveable_component.addField("myTurnSpeed", "1");
			tower_moveable_component.addField("myMoveSpeed", "0");
			tower_moveable_component.addField("myMaxDistance", "1000");
			tower_moveable_component.addField("myMovementCalc", "NoMovementStrategy");
//			tower_moveable_component.addField("myMovementCalc", "GreedyMovementStrategy");
			tower_moveable_component.addField("removeOnGoal", "true");
			tower_moveable_component.addField("explodesOnGoal", "false");
			
			ComponentData tower_creator_component = new ComponentData();
			tower_creator_component.setComponentName("CreatorComponent");
			tower_creator_component.addField("mySpawningStrategy", "BasicSpawnStrategy");
			tower_creator_component.addField("myTimeBetweenSpawns", "2");
			tower_creator_component.addField("mySpawnName", "projectile");
			
			ComponentData tower_targeting_component = new ComponentData();
			tower_targeting_component.setComponentName("TargetingComponent");
			tower_targeting_component.addField("mySightRange", "400");
			tower_targeting_component.addField("mySightWidth", "360");
			tower_targeting_component.addField("myTargetsEnemies", "true");
			tower_targeting_component.addField("myTargetingStrategy", "BadTargetingStrategy");
			
			ComponentData tower_team_component = new ComponentData();
			tower_team_component.setComponentName("TeamComponent");
			tower_team_component.addField("myTeamID", "us");
			
//			ComponentData cd4 = new ComponentData();
//			cd4.setComponentName("DamageDealingComponent");
//			cd4.addField("myDamage", "50");
//			cd4.addField("myDamageRadius", "2");
//			cd4.addField("myDamageCalc", "BinaryDamageStrategy");
			
//			ComponentData cd5 = new ComponentData();
//			cd5.setComponentName("CreatorComponent");
//			cd5.addField("mySpawningStrategy", "BasicSpawnStrategy");
//			cd5.addField("myTimeBetweenSpawns", "10");
//			cd5.addField("mySpawnName", "Awesome Tower2");
			
			tower.addComponent("PhysicalComponent", tower_physical_component);
			tower.addComponent("MoveableComponent", tower_moveable_component);
			tower.addComponent("CreatorComponent", tower_creator_component);
			tower.addComponent("TargetingComponent", tower_targeting_component);
			tower.addComponent("TeamComponent", tower_team_component);
			
//			ed.addComponent("PhysicalComponent",cd1);
//			ed.addComponent("CollidableComponent",cd2);
//			ed.addComponent("MoveableComponent",cd3);
//			ed.addComponent("CreatorComponent",cd5);
			
			
			
			
			// ********* Projectile Entity Data ********** //
			
			EntityData projectile  = new EntityData();
			
			projectile.setName("projectile");
			
			ComponentData projectile_physical_component = new ComponentData();
			projectile_physical_component.setComponentName("PhysicalComponent");
			projectile_physical_component.addField("myHeading", "0");
			projectile_physical_component.addField("myImagePath", "src/resources/cookie.png");
			projectile_physical_component.addField("myImageSize", "10");
			projectile_physical_component.addField("myValidTerrains", "grass, ice");
			
			ComponentData projectile_moveable_component = new ComponentData();
			projectile_moveable_component.setComponentName("MoveableComponent");
			projectile_moveable_component.addField("myMovementCalc", "GreedyMovementStrategy");
			projectile_moveable_component.addField("myTurnSpeed", "10");
			projectile_moveable_component.addField("myMoveSpeed", "20");
			projectile_moveable_component.addField("explodesAtMaxDistance", "true");
			projectile_moveable_component.addField("myMaxDistance", "300");
			projectile_moveable_component.addField("removeOnGoal", "true");
			projectile_moveable_component.addField("explodesOnGoal", "true");
			
			ComponentData projectile_collidable_component = new ComponentData();
			projectile_collidable_component.setComponentName("CollidableComponent");
			projectile_collidable_component.addField("myCollisionRadius", "0");
			
			ComponentData projectile_team_component = new ComponentData();
			projectile_team_component.setComponentName("TeamComponent");
			projectile_team_component.addField("myTeamID", "us");
			
			ComponentData projectile_targeting_component = new ComponentData();
			projectile_targeting_component.setComponentName("TargetingComponent");
			projectile_targeting_component.addField("mySightRange", "200");
			projectile_targeting_component.addField("mySightWidth", "360");
			projectile_targeting_component.addField("myTargetsEnemies", "true");
			projectile_targeting_component.addField("myTargetingStrategy", "BadTargetingStrategy");
			
			projectile.addComponent("PhysicalComponent", projectile_physical_component);
			projectile.addComponent("TargetingComponent", projectile_targeting_component);
			projectile.addComponent("CollidableComponent", projectile_collidable_component);
			projectile.addComponent("TeamComponent", projectile_team_component);
			projectile.addComponent("MoveableComponent", projectile_moveable_component);

			
			// Player Data
			PlayerData pdd = new PlayerData();
			pdd.setLoseStrategyName("lose strategy 1");
			pdd.setStartingCash(2000);
			pdd.setWinStrategyName("win strategy 1");
			pdd.setStartingLives(5);
			
			//Level data
			WaveData wad1 = new WaveData();
			wad1.setName("Cool wave");
			wad1.setNumEnemies(1);
			wad1.setSpawnPointName("spawnPoint");
			wad1.setSinkPointName("sinkPoint");
			wad1.setTimeBetweenEnemy(20);
			wad1.setTimeForWave(0);
			wad1.setWaveEntity("Awesome Tower1");
			WaveData wad2 = new WaveData();
			wad2.setName("Awesome wave");
			wad2.setNumEnemies(20);
			wad2.setSpawnPointName("spawnPoint");
			wad2.setSinkPointName("sinkPoint");
			wad2.setTimeBetweenEnemy(50);
			wad2.setTimeForWave(1);
			wad2.setWaveEntity("Awesome Tower1");
			WaveData wad3 = new WaveData();
			wad3.setName("Dumb wave");
			wad3.setNumEnemies(30);
			wad3.setSpawnPointName("spawnPoint");
			wad3.setSinkPointName("sinkPoint");
			wad3.setTimeBetweenEnemy(60);
			wad3.setTimeForWave(6);
			wad3.setWaveEntity("Awesome Tower2");
			LevelData ld = new LevelData();
			ld.addWaveDataListToList(wad1);
			//ld.addWaveDataListToList(wad2);
			//ld.addWaveDataListToList(wad3);
			ld.setLevelName("0");
			LevelDataContainer ldc = new LevelDataContainer();
			ldc.createNewLevelData(ld);

			ser.serializeToFile(md, "exampleGame/MapData/"+md.getClass().getSimpleName());
			ser.serializeToFile(pdd, "exampleGame/PlayerData/"+pdd.getClass().getSimpleName());
			ser.serializeToFile(ldc, "exampleGame/LevelData/"+ldc.getClass().getSimpleName());
			ser.serializeToFile(tower, "exampleGame/EntityData/"+tower.getClass().getSimpleName()+1);
			ser.serializeToFile(projectile, "exampleGame/EntityData/"+projectile.getClass().getSimpleName()+2);
			
			derp.deserializeFromFile("SerializedFiles/exampleGame/MapData/"+md.getClass().getSimpleName(), MapDataContainer.class);
			derp.deserializeFromFile("SerializedFiles/exampleGame/PlayerData/"+pdd.getClass().getSimpleName(), PlayerData.class);
			derp.deserializeFromFile("SerializedFiles/exampleGame/LevelData/"+ldc.getClass().getSimpleName(), LevelDataContainer.class);
			derp.deserializeFromFile("SerializedFiles/exampleGame/EntityData/"+tower.getClass().getSimpleName()+1, EntityData.class);
			derp.deserializeFromFile("SerializedFiles/exampleGame/EntityData/"+projectile.getClass().getSimpleName()+2, EntityData.class);
			
			TerrainMap terrainMap = new TerrainMap(md);
//			terrainMap.getDestination();
		} catch (Exception e) {
			//TODO add more meaningful error message
			throw new SerializationException(ResouceAccess.getError("NoSerialize"));

		}
	}
	public static void main(String[] args) throws SerializationException
	{
		JSONSerializer ser = new JSONSerializer();
		MockGameDataConstructor m = new MockGameDataConstructor();
		m.constructMockData();
	}
}
