package engine.model.entities;

public interface IModifiableEntityManager {
	public void putEntity(String id, ConcreteEntity entity);
	public void removeEntity(String id);
}
