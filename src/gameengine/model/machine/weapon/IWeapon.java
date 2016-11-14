package gameengine.machine;

public interface IWeapon {
	public void fire(Machine target, double initialDirectionRadians, Point initialLocation);
	public IProjectile getProjectile();
}
