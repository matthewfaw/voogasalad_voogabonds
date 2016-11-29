package authoring.controller;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.ArrayList;

import authoring.model.EnemyData;
import authoring.model.WeaponData;
import engine.IObservable;
import engine.IObserver;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class WeaponDataController extends Controller implements IObservable<Controller>{

	private AbstractMap<String, WeaponData> myWeaponDataMap = new HashMap<String, WeaponData>();
	private ArrayList<IObserver<Controller>> myListeners = new ArrayList<IObserver<Controller>>();

	public AbstractMap<String, WeaponData> finalizeWeaponDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myWeaponDataMap;
	}
	
	public void createWeaponData(WeaponData weaponData){
		myWeaponDataMap.put(weaponData.getName(), weaponData);
	}
	
	public WeaponData getWeaponData(String weaponName){
		return myWeaponDataMap.get(weaponName);
	}
	
	public void updateWeaponData(String originalName, WeaponData updatedWeapon){
		myWeaponDataMap.remove(originalName);
		createWeaponData(updatedWeapon);
	}
	
	public void attach(IObserver<Controller> listener){
		myListeners.add(listener);
	}
	
	public void detach(IObserver<Controller> listener){
		myListeners.remove(listener);
	}
	
	public void notifyObservers(){
		for (IObserver observer: myListeners){
			observer.update(this);
		}
	}
	
	
}