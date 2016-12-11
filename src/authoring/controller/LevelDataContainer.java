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

	/**
	 * get the level data corresponding to the specified level
	 * 
	 * This method assumes that the requested level exists
	 * 
	 * @param aLevel
	 * @return the level data at the specified index, if it exists
	 */
	public LevelData getLevelData(int aLevel) {
		return myLevelList.get(aLevel);
	}
	
	/**
	 * A method that returns whether a level at the specified index exists
	 * @param aLevel
	 * @return true if aLevel is within the level list
	 */
	public boolean hasLevel(int aLevel)
	{
		return aLevel < myLevelList.size();
	}

}
