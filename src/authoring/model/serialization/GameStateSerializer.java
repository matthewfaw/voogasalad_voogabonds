package authoring.model.serialization;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import authoring.controller.Router;
import authoring.controller.TowerDataController;
import authoring.model.EnemyData;
import authoring.view.side_panel.InfoTabs;

public class GameStateSerializer {

	private static final String DEFAULT_FILE_LOCATION = "src/SerializedFiles/";
	private Router router;
	private JSONSerializer ser = new JSONSerializer();
	private JSONDeserializer des = new JSONDeserializer();

	
	/**
	 * 
	 * @param gameName
	 * Note: This code is ugly as hell but I'm refactoring as soon as basic is ok.
	 */
	public void saveGameState(String gameName){

		router = InfoTabs.getRouter();

		createNewDirectory(gameName,"");

		try {
			createNewDirectory("EnemyData", gameName);
			ser.serializeToFile(router.getEnemyDataController().finalizeEnemyDataMap(), gameName+"/"+"EnemyData"+"/"+"exampleEnemyData");
		}catch(Exception e){
			System.out.println("Enemy fail");
		}
		try{
			createNewDirectory("TowerData", gameName);
			ser.serializeToFile(router.getTowerDataController().finalizeTowerDataMap(), gameName+"/"+"TowerData"+"/"+"exampleTowerData");
		}catch(Exception e1){
			System.out.println("Tower fail");
		}
		try{
			createNewDirectory("WeaponData", gameName);
			ser.serializeToFile(router.getWeaponDataController().finalizeWeaponDataMap(), "/"+gameName+"/"+"WeaponData"+"/"+"exampleWeaponData");
		}catch(Exception e){
			System.out.println("Weapon fail");
		}
		try{
			createNewDirectory("ProjectileData", gameName);
			ser.serializeToFile(router.getProjectileDataController().getProjectileDataMap(), "/"+gameName+"/"+"ProjectileData"+"/"+"exampleProjectileData");
		}catch(Exception e){
			System.out.println("Projectile fail");
		}
		try{
			createNewDirectory("WaveData", gameName);
			ser.serializeToFile(router.getWaveDataController().finalizeWaveDataMap(), "/"+gameName+"/"+"WaveData"+"/"+"exampleWaveData");
		}catch(Exception e){
			System.out.println("Wave fail");
		}
		try{
			createNewDirectory("MapData", gameName);
			ser.serializeToFile(router.getMapDataController().getMapData(), gameName+"/"+"MapData"+"/"+"exampleMapData");
		}catch(Exception e){
			System.out.println("Map fail");
		}
		try{
			createNewDirectory("PlayerData", gameName);
			ser.serializeToFile(router.getPlayerDataController().getPlayerData(), gameName+"/"+"PlayerData"+"/"+"examplePlayerData");
		}catch(Exception e){
			System.out.println("Player fail");
		}


	}

	private void createNewDirectory(String gameName, String modifier){
		
		if (modifier!= ""){
			modifier = modifier+"/";
		}

		File newDir = new File(DEFAULT_FILE_LOCATION+modifier+gameName);

		if (!newDir.exists()){
			newDir.mkdir();
		}
		else{
			//prompt user if they want to overwrite save data
			System.out.println("File already exists.");
		}

	}

}
