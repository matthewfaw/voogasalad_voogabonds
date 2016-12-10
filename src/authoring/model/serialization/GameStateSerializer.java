package authoring.model.serialization;

import java.io.File;
import java.util.AbstractMap;
import authoring.controller.Router;
import authoring.model.WaveData;

public class GameStateSerializer {

	private static final String DEFAULT_FILE_LOCATION = "src/SerializedFiles/";
	private Router router;
	private JSONSerializer ser = new JSONSerializer();
	private JSONDeserializer des = new JSONDeserializer();
	
	public GameStateSerializer(Router r){
		this.router = r;
	}


	/**
	 * 
	 * @param gameName
	 * Note: This code is ugly as hell but I'm refactoring as soon as basic is ok.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void saveGameState(String gameName) throws InstantiationException, IllegalAccessException{

		createNewDirectory(gameName,"");

//		try {
//			createNewDirectory("EnemyData", gameName);
//			AbstractMap<String, EnemyData> ed = router.getEnemyDataController().finalizeEnemyDataMap();
//			for (String edName : ed.keySet()){
//				ser.serializeToFile(ed.get(edName), gameName+"/"+"EnemyData"+"/"+edName);
//			}
//		}catch(Exception e){
//			System.out.println("Enemy fail");
//		}
//		try{
//			createNewDirectory("TowerData", gameName);
//			AbstractMap<String, TowerData> td = router.getTowerDataController().finalizeTowerDataMap();
//			System.out.println(td);
//			for (String tdName : td.keySet()){
//				System.out.println(tdName);
//				System.out.println(td.keySet());
//			ser.serializeToFile(td.get(tdName), gameName+"/"+"TowerData"+"/"+tdName);
//			}
//		}catch(Exception e1){
//			System.out.println("Tower fail");
//		}
//		try{
//			createNewDirectory("WeaponData", gameName);
//			AbstractMap<String, WeaponData> wd = router.getWeaponDataController().finalizeWeaponDataMap();
//			for (String wdName : wd.keySet()){
//			ser.serializeToFile(wd.get(wdName), "/"+gameName+"/"+"WeaponData"+"/"+wdName);
//			}
//			}catch(Exception e){
//			System.out.println("Weapon fail");
//		}
//		try{
//			createNewDirectory("ProjectileData", gameName);
//			AbstractMap<String, ProjectileData> pd = router.getProjectileDataController().getProjectileDataMap();
//			for (String pdName : pd.keySet()){
//			ser.serializeToFile(pd.get(pdName), "/"+gameName+"/"+"ProjectileData"+"/"+pdName);
//			}
//		}catch(Exception e){
//			System.out.println("Projectile fail");
//		}
		try{
			createNewDirectory("EntityData", gameName);
			ser.serializeToFile(router.getEntityDataContainer(), gameName+"/"+"EntityData"+"/"+"EntityData");
		}
		catch(Exception e){
			System.out.println("Entity Fail");
		}
		try{
			createNewDirectory("WaveData", gameName);
			AbstractMap<String, WaveData> wavd = router.getWaveDataContainer().finalizeWaveDataMap();
			for (String wavdName : wavd.keySet()){
			ser.serializeToFile(wavd.get(wavdName), "/"+gameName+"/"+"WaveData"+"/"+wavdName);
			}
		}catch(Exception e){
			System.out.println("Wave fail");
		}
		try{
			createNewDirectory("MapData", gameName);
			ser.serializeToFile(router.getMapDataContainer(), gameName+"/"+"MapDataContainer"+"/"+"MapData");
		}catch(Exception e){
			System.out.println("Map fail");
		}
		try{
			createNewDirectory("PlayerData", gameName);
			ser.serializeToFile(router.getPlayerDataContainer().getPlayerData(), gameName+"/"+"PlayerData"+"/"+"PlayerData");
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
