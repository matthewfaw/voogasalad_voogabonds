package engine.model.data_stores;

import java.util.HashMap;
import java.util.List;

import authoring.model.IReadableData;

/**
 * A class that encapulates the data for all available objects of type T
 * Provides methods for retrieving a data object
 * @author matthewfaw
 *
 *@param T: any class that implements the IReadableData interface
 */
public class DataStore<T extends IReadableData> {
	private HashMap<String, T> myWeaponData;

	public DataStore(List<T> aWeaponDataList) {
		myWeaponData = new HashMap<String, T>();
		aWeaponDataList.forEach(e -> myWeaponData.put(e.getName(), e));
	}
	
	public T getData(String aWeaponName) {
		if (myWeaponData.containsKey(aWeaponName)) {
			return myWeaponData.get(aWeaponName);
		} else {
			return null;
		}
	}
	
	public boolean hasKey(String aWeaponName) {
		return myWeaponData.containsKey(aWeaponName);
	}
}