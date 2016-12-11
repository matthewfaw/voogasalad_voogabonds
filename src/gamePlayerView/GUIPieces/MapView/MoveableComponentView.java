package gamePlayerView.GUIPieces.MapView;

import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveableComponentView extends ImageView implements IObserver<IViewable> {

    public MoveableComponentView(IObservable<IViewable> aObservable) {
    	aObservable.attach(this);
    }

	@Override
	public void update(IViewable aChangedObject) {
		String imagePath = aChangedObject.getImagePath();
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(aChangedObject.getImagePath().substring(4)));
		this.setImage(image);
		this.setX(aChangedObject.getPosition().getX());
		this.setY(aChangedObject.getPosition().getY());
		this.setFitWidth(aChangedObject.getSize());
		this.setFitHeight(aChangedObject.getSize());
	}
}
