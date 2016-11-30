package engine.model.strategies.movement;

import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import javafx.util.Pair;
import utility.Point;

abstract public class AbstractMovementStrategy implements IMovementStrategy {
	
	@Override
	public  Pair<Double, Point> nextMove(IMovable m) {
		if (m.getGoal() != null)
			return nextMoveWithGoal(m);
		else
			return nextMoveNoGoal(m);
	}

	abstract protected Pair<Double, Point> nextMoveNoGoal(IMovable m);

	abstract protected Pair<Double, Point> nextMoveWithGoal(IMovable m);

}
