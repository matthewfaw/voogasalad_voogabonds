package gamePlayerView.GUIPieces.MapView;

import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import engine.controller.ApplicationController;
import javafx.scene.Node;
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
		String imagePath = aChangedObject.getImagePath();
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(aChangedObject.getImagePath().substring(4)));
		this.setImage(image);
		this.setLayoutX(aChangedObject.getPosition().getX());
		this.setLayoutY(aChangedObject.getPosition().getY());
		this.setFitWidth(aChangedObject.getSize());
		this.setFitHeight(aChangedObject.getSize());
		
		//this.setOnMouseClicked(e -> myAppController.onEntitySelected(aChangedObject.getEntity()));
	}
}