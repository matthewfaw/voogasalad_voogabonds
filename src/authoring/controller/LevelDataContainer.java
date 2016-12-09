package authoring.controller;

import java.util.*;

import authoring.model.LevelData;

public class LevelDataContainer {
	
	private List<LevelData> myLevelList;
	
	public LevelDataContainer()
	{
		myLevelList = new ArrayList<LevelData>();
	}
	
	/**
	 * 
	 * @param LevelData
	 * Adds new LevelData object to list of LevelData
	 */
	public void createNewLevelData(LevelData ld){
		myLevelList.add(ld);
	}
	
	/**
	 *@param String: Level Name
	 *Returns specific level by input of its specified name 
	 */
	public LevelData getLevelData(String name){
		for (LevelData LD : myLevelList){
			if (LD.getLevelName().equals(name)){
				return LD;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return List<LevelData>
	 * Returns current list of all levels
	 */
	public List<LevelData> finalizeLevelDataMap(){
		return myLevelList;
	}

}
