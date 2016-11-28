package engine.model.game_environment.terrain;

import authoring.model.map.MapData;

public class TerrainGridFactory {
	public TerrainGridFactory()
	{
	}
	
	//TODO: Add the real implementation of this--just for testing purposes
	@Deprecated
	public Terrain[][] constructTerrainMap(MapData aTerrainMapData)
	{
		Terrain[][] terrain = new Terrain[2][2];
		terrain[0][0] = new Terrain(0,0,"grass");
		terrain[0][1] = new Terrain(0,1,"grass");
		terrain[1][0] = new Terrain(1,0,"grass");
		terrain[1][1] = new Terrain(1,1,"grass");
		return terrain;
	}
}
