package engine.model.entities;

public interface IModifiableEntityManager {
	public void addEntity(Integer id, IEntity entity);
	public void removeEntity(Integer id);
}
