package authoring.model.serialization;
import java.io.File;
import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
import authoring.controller.EntityDataContainer;
import authoring.controller.Router;
import authoring.model.EntityData;
import authoring.model.map.TerrainData;
import utility.ErrorBox;

public class GameStateLoader {
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
	private JSONDeserializer deserializer = new JSONDeserializer();
	
	public void loadMapData(Router r, String gameTitle){
		String mapFilePath = myResources.getString("DefaultSerialPath") + gameTitle + myResources.getString("MapDataFilePath");
		try{
			MapDataContainer newMapData = (MapDataContainer) deserializer.deserializeFromFile(mapFilePath, MapDataContainer.class);
			MapDataContainer routerMapData = r.getMapDataContainer();
			
			routerMapData.setDimensions(newMapData.getNumXCells(), newMapData.getNumYCells()); //Set dimensions
			routerMapData.cellSize(newMapData.getCellSize());                                  //Set cell size
			
			//Load spawn points
			for (String spawnName: newMapData.getSpawnPointMap().keySet()){
				routerMapData.addSpawnPoints(spawnName, newMapData.getSpawnPointMap().get(spawnName));
			}
			
			//Load sink points
			for (String sinkName: newMapData.getSinkPointMap().keySet()){
				routerMapData.addSinkPoints(sinkName, newMapData.getSinkPointMap().get(sinkName));
			}
			
			//Load terrainList
			for (TerrainData td: newMapData.getTerrainList()){
				routerMapData.addTerrainData(td);
			}
			
			//Load valid terrain
			for (String terrainName: newMapData.getValidTerrainMap().keySet()){
				routerMapData.addValidTerrain(terrainName, newMapData.getValidTerrainMap().get(terrainName));
			}
		}catch(Exception e){
			ErrorBox.displayError(myResources.getString("LoadAuthoringError"));
		}
	}
	
	public void loadEntityData(Router r, String gameTitle){
		String entityFilePath = myResources.getString("DefaultSerialPath") + gameTitle + myResources.getString("EntityDataFilePath");
		File directory = new File(entityFilePath);
		File[] entityFiles = directory.listFiles();
		try{
			EntityDataContainer routerEntityData = r.getEntityDataContainer();
			for (File f: entityFiles){
				EntityData oldEntityData = (EntityData) deserializer.deserializeFromFile(f.toString(), EntityData.class);
				routerEntityData.createEntityData(oldEntityData);
			}
		}catch(Exception e){
			e.printStackTrace();
			ErrorBox.displayError(myResources.getString("LoadAuthoringError"));
		}
	}
	
}
