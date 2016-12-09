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
		
		for (Stack<TerrainData> terrainCell: aTerrainMapData.getTerrainList()) {
			TerrainData topTerrain = terrainCell.peek();
			terrain[(int) topTerrain.getLoc().getX()][(int) topTerrain.getLoc().getY()] = new Terrain(terrainCell);
		}
		return terrain;
	}
}
