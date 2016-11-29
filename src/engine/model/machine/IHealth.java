package engine.model.machine;

/**
 * Wrapper interface for health
 *
 */
public interface IHealth {
	public double getHealth();
	public void updateHealth(IHealth deltaHealth);
	public void setHealthAsDamage(); //negates value of health
}