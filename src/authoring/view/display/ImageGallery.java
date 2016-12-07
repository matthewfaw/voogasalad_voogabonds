package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
	private ScrollPane scrollPane;
	private TilePane imagePane;
	private int screenWidth;
	private int screenHeight;
	
	public ImageGallery(Stage tStage, String fPath) {
		setUpScreenResolution();
		this.scrollPane = new ScrollPane();
		this.terrainStage = tStage;
		imagePane = new TilePane();
		scrollPane.setContent(imagePane);
		this.scene = new Scene(scrollPane);
		this.filePath = fPath;
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
		imagePane.setHgap(screenWidth*0.01);
		imagePane.setPrefColumns(5);
	}
	
	private void populatePane() {
		String relativePath = new File("").getAbsolutePath();
		String finalPath = relativePath.concat(filePath);
		File file = new File(finalPath);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			Image image = new Image(fileList[i].toURI().toString());
			ImageView terrainPic = new ImageView();
			terrainPic.setFitWidth(screenWidth*0.1);
			terrainPic.setFitHeight(screenWidth*0.1);
			terrainPic.setImage(image);
			imagePane.getChildren().add(terrainPic);
		}
	}
	
}
