package engine.model.entities;

public interface IModifiableEntityManager {
	public void addEntity(Integer id, ConcreteEntity entity);
	public void removeEntity(Integer id);
}
