package authoring.view.objects;

import java.util.Map;

import authoring.authoring_interfaces.IWave;

public class FrontEndWave implements IWave {
	
	private int myNumber;
	private Map<String, Integer> myEnemyNames;
	private String myPattern;
	
	public FrontEndWave(int number, Map<String, Integer> enemyNames, String pattern){
		myNumber = number;
		myEnemyNames = enemyNames;
		myPattern = pattern;
	}

	@Override
	public int getWaveNumber() {
		return myNumber;
	}

	@Override
	public Map<String, Integer> getWaveEnemyNames() {
		return myEnemyNames;
	}

	@Override
	public String getWavePattern() {
		return myPattern;
	}

}
