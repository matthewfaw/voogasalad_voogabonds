package authoring.model.serialization;

import java.io.File;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import authoring.controller.LevelDataContainer;
import authoring.controller.Router;
import authoring.model.LevelData;
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
			createNewDirectory("LevelData", gameName);
			List<LevelData> ldc = router.getLevelDataContainer().finalizeLevelDataMap();
			ser.serializeToFile(ldc, gameName+"/"+"LevelData"+"/"+"LevelData");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Level fail");
		}
		try{
			createNewDirectory("MapData", gameName);
			ser.serializeToFile(router.getMapDataContainer(), gameName+"/"+"MapData"+"/"+"MapData");
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
