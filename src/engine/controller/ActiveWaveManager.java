package engine.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import authoring.model.EnemyData;
import authoring.model.WaveData;
import engine.model.data_stores.DataStore;

/**
 * A class intended to manage which waves are currently active
 * Provides a simple interface to retrieve which Enemies to be constructed
 * at a given time step 
 * @author matthewfaw
 *
 */
public class ActiveWaveManager {
	private static final double DEFAULT_START_TIME = 0.0;

	private DummyWaveOperationData myWaveOperationData;
	private DataStore<EnemyData> myEnemyDataStore;
	
	private LinkedHashMap<WaveData, Integer> myCurrentlyActiveWaves;
	private double myCurrentTime;
	private double myTimeToAddMoreWaves;
	
	public ActiveWaveManager(DummyWaveOperationData aWaveOperationData, DataStore<EnemyData> aEnemyDataStore)
	{
		myWaveOperationData = aWaveOperationData;
		myEnemyDataStore = aEnemyDataStore;

		myCurrentlyActiveWaves = new LinkedHashMap<WaveData, Integer>();
		
		setCurrentTime(DEFAULT_START_TIME);
		setNextRoundOfWaveDataAsActive();
	}
	
	/**
	 * A method that returns the Enemies to construct, given a current time
	 * @param aTotalTimeElapsed
	 * @return a map from enemy data to the spawn point corresponding to that enemy
	 * 
	 * IMPORTANT TODO: add support for frequency of spawning
	 * TODO: This return type is kinda hacky... maybe make a custom class for this?
	 */
	public Map<EnemyData, String> getEnemiesToConstruct(double aTotalTimeElapsed)
	{
		//1. Update the current time
		setCurrentTime(aTotalTimeElapsed);

		//2. dispatch next waves, if it's time
		if (isTimeToAddMoreWaves()) {
			setNextRoundOfWaveDataAsActive();
		}
		
		//3. get all the enemies
		Map<EnemyData, String> enemiesToConstruct = new HashMap<EnemyData, String>();
		for (WaveData activeWave: myCurrentlyActiveWaves.keySet()) {
			if (myCurrentlyActiveWaves.get(activeWave) > 0) {
				EnemyData enemy = myEnemyDataStore.getData(activeWave.getWaveEnemy());
				enemiesToConstruct.put(enemy, activeWave.getSpawnPointName());
				myCurrentlyActiveWaves.put(activeWave, myCurrentlyActiveWaves.get(activeWave) - 1);
			} else {
				myCurrentlyActiveWaves.remove(activeWave);
			}
		}
		
		return enemiesToConstruct;
	}

	/**
	 * A method to add the next round of wave data to be set as active
	 * Assumes that multiple waves can be active at the same time
	 */
	private void setNextRoundOfWaveDataAsActive()
	{
		while(true) {
			WaveData waveData = myWaveOperationData.pop();
			if (waveData != null) {
				myCurrentlyActiveWaves.put(waveData, waveData.getNumEnemies());
				if (waveData.getTimeForWave() != 0) {
					updateTimeOfNextTransition(waveData.getTimeForWave());
					break;
				}
			} else {
				break;
			}
		}
	}
	
	/**
	 * A method to determine if we can set more waves as active
	 * @return true if we can, false otherwise
	 */
	private boolean isTimeToAddMoreWaves()
	{
		return myCurrentTime > myTimeToAddMoreWaves;
	}
	
	/**
	 * Sets the current time to the input value
	 * Note that the time is specified in milliseconds
	 * @param aTotalTimeElapsed: a new current time
	 */
	private void setCurrentTime(double aTotalTimeElapsed)
	{
		myCurrentTime = aTotalTimeElapsed;
	}
	/**
	 * sets the time until the next wave is to be added, relative
	 * to the current time
	 * @param aDeltaTime: a wave duration
	 */
	private void updateTimeOfNextTransition(double aDeltaTime)
	{
		myTimeToAddMoreWaves = myCurrentTime + aDeltaTime;
	}
}
