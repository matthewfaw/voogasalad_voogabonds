package gamePlayerView.GUIPieces;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveableComponentView extends ImageView implements IObserver<IViewable> {

    public MoveableComponentView(IObservable<IViewable> aObservable){
    }

	@Override
	public void update(IViewable aChangedObject) {
		String imagePath = aChangedObject.getImagePath();
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(aChangedObject.getImagePath().substring(4)));
		this.setImage(image);
		this.setX(aChangedObject.getPosition().getY());
		this.setY(aChangedObject.getPosition().getX());
		this.setFitWidth(aChangedObject.getSize());
		this.setFitHeight(aChangedObject.getSize());
	}
}
