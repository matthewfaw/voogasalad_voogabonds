package engine.model.strategies;

import javafx.util.Pair;
import utility.Point;

public interface IMovementStrategy {

	public Pair<Double, Point> nextMove(IMovable m);
}
