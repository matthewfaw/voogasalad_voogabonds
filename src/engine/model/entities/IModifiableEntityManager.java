package engine.model.entities;

public interface IModifiableEntityManager {
	public void addEntity(String id, IEntity entity);
	public void removeEntity(String id);
}
