package engine.model.components;

import authoring.model.Hide;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.entities.IEntity;
import gamePlayerView.gamePlayerView.Router;

abstract public class AbstractComponent implements IModifiableComponent, IViewable {

	private transient IEntity myEntity;
	@Hide
	private Router myRouter;

	public AbstractComponent(IEntity aEntity, Router router) {
		myEntity = aEntity;
		myRouter = router;
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
