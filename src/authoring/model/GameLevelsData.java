package authoring.model;

import java.util.ArrayList;
import java.util.List;
/**
 * This class wraps different levels of waves
 * @author owenchung
 *
 */
public class GameLevelsData {
	
	private List<OneLevelData> WavesInLevel;
	
	public GameLevelsData() {
		WavesInLevel = new ArrayList<OneLevelData>();
	}
	
	public void addGameLevels(OneLevelData aLevelData) {
		WavesInLevel.add(aLevelData);
	}
	
	public OneLevelData getLevelData(int currLevel) {
		return WavesInLevel.get(currLevel);
	}
	
}
