package engine.model.strategies.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import engine.model.strategies.ISpawningStrategy;
import utility.ResouceAccess;

public class SpawningStrategyFactory extends AbstractStrategyFactory<ISpawningStrategy> {
	private static final String FOLDER_NAME = new String("spawning");
	
	public SpawningStrategyFactory() {
		super(FOLDER_NAME);
	}

	@Override
	protected ISpawningStrategy constructStrategy(String strategyName) throws ClassNotFoundException {
		ISpawningStrategy result;
		try {
			Class<?> strategyType = Class.forName(strategyName);
			Constructor<?> construct = strategyType.getConstructor(this.getClass());
			result = (ISpawningStrategy) construct.newInstance(this);
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