package engine.model.machine;

public class Health implements IHealth{
	private double health;
	
	public Health(double initialHealth) {
		health = initialHealth;
	}
	
	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void updateHealth(IHealth deltaHealth) {
		health += deltaHealth.getHealth();
	}

	@Override
	public void setHealthAsDamage() {
		health *= -1;
	}

}
