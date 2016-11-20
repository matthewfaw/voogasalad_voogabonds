package engine.model.strategies;

public class StrategyFactory {
	

	public static IMovementStrategy movementStrategy(String movementStrategy) {
		return new GreedyMovementStrategy();
	}

	public static IDamageStrategy damageStrategy(String damageStrategy) {
		return new ExponentialDamageStrategy();
	}
	
	public static ITargetStrategy targetStrategy(String movementStrategy) {
		return new BadTargetStrategy();
	}

}
