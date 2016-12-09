package engine.model.game_environment.terrain;

import java.util.Stack;

import authoring.controller.MapDataContainer;
import authoring.model.map.TerrainData;

public class TerrainGridFactory {
	public TerrainGridFactory()
	{
	}
	
	@Deprecated
	public Terrain[][] constructTerrainMap(MapDataContainer aTerrainMapData, int cellSize)
	{
		Terrain[][] terrain = new Terrain[aTerrainMapData.getNumXCells()][aTerrainMapData.getNumXCells()];

		for (int i=0; i<aTerrainMapData.getNumXCells(); ++i) {
			for (int j=0; j<aTerrainMapData.getNumYCells(); ++j) {
				Stack<TerrainData> terrainData = aTerrainMapData.getTerrainList()[i][j];
				terrain[i][j] = new Terrain(terrainData);
			}
		}
		return terrain;
	}
}
