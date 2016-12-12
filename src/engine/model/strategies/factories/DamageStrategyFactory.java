package engine.model.strategies.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import engine.model.strategies.IDamageStrategy;
import utility.ResouceAccess;

public class DamageStrategyFactory extends AbstractStrategyFactory<IDamageStrategy> {
	private static final String FOLDER_NAME = new String("damage");

	public DamageStrategyFactory() {
		super(FOLDER_NAME);
	}

	@Override
	protected IDamageStrategy constructStrategy(String strategyName) throws ClassNotFoundException {
		IDamageStrategy result;
		try {
			Class<?> strategyType = Class.forName(strategyName);
			Constructor<?> construct = strategyType.getConstructor(this.getClass());
			result = (IDamageStrategy) construct.newInstance(this);
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