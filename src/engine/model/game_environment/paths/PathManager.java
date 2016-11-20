package engine.model.game_environment.paths;

import java.util.List;

import engine.model.game_environment.terrain.Terrain;

public class PathManager {
	
	private List<Terrain> myPath;
	
	PathManager(List<Terrain> aPathToFollow)
	{
		myPath = aPathToFollow;
	}
}
