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
	private boolean hasEntityID;
	
    public MoveableComponentView(
    		IObservable<IViewablePhysical> aObservable, ApplicationController aAppController, Pane pane){
    	myAppController = aAppController;
    	myObservable = aObservable;
    	myPane = pane;
    	hasEntityID = false;
    }	
    
    public String getEntityID() {
    	return entityID;
    }
    
    public void setEntityID(String id) {
    	entityID = id;
    }

	@Override
	public void update(IViewablePhysical aChangedObject) {
		String imagePath = aChangedObject.getImagePath();
		if (imagePath != null) {
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(aChangedObject.getImagePath()));
			this.setImage(image);
			this.setX(aChangedObject.getPosition().getX() - aChangedObject.getSize() / 2);
			this.setY(aChangedObject.getPosition().getY() - aChangedObject.getSize() / 2);
			this.setFitWidth(aChangedObject.getSize());
			this.setFitHeight(aChangedObject.getSize());
			//this.setOnMouseClicked(e -> myAppController.onEntitySelected(aChangedObject.getEntity()));
			this.setRotate(aChangedObject.getHeading());
			if (aChangedObject.getEntityID() != null && !hasEntityID){
				setEntityID(aChangedObject.getEntityID());
				System.out.println("Setting entity ID");
				this.setOnMouseClicked(e -> myAppController.onEntityClicked(aChangedObject.getEntityID()));
				hasEntityID = true;
			}
		}
	}

	@Override
	public void remove(IViewablePhysical aRemovedObject) {
		myPane.getChildren().remove(this);
	}
}