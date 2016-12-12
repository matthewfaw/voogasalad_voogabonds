package engine.model.strategies;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import engine.model.game_environment.MapMediator;
import engine.model.strategies.damage.ExponentialDamageStrategy;
import utility.ResouceAccess;

/**
 * A class to pick the right strategy from a given Strategy name
 * @author Weston
 *
 */
public class StrategyFactory {
	
	private MapMediator myMap;
	public List<String> myMovementStrategies;
	
	public StrategyFactory(MapMediator map) {
		myMap = map;
		
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
	public IMovementStrategy movementStrategy(String movementStrategy) throws ClassNotFoundException {
		//TODO: Users see names from a resource file (resource key is class name)
		
		movementStrategy = String.format("engine.model.strategies.movement.%s", movementStrategy);
		
		IMovementStrategy result;
		try {
			Class<?> strategyType = Class.forName(movementStrategy);
			Constructor<?> construct = strategyType.getConstructor(this.getClass());
			result = (IMovementStrategy) construct.newInstance(this);
		} catch (
				ClassNotFoundException |
				NoSuchMethodException |
				InstantiationException |
				SecurityException |
				IllegalArgumentException |
				InvocationTargetException |
				IllegalAccessException e) {
			throw new ClassNotFoundException(String.format(ResouceAccess.getError("BadStrategy"), movementStrategy), e);
		}

		
		return result;
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
	 * @return null
	 */
	public ITargetingStrategy targetStrategy(String targetStrategy) {
		return null;
	}


	public MapMediator getMap() {
		return myMap;
	}

}
