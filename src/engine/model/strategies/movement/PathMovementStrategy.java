package engine.model.strategies.movement;

import java.util.Random;

import engine.model.game_environment.paths.PathManager;
import engine.model.strategies.AbstractMovementStrategy;
import engine.model.strategies.IMovable;
import engine.model.strategies.IPhysical;
import javafx.util.Pair;
import utility.Point;

public class PathMovementStrategy extends AbstractMovementStrategy{
	private static final Random RANDOM = new Random();
	private PathManager myPath;
	
	@Override
	public  Pair<Double, Point> nextMove(IMovable m, IPhysical p) {
		if (myPath == null) {
			myPath = m.getPath();
		}
		return super.nextMove(m, p);
	}
	
	@Override
	protected Pair<Double, Point> nextMoveNoGoal(IMovable m, IPhysical p) {
		//Spins around aimlessly
		return new Pair<Double, Point>(p.getHeading() + RANDOM.nextDouble() * m.getTurnSpeed(), p.getPosition());
	}

	@Override
	protected Pair<Double, Point> nextMoveWithGoal(IMovable m, IPhysical p) {
		Point subGoal = myPath.getNextVertex(p.getPosition());
		
		//Check if we aren't on the path anymore
		if (subGoal == null)
			return nextMoveNoGoal(m, p);
		
		double newHeading = newHeadingTowards(subGoal, m, p);
		
		Point newPosition;
		if (p.getPosition().towards(subGoal) == newHeading)
			newPosition = moveTowards(subGoal, newHeading, m, p);
		else
			newPosition = p.getPosition();
		
		return new Pair<Double, Point>(newHeading, newPosition);
	}
	
	

	private Point moveTowards(Point subGoal, double heading, IMovable m, IPhysical p) {
		double distance = Math.min(m.getMoveSpeed(), p.getPosition().euclideanDistance(subGoal));
		
		return p.getPosition().moveAlongHeading(distance, heading);
	}

	
	private double newHeadingTowards(Point goal, IMovable m, IPhysical p) {
		double newHeading;
		double deltaToTarget = p.getHeading() - p.getPosition().towards(m.getGoal());
		
		if (Math.abs(deltaToTarget) <= Math.abs(m.getTurnSpeed()))
			newHeading = p.getHeading() + deltaToTarget;
		else
			//heading = currHeading + turnSpeed + (delta/|delta|)
			newHeading = p.getHeading() + m.getTurnSpeed() * (deltaToTarget/Math.abs(deltaToTarget));
		
		return newHeading;
	}

}
