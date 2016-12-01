package authoring.model.serialization;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.collections.ObservableMap;
import authoring.controller.Router;
import authoring.controller.TowerDataContainer;
import authoring.model.EnemyData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WaveData;
import authoring.model.WeaponData;
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
			AbstractMap<String, EnemyData> ed = router.getEnemyDataController().finalizeEnemyDataMap();
			for (String edName : ed.keySet()){
				ser.serializeToFile(ed.get(edName), gameName+"/"+"EnemyData"+"/"+edName);
			}
		}catch(Exception e){
			System.out.println("Enemy fail");
		}
		try{
			createNewDirectory("TowerData", gameName);
			ObservableMap<String, TowerData> td = router.getTowerDataController().finalizeTowerDataMap();
			for (String tdName : td.keySet()){
			ser.serializeToFile(td.get(tdName), gameName+"/"+"TowerData"+"/"+tdName);
			}
		}catch(Exception e1){
			System.out.println("Tower fail");
		}
		try{
			createNewDirectory("WeaponData", gameName);
			AbstractMap<String, WeaponData> wd = router.getWeaponDataController().finalizeWeaponDataMap();
			for (String wdName : wd.keySet()){
			ser.serializeToFile(wd.get(wdName), "/"+gameName+"/"+"WeaponData"+"/"+wdName);
			}
			}catch(Exception e){
			System.out.println("Weapon fail");
		}
		try{
			createNewDirectory("ProjectileData", gameName);
			AbstractMap<String, ProjectileData> pd = router.getProjectileDataController().getProjectileDataMap();
			for (String pdName : pd.keySet()){
			ser.serializeToFile(pd.get(pdName), "/"+gameName+"/"+"ProjectileData"+"/"+pdName);
			}
		}catch(Exception e){
			System.out.println("Projectile fail");
		}
		try{
			createNewDirectory("WaveData", gameName);
			LinkedHashMap<String, WaveData> wavd = router.getWaveDataController().finalizeWaveDataMap();
			for (String wavdName : wavd.keySet()){
			ser.serializeToFile(wavd.get(wavdName), "/"+gameName+"/"+"WaveData"+"/"+wavdName);
			}
		}catch(Exception e){
			System.out.println("Wave fail");
		}
		try{
			createNewDirectory("MapData", gameName);
			ser.serializeToFile(router.getMapDataController(), gameName+"/"+"MapData"+"/"+"MapData");
		}catch(Exception e){
			System.out.println("Map fail");
		}
		try{
			createNewDirectory("PlayerData", gameName);
			ser.serializeToFile(router.getPlayerDataController().getPlayerData(), gameName+"/"+"PlayerData"+"/"+"PlayerData");
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
