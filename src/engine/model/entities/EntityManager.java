package engine.model.entities;

import java.util.HashMap;
import java.util.Map;

public class EntityManager implements IModifiableEntityManager {
	Map<Integer,IEntity> myEntityMap;

	public EntityManager() {
		myEntityMap = new HashMap<Integer, IEntity>();
	}
	
	public void addEntity(Integer id, IEntity entity) {
		myEntityMap.put(id, entity);
	}
	
	public Map<Integer, IEntity> getEntityMap() {
		return myEntityMap;
	}
}
