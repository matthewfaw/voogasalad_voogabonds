package engine.model.strategies;

import engine.model.game_environment.paths.PathManager;
import utility.Point;

public interface IMovable {
	
	abstract public Point getGoal();
	abstract public double getTurnSpeed();
	abstract public double getMoveSpeed();
	abstract public PathManager getPath();
	
}
