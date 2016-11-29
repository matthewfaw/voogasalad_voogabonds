package engine.model.machine;

public class Health implements IHealth{
	private int health;
	
	public Health(int initialHealth) {
		health = initialHealth;
	}
	
	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void updateHealth(IHealth deltaHealth) {
		health += deltaHealth.getHealth();
	}

}
