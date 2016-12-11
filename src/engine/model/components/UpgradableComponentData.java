package engine.model.components;

import java.util.HashMap;
import java.util.Map;

public class UpgradableComponentData {
	private Map<String, Integer> myUpgradesMap;
	
	public UpgradableComponentData()
	{
		myUpgradesMap = new HashMap<String, Integer>();
	}
	
	public Map<String, Integer> getUpgrades()
	{
		return myUpgradesMap;
	}

}
