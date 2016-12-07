package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
	private String selectedImagePath;
	private GridToolBar toolBar;
	private ScrollPane scrollPane;
	private TilePane imagePane;
	private int screenWidth;
	private VBox container;
	private Button confirmImage;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public ImageGallery(GridToolBar tBar, Stage tStage, String fPath) {
		setUpScreenResolution();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.scrollPane = new ScrollPane();
		this.terrainStage = tStage;
		this.toolBar = tBar;
		this.container = new VBox(screenWidth*0.01);
		imagePane = new TilePane();
		scrollPane.setContent(imagePane);
		this.filePath = fPath;
		this.confirmImage = new Button(myResources.getString("ApplyChanges"));
		container.getChildren().addAll(scrollPane, confirmImage);
		this.scene = new Scene(container);
		setUpPane();
		populatePane();
		terrainStage.setScene(scene);
		terrainStage.show();
		confirmImageHandler();
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
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
			TerrainImage image  = new TerrainImage(fileList[i].toURI().toString(), this);
			image.setFitWidth(screenWidth*0.1);
			image.setFitHeight(screenWidth*0.1);
			imagePane.getChildren().add(image);
		}
	}
	
	private void confirmImageHandler() {
		confirmImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				toolBar.setSelectedImagePath(selectedImagePath);
				terrainStage.close();
			}
		});
	}
	
	public void setSelectedImagePath(String newPath) {
		selectedImagePath = newPath;
	}
	
	public String getSelectedImagePath() {
		return selectedImagePath;
	}
	
}
