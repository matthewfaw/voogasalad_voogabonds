package engine.model.game_environment.terrain;

import authoring.controller.MapDataContainer;
import authoring.model.map.TerrainData;

public class TerrainGridFactory {
	public TerrainGridFactory()
	{
	}
	
	//TODO: Add the real implementation of this--just for testing purposes
	@Deprecated
	public Terrain[][] constructTerrainMap(MapDataContainer aTerrainMapData, int cellSize)
	{
		Terrain[][] terrain = new Terrain[aTerrainMapData.getNumXCells()][aTerrainMapData.getNumXCells()];
		for (TerrainData data: aTerrainMapData.getTerrainList()) {
			int xLoc = (int) data.getLoc().getX();
			int yLoc = (int) data.getLoc().getY();
			terrain[xLoc][yLoc] = new Terrain(xLoc,yLoc,data.getName(), cellSize);
		}
		return terrain;
	}
}
