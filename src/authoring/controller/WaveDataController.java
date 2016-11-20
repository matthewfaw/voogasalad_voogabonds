package authoring.controller;
import java.util.AbstractMap;
import authoring.model.WaveData;
import authoring.view.objects.FrontEndWave;

public class WaveDataController {
	private AbstractMap<String, WaveData> myWaveMap;
	
	public void createNewWave(FrontEndWave wave){
		try{
			//Create WaveData object here
		}
		catch(Exception e){
			//Call front-end error handler;
		}
	}
}
