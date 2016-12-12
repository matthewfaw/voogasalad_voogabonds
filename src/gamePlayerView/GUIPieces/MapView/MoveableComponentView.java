package gamePlayerView.GUIPieces.MapView;

import engine.IObservable;

import engine.IObserver;
import engine.controller.ApplicationController;
import engine.model.components.viewable_interfaces.IViewable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveableComponentView extends ImageView implements IObserver<IViewable> {

	private ApplicationController myAppController;
	
    public MoveableComponentView(IObservable<IViewable> aObservable, ApplicationController aAppController){
    	myAppController = aAppController;
    }	
    //public MoveableComponentView(IObservable<IViewable> aObservable) {
    	//
    //}

	@Override
	public void update(IViewable aChangedObject) {
//		String imagePath = aChangedObject.getImagePath();
//		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(aChangedObject.getImagePath().substring(4)));
//		this.setImage(image);
//		this.setX(aChangedObject.getPosition().getX() - aChangedObject.getSize() / 2);
//		this.setY(aChangedObject.getPosition().getY() - aChangedObject.getSize() / 2);
//		this.setFitWidth(aChangedObject.getSize());
//		this.setFitHeight(aChangedObject.getSize());
//		//this.setOnMouseClicked(e -> myAppController.onEntitySelected(aChangedObject.getEntity()));
//		this.setRotate(aChangedObject.getHeading());
	}
}