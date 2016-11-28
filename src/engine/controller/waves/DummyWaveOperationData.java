package engine.controller.waves;

import java.util.Stack;

import authoring.model.IReadableData;
import authoring.model.WaveData;

/**
 * a temporary class used to mock what is expected from the authoring peeps
 * to give me access to their WaveData
 * 
 * @author matthewfaw
 *
 */
@Deprecated
public class DummyWaveOperationData implements IReadableData {
	private Stack<WaveData> myWaveData;
	
	public WaveData pop()
	{
		if (!myWaveData.isEmpty()) {
			return myWaveData.pop();
		} else {
			return null;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
