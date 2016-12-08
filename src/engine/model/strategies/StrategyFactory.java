package engine.model.strategies;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import engine.model.game_environment.paths.PathFactory;
import engine.model.strategies.damage.ExponentialDamageStrategy;
import engine.model.strategies.movement.GreedyMovementStrategy;
import engine.model.strategies.target.BadTargetStrategy;

/**
 * A class to pick the right strategy from a given Strategy name
 * @author Weston
 *
 */
public class StrategyFactory {
	
	private PathFactory myPathMaker;
	public List<String> myMovementStrategies;
	
	public StrategyFactory(PathFactory pathMaker) {
		myPathMaker = pathMaker;
		
		
		File[] folder = new File("src/engine/model/strategies/movement").listFiles();
		myMovementStrategies = Arrays.stream(folder)
											.map(e -> e.getName().replaceAll(".java", ""))
											.collect(Collectors.toList());
	}
	
	@Deprecated
	public StrategyFactory() {
		
		File[] folder = new File("src/engine/model/strategies/movement").listFiles();
		myMovementStrategies = Arrays.stream(folder)
											.map(e -> e.getName().replaceAll(".java", ""))
											.collect(Collectors.toList());
	}

	/**
	 * Gets a new instance of the movement strategy named movementStrategy.
	 * Currently only returns GreedyMovementStrategy.
	 * @param movementStrategy
	 * @return strategy with name movementStrategy
	 * @throws ClassNotFoundException 
	 */
	public IMovementStrategy movementStrategy(String movementStrategy, List<String> validTerrains) throws ClassNotFoundException {
		//TODO: Users see names from a resource file (resource key is class name)
		
		movementStrategy = String.format("engine.model.strategies.movement.%s", movementStrategy);
		
		IMovementStrategy result;
		try {
			Class<?> strategyType = Class.forName(movementStrategy);
			Constructor<?> construct = strategyType.getConstructor();
			result = (IMovementStrategy) construct.newInstance();
		} catch (
				ClassNotFoundException |
				NoSuchMethodException |
				InstantiationException |
				SecurityException |
				IllegalArgumentException |
				InvocationTargetException |
				IllegalAccessException e) {
			throw new ClassNotFoundException(String.format(/*ResouceAccess.getError("BadStrategy")*/ "Bad %s Strategy", movementStrategy), e);
		}

		
		return result;
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
	
	public static void main(String[] args) throws ClassNotFoundException {
		StrategyFactory f = new StrategyFactory();
		
		f.myMovementStrategies.stream().forEach(e -> System.out.println(e));
		f.movementStrategy("GreedyMovementStrategy", new ArrayList<String>());
	}

}
