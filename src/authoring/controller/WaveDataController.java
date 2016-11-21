package authoring.controller;
import java.util.LinkedHashMap;
import authoring.model.WaveData;

public class WaveDataController {
	/**
	 * HashMap which maintains insertion order. Insertion order is important because
	 * waves all have an attribute 'timeBeforeWave', which means that they are
	 * relative to what comes before them. As a result, we need to make sure that
	 * waves are in the map in the order in which they were created.
	 */
	private LinkedHashMap<String, WaveData> myWaveMap;
	
	public void createNewWave(String name, WaveData wave){
		try{
			//Create WaveData object here
			//Add to wave
		}
		catch(Exception e){
			//Call front-end error handler;
		}
	}
	
	public WaveData getWaveData(String name){
		return myWaveMap.get(name);
	}
	
	/**
	 * The function is this complex because it's impossible to update the key of a
	 * LinkedHashMap (which is necessary if the name gets updated). As a result, 
	 * we need to go through the map again and copy over all old values, making sure to put 
	 * it in the same order.
	 * @param wave
	 * @param originalName
	 */
	public void updateWave(WaveData wave, String originalName){
		if (originalName.equals(wave.getName())){
			//Find original WaveData object, simply update it
		}else{
			LinkedHashMap<String, WaveData> newMap = new LinkedHashMap<String, WaveData>();
			for (String name: myWaveMap.keySet()){
				if (name.equals(originalName)){
					//TODO: Put new object with potentially new name into newMap
				}else{
					//Put same object into newMap
					newMap.put(name, myWaveMap.get(name));
				}
			}
			myWaveMap = newMap;
		}
	}
}
