package engine.model.machine;
import utility.Damage;
public class Health {
	private double myMaxHealth;
	private double myCurrHealth;
	
	public Health(double initialHealth, double maxHealth) {
		myCurrHealth = initialHealth;
		myMaxHealth = maxHealth;
	}
	
	public Health(double initialHealth) {
		this(initialHealth, initialHealth);
	}
	
	public double getHealth() {
		return myCurrHealth;
	}
	public double takeDamage(Damage dmg) {
		double startingHealth = myCurrHealth;
		myCurrHealth -= dmg.getDamage();
		
		if (myCurrHealth < 0) {
			myCurrHealth = 0;
		}
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
		
		return myCurrHealth - startingHealth;
	}
}