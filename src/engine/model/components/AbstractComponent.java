package engine.model.components;

import java.util.List;

import authoring.model.ComponentData;
import authoring.model.EntityData;
import authoring.model.Hide;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityManager;
import engine.model.entities.IEntity;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;

abstract public class AbstractComponent implements IModifiableComponent, IViewable {

	@Hide
	private transient IEntity myEntity;
	@Hide
	private String myEntityID;
	@Hide
	private transient Router myRouter;

	public AbstractComponent(IEntity aEntity, Router router) {
		myEntity = aEntity;
		myEntityID = aEntity.getId();
		setRouter(router);
	}
	
	public abstract void setSystems(List<ISystem<?>> aSystemList);
	
	public void setRouter(Router aRouter)
	{
		myRouter = aRouter;
	}
	public abstract void redistributeThroughRouter(Router aRouter);
	
	public abstract void updateComponentData(ComponentData aComponentData);
	
	public void reconnectToEntity(EntityManager aEntityManager)
	{
		myEntity = aEntityManager.getEntity(myEntityID);
		myEntity.addComponent(this);
	}
	
	protected Router getRouter() {
		return myRouter;
	}
	
	@Override
	public IEntity getEntity() {
		return myEntity;
	}

	@Override
	public void setEntity(IEntity e) {
		myEntity = e;
	}
	
	@Override
	public String getEntityID() {
		return getEntity() != null ? getEntity().getId() : null;
	}
}
