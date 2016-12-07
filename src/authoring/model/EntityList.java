package authoring.model;

import java.util.ArrayList;

import authoring.controller.Container;
import engine.IObserver;
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
