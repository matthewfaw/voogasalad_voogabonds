package authoring.controller;

import java.util.*;

import authoring.model.LevelData;

public class LevelDataContainer {
	
	private Map<String, LevelData> myLevelMap = new HashMap<String, LevelData>();
	
	public void createNewLevelData(LevelData ld){
		myLevelMap.put(ld.getLevelName(), ld);
	}
	
	public LevelData getLevelData(String name){
		return myLevelMap.get(name);
	}
	
	public Map<String, LevelData> finalizeLevelDataMap(){
		return myLevelMap;
	}

}
