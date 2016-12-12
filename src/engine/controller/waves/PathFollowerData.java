package engine.controller.waves;

import authoring.model.EntityData;
import utility.Point;

public class PathFollowerData {
	private EntityData myEntityData;
	private String mySpawnPoint;
	private String mySinkPoint;
	
	public PathFollowerData(EntityData entity, String start, String end) {
		myEntityData = entity;
		mySpawnPoint = start;
		mySinkPoint = end;
	}

	public EntityData getMyEntityData() {
		return myEntityData;
	}

	public String getSpawnPoint() {
		return mySpawnPoint;
	}

	public String getSinkPoint() {
		return mySinkPoint;
	}	
}
