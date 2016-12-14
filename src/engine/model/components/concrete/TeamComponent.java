package engine.model.components.concrete;

import java.util.ArrayList;

import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewableTeam;
import engine.model.entities.IEntity;
import gamePlayerView.gamePlayerView.Router;
import engine.model.systems.ISystem;
import engine.model.systems.TeamSystem;

/**
 * The purpose of this class is to manage which team an entity belongs to
 * This information could be used to determine which entities should be targeted
 * since you may not want to target an entity on your own team
 * @author matthewfaw
 *
 */
public class TeamComponent extends AbstractComponent implements IViewableTeam {
	private String myTeamID;
	
	@Hide
	private transient List<IObserver<IViewableTeam>> myObservers;

	@Hide
	private transient TeamSystem mySystem;
	
	public TeamComponent(IEntity aEntity, TeamSystem teams, ComponentData componentData, Router router) {
	    super(aEntity, router);
	    updateComponentData(componentData);
		myObservers = new ArrayList<IObserver<IViewableTeam>>();
		mySystem = teams;
		mySystem.attachComponent(this);
	}
	
	@Override
	public String getTeamID() {
		return myTeamID;
	}

	@Override
	public void distributeInfo() {
		getRouter().distributeViewableComponent(this);
	}
	
	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewableTeam> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewableTeam> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}
	
	public void delete() {
		mySystem.detachComponent(this);
	}
	@Override
	public void setSystems(List<ISystem<?>> aSystemList) {
		for (ISystem<?> system: aSystemList) {
			if (system instanceof TeamSystem) {
 				mySystem = (TeamSystem) system;
 				mySystem.attachComponent(this);
 			}
		}
	}
	@Override
	public void redistributeThroughRouter(Router aRouter) {
		super.setRouter(aRouter);
		myObservers = new ArrayList<IObserver<IViewableTeam>>();
		aRouter.distributeViewableComponent(this);
	}
	@Override
	public void updateComponentData(ComponentData aComponentData) {
		myTeamID = aComponentData.getFields().get("myTeamID");
	}
}
