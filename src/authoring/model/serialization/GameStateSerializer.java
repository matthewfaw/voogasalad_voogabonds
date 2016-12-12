package authoring.model.serialization;

import java.io.File;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import authoring.controller.LevelDataContainer;
import authoring.controller.Router;
import authoring.model.EntityData;
import authoring.model.LevelData;
import authoring.model.WaveData;
import engine.exceptions.SerializationException;

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
	 * @throws SerializationException 
	 */
	public void saveGameState(String gameName) throws InstantiationException, IllegalAccessException, SerializationException{

		createNewDirectory(gameName,"");

		try{
			createNewDirectory("EntityData", gameName);
			AbstractMap<String, EntityData> entd = router.getEntityDataContainer().getEntityDataMap();
			for (String entity: entd.keySet()){
			    ser.serializeToFile(entd.get(entity), "/"+gameName+"/"+"EntityData"+"/"+entity);
			}
			//ser.serializeToFile(router.getEntityDataContainer(), gameName+"/"+"EntityData"+"/"+"EntityData");
		}
		catch(Exception e){
			throw new SerializationException("System was unable to load previous entity data.");
		}
		try{
			createNewDirectory("WaveData", gameName);
			AbstractMap<String, WaveData> wavd = router.getWaveDataContainer().finalizeWaveDataMap();
			for (String wavdName : wavd.keySet()){
			ser.serializeToFile(wavd.get(wavdName), "/"+gameName+"/"+"WaveData"+"/"+wavdName);
			}
		}catch(Exception e){
			throw new SerializationException("System was unable to load previous wave data.");
		}
		try{
			createNewDirectory("LevelData", gameName);
			LevelDataContainer ldc = router.getLevelDataContainer();
			ser.serializeToFile(ldc, gameName+"/"+"LevelData"+"/"+"LevelData");
		}catch(Exception e){
			throw new SerializationException("System was unable to load previous level data.");
		}
		try{
			createNewDirectory("MapData", gameName);
			ser.serializeToFile(router.getMapDataContainer(), gameName+"/"+"MapData"+"/"+"MapData");
		}catch(Exception e){
			throw new SerializationException("System was unable to load previous map data.");
		}
		try{
			createNewDirectory("PlayerData", gameName);
			ser.serializeToFile(router.getPlayerDataContainer().getPlayerData(), gameName+"/"+"PlayerData"+"/"+"PlayerData");
		}catch(Exception e){
			throw new SerializationException("System was unable to load previous player data.");
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
