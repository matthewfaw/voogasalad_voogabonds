package engine.model.strategies;

import java.util.List;

import engine.model.game_environment.paths.PathFactory;
import engine.model.strategies.damage.ExponentialDamageStrategy;
import engine.model.strategies.movement.GreedyMovementStrategy;
import engine.model.strategies.movement.PathMovementStrategy;
import engine.model.strategies.target.BadTargetStrategy;

/**
 * A class to pick the right strategy from a given Strategy name
 * @author Weston
 *
 */
public class StrategyFactory {
	
	private PathFactory myPathMaker;
	
	public StrategyFactory(PathFactory pathMaker) {
		myPathMaker = pathMaker;
	}

	/**
	 * Gets a new instance of the movement strategy named movementStrategy.
	 * Currently only returns GreedyMovementStrategy.
	 * @param movementStrategy
	 * @return strategy with name movementStrategy
	 */
	public IMovementStrategy movementStrategy(String movementStrategy, List<String> validTerrains) {
		switch (movementStrategy.toLowerCase()) {
		case "greedy":
			return new GreedyMovementStrategy();
		case "path":
			return new PathMovementStrategy(myPathMaker.constructPaths(validTerrains));
		}
		return new GreedyMovementStrategy();
	}
	
	public static IMovementStrategy movementStrategy(String movementStrategy) {
		switch (movementStrategy.toLowerCase()) {
		case "greedy":
			return new GreedyMovementStrategy();
		}
		return new GreedyMovementStrategy();
	}

	/**
	 * Gets a new instance of the damage strategy named damageStrategy.
	 * Currently only returns ExponentialDamageStrategy.
	 * @param damageStrategy
	 * @return ExponentialDamageStrategy
	 */
	public static IDamageStrategy damageStrategy(String damageStrategy) {
		return new ExponentialDamageStrategy();
	}
	
	/**
	 * Gets a new instance of the target strategy named targetStrategy.
	 * Currently only returns BadTargetStrategy.
	 * @param targetStrategy
	 * @return BadTargetStrategy
	 */
	public static ITargetStrategy targetStrategy(String targetStrategy) {
		return new BadTargetStrategy();
	}

}
