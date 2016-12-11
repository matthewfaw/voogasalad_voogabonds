package authoring.model.serialization;
import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
import authoring.controller.Router;
import authoring.model.map.TerrainData;
import utility.ErrorBox;

public class GameStateLoader {
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
	private JSONDeserializer deserializer = new JSONDeserializer();
	
	public void loadMapData(String filePath, Router r){
		try{
			MapDataContainer newMapData = (MapDataContainer) deserializer.deserializeFromFile(filePath, MapDataContainer.class);
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
}
