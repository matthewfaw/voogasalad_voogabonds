package engine.model.strategies.movement;

import engine.model.strategies.AbstractMovementStrategy;
import engine.model.strategies.IMovable;
import engine.model.strategies.IPhysical;
import engine.model.strategies.StrategyFactory;
import javafx.util.Pair;
import utility.Point;

public class NoMovementStrategy extends AbstractMovementStrategy {

	public NoMovementStrategy(StrategyFactory creator) {
		super(creator);
	}

	@Override
	protected Pair<Double, Point> nextMoveNoGoal(IMovable m, IPhysical p) {
		return new Pair<Double, Point>(p.getHeading(), p.getPosition());
	}

	@Override
	protected Pair<Double, Point> nextMoveWithGoal(IMovable m, IPhysical p) {
		return new Pair<Double, Point>(p.getHeading(), p.getPosition());
	}

}
