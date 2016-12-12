package gamePlayerView.GUIPieces.MapView;

import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import engine.controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MoveableComponentView extends ImageView implements IObserver<IViewable> {

	private Pane myPane;
	
    public MoveableComponentView(IObservable<IViewable> aObservable, Pane aPane){
    	myPane = aPane;
    }	
    //public MoveableComponentView(IObservable<IViewable> aObservable) {
    	//
    //}

	@Override
	public void update(IViewable aChangedObject) {
		//String imagePath = aChangedObject.getImagePath();
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
	public void remove(IViewable aRemovedObject) {
		// TODO Auto-generated method stub
		myPane.getChildren().remove(this);
	}
}