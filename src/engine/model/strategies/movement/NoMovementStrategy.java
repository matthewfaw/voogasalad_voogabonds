package engine.model.strategies.movement;

import engine.model.strategies.IMovable;
import javafx.util.Pair;
import utility.Point;

public class NoMovementStrategy extends AbstractMovementStrategy {

	@Override
	protected Pair<Double, Point> nextMoveNoGoal(IMovable m) {
		//return new Pair<Double, Point>(m.getHeading(), m.getPosition());
		return null;
	}

	@Override
	protected Pair<Double, Point> nextMoveWithGoal(IMovable m) {
		//return new Pair<Double, Point>(m.getHeading(), m.getPosition());
		return null;
	}

}
