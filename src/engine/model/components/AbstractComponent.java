package engine.model.components;

import authoring.model.Hide;
import engine.model.entities.IEntity;
import gamePlayerView.gamePlayerView.Router;

abstract public class AbstractComponent implements IModifiableComponent{
	private IEntity myEntity;
	@Hide
	private Router myRouter;

	public AbstractComponent(Router router) {
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
}
