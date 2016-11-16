package engine.model.strategies;

import engine.model.machine.weapon.projectile.Projectile;
import javafx.util.Pair;
import utility.Point;

public interface IMovementStrategy {

	public Pair<Double, Point> nextMove(Projectile projectile);
}
