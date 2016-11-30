package gamePlayerView.GUIPieces;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveableComponentView extends ImageView{
    private double myX;
    private double myY;
    private double myHeight;
    private double myWidth;
    private ImageView myImage;
    
    
    
    public MoveableComponentView(){
        myX = 0;
        myY = 0;
        myHeight = 25;
        myWidth = 25;
        myImage = new ImageView();
    }

    
}
