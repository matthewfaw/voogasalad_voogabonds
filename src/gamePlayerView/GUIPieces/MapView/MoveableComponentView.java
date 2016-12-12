package gamePlayerView.GUIPieces.MapView;

import engine.IObservable;

import engine.IObserver;
import engine.controller.ApplicationController;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewablePhysical;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MoveableComponentView extends ImageView implements IObserver<IViewablePhysical> {

	private Pane myPane;
	private ApplicationController myAppController;
	private IObservable<IViewablePhysical> myObservable;
	private String entityID;
	
    public MoveableComponentView(IObservable<IViewablePhysical> aObservable, ApplicationController aAppController, Pane pane, String id){

    	myAppController = aAppController;
    	myObservable = aObservable;
    	myPane = pane;
    	entityID = id;
    }	
    
    public String getEntityID() {
    	return entityID;
    }

	@Override
	public void update(IViewablePhysical aChangedObject) {
		
		//this.setOnMouseClicked(e -> myAppController.onEntitySelected(aChangedObject.getEntity()));
		String imagePath = aChangedObject.getImagePath();
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(aChangedObject.getImagePath().substring(4)));
		this.setImage(image);
		this.setX(aChangedObject.getPosition().getX() - aChangedObject.getSize() / 2);
		this.setY(aChangedObject.getPosition().getY() - aChangedObject.getSize() / 2);
		this.setFitWidth(aChangedObject.getSize());
		this.setFitHeight(aChangedObject.getSize());
		//this.setOnMouseClicked(e -> myAppController.onEntitySelected(aChangedObject.getEntity()));
		this.setRotate(aChangedObject.getHeading());
	}

	@Override
	public void remove(IViewablePhysical aRemovedObject) {
		myPane.getChildren().remove(this);
	}
}