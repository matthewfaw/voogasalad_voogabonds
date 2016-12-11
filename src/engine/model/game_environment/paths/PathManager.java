package engine.model.game_environment.paths;

import java.util.List;
import java.util.stream.Collectors;

import engine.model.game_environment.terrain.Terrain;
import utility.Point;

public class PathManager {
	
	private List<Point> myPath;
	
	public PathManager(List<Terrain> aPathToFollow)
	{
		myPath = aPathToFollow.stream().map(t -> t.getCenter()).collect(Collectors.toList());
	}
	
	public Point getNextVertex(Point p) {
		for (int i = 0; i < myPath.size() - 1; i++) {
			Point a = myPath.get(i);
			Point b = myPath.get(i + 1);
			if (!a.equals(b) && p.onLine(a, b))
				return b;
		}
		return null;
	}
	
}
