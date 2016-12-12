package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObservable;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewableTeam;

/**
 * The purpose of this class is to manage which team an entity belongs to
 * This information could be used to determine which entities should be targeted
 * since you may not want to target an entity on your own team
 * @author matthewfaw
 *
 */
public class TeamComponent extends AbstractComponent implements IViewableTeam{
	private String myTeamID;
	
	@Hide
	private List<IObserver<IViewable>> myObservers;
	
	public TeamComponent(ComponentData componentData) {
		myTeamID = componentData.getFields().get("myTeamID");
		myObservers = new ArrayList<IObserver<IViewable>>();
	}
	
	@Override
	public String getTeamID()
	{
		return myTeamID;
	}

	@Override
	public void distributeInfo() {
		// TODO Auto-generated method stub
		
	}
	
	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}
	

}
