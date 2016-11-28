package authoring.model;

import java.util.List;
import java.util.Stack;

public class LevelData {
	
	private WaveData waveData;
	private List<WaveData> waveDataList;
	private Stack<List<WaveData>> levelData;
	
	public void setWaveData(WaveData waveData){
		this.waveData = waveData;
	}
	
	public WaveData getWaveData(){
		return waveData;
	}
	
	public void addWaveDataListToList(WaveData waveData){
		this.waveDataList.add(waveData);
	}
	
	public List<WaveData> getWaveDataList(){
		return this.waveDataList;
	}
	
	public void removeWaveDataFromList(WaveData waveData){
		this.waveDataList.remove(waveData);
	}
	
	public void clearCurrentWaveData(){
		this.waveDataList.clear();
	}
	
	public void setWaveListInLevel(List<WaveData> waveDataList){
		levelData.push(waveDataList);
	}
	
	public List<WaveData> getWaveListInLevel(){
		return levelData.peek();
	}
	
	public void clearLevelData(){
		levelData.clear();
	}
	
	public Stack<List<WaveData>> getLevelData(){
		return this.levelData;
	}

}
