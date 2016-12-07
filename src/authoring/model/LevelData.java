package authoring.model;

import java.util.*;
import java.util.Stack;

public class LevelData {
	
	private String name;
	private List<WaveData> waveDataList = new ArrayList<WaveData>();
	
	public void setLevelName(String name){
		this.name = name;
	}
	
	public String getLevelName(){
		return name;
	}
	
	public void addWaveDataListToList(WaveData waveData){
		this.waveDataList.add(waveData);
	}
	
	public List<WaveData> getWaveDataList(){
		return waveDataList;
	}
	
	public void removeWaveDataFromList(WaveData waveData){
		this.waveDataList.remove(waveData);
	}
	
	public void clearCurrentWaveData(){
		this.waveDataList.clear();
	}

}
