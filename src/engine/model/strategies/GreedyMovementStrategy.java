package engine.model.strategies;

import javafx.util.Pair;
import utility.Point;

public class GreedyMovementStrategy implements IMovementStrategy {

	@Override
	public Pair<Double, Point> nextMove(IMovable m) {
		double newHeading = m.getLocation().towards(m.getGoal());
		
		double distance = Math.min(m.getMoveSpeed(), m.getLocation().euclideanDistance(m.getGoal()));
		
		
		return new Pair<Double, Point>(newHeading, m.getLocation().moveAlongHeading(distance, newHeading));
	}

}
