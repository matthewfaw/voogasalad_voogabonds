package engine.model.strategies.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import engine.model.game_environment.MapMediator;
import engine.model.strategies.IMovementStrategy;
import utility.ResouceAccess;

/**
 * A class to pick the right strategy from a given Strategy name
 * @author Weston
 *
 */
public class MovementStrategyFactory extends AbstractStrategyFactory<IMovementStrategy> {
	private static final String FOLDER_NAME = new String("movement");
	private MapMediator myMap;
	
	public MovementStrategyFactory(MapMediator map) {
		super(FOLDER_NAME);
		myMap = map;
	}

	public MapMediator getMap() {
		return myMap;
	}

	@Override
	protected IMovementStrategy constructStrategy(String strategyName) throws ClassNotFoundException {
		IMovementStrategy result;
		try {
			Class<?> strategyType = Class.forName(strategyName);
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
			throw new ClassNotFoundException(String.format(ResouceAccess.getError("BadStrategy"), strategyName), e);
		}
		return result;
	}

}
