package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * @author Christopher Lu
 * ImageGallery displays images in a grid based view by iterating through files and
 * placing imageviews of the files in a horizontal tile pane.
 */

public class ImageGallery {
	
	private Stage terrainStage;
	private Scene scene;
	private String filePath;
	private TilePane imagePane;
	private int screenWidth;
	private int screenHeight;
	
	public ImageGallery(Stage tStage, String fPath) {
		setUpScreenResolution();
		this.terrainStage = tStage;
		this.scene = new Scene(imagePane);
		this.filePath = fPath;
		imagePane = new TilePane();
		setUpPane();
		populatePane();
		terrainStage.setScene(scene);
		terrainStage.show();
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private void setUpPane() {
		imagePane.setHgap(screenWidth*0.001);
		imagePane.setPrefColumns(5);
	}
	
	private void populatePane() {
		File file = new File(filePath);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			Image image = new Image(fileList[i].toURI().toString());
			ImageView terrainPic = new ImageView();
			terrainPic.setFitWidth(screenWidth*0.005);
			terrainPic.setFitHeight(screenHeight*0.005);
			terrainPic.setImage(image);
			imagePane.getChildren().add(terrainPic);
		}
	}
	
}
