package authoring.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import authoring.controller.Container;
import engine.IObserver;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import engine.IObservable;

public class EntityList implements IObservable<EntityList>{
	ArrayList<EntityData> myEntities = new ArrayList<EntityData>();
	ArrayList<IObserver<EntityList>> myObservers = new ArrayList<IObserver<EntityList>>();
	
	
	public void addEntity(EntityData ed){
		for (String componentName: ed.getComponents().keySet()){
			if (componentName.equals("DamageDealing")){
				notifyObservers();
			}
		}
		myEntities.add(ed);
	}
	
	public void removeEntity(EntityData ed){
		for (String componentName: ed.getComponents().keySet()){
			if (componentName.equals("DamageDealing")){
				notifyObservers();
			}
		}
		myEntities.remove(ed);
	}
	
	public EntityData getEntity(String name){
		for (EntityData ed: myEntities){
			if (ed.getName().equals(name)){
				return ed;
			}
		}
		return null;
	}
	
	public boolean contains(String name){
	        for (EntityData ed: myEntities) {
	                if (ed.getName().equals(name)){
	                        return true;
	                }
	        }
	        return false;
	}
	
	public int indexOf(String name){
	        for (EntityData ed: myEntities){
	                if (ed.getName().equals(name)){
	                        return myEntities.indexOf(ed);
	                }
	        }
	        return -1;
	}
	
	public boolean set(String oldName, EntityData newData){
	        for (EntityData ed: myEntities) {
	                if (ed.getName().equals(oldName)){
	                        myEntities.set(myEntities.indexOf(ed), newData);
	                        return true;
	                }
	        }
	        return false;
	}
	
	
	/**
	 * IObservable functions
	 */
	public void attach(IObserver<EntityList> observer){
		myObservers.add(observer);
	}
	
	public void detach(IObserver<EntityList> observer){
		myObservers.remove(observer);
	}
	
	public void notifyObservers(){
		for (IObserver<EntityList> observer: myObservers){
			observer.update(this);
		}
	}
}
