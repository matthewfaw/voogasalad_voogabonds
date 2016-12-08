package engine.model.strategies.movement;

import java.util.Random;

import engine.model.game_environment.paths.PathManager;
import engine.model.strategies.AbstractMovementStrategy;
import engine.model.strategies.IMovable;
import javafx.util.Pair;
import utility.Point;

public class PathMovementStrategy extends AbstractMovementStrategy{
	private static final Random RANDOM = new Random();
	private PathManager myPath;
	
	@Override
	public  Pair<Double, Point> nextMove(IMovable m) {
		if (myPath == null) {
			myPath = m.getPath();
		}
		return super.nextMove(m);
	}
	
	@Override
	protected Pair<Double, Point> nextMoveNoGoal(IMovable m) {
		//Spins around aimlessly
		return new Pair<Double, Point>(m.getHeading() + RANDOM.nextDouble() * m.getTurnSpeed(), m.getPosition());
	}

	@Override
	protected Pair<Double, Point> nextMoveWithGoal(IMovable m) {
		Point subGoal = myPath.getNextVertex(m.getPosition());
		
		//Check if we aren't on the path anymore
		if (subGoal == null)
			return nextMoveNoGoal(m);
		
		double newHeading = newHeadingTowards(subGoal, m);
		
		Point newPosition;
		if (m.getPosition().towards(subGoal) == newHeading)
			newPosition = moveTowards(subGoal, newHeading, m);
		else
			newPosition = m.getPosition();
		
		return new Pair<Double, Point>(newHeading, newPosition);
	}
	
	

	private Point moveTowards(Point subGoal, double heading, IMovable m) {
		double distance = Math.min(m.getMoveSpeed(), m.getPosition().euclideanDistance(m.getGoal()));
		
		return m.getPosition().moveAlongHeading(distance, heading);
	}

	
	private double newHeadingTowards(Point goal, IMovable m) {
		double newHeading;
		double deltaToTarget = m.getHeading() - m.getPosition().towards(m.getGoal());
		
		if (Math.abs(deltaToTarget) <= Math.abs(m.getTurnSpeed()))
			newHeading = m.getHeading() + deltaToTarget;
		else
			//heading = currHeading + turnSpeed + (delta/|delta|)
			newHeading = m.getHeading() + m.getTurnSpeed() * (deltaToTarget/Math.abs(deltaToTarget));
		
		return newHeading;
	}

}
