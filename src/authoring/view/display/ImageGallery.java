package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
	private ListView<TerrainImage> imagePane;
	private int screenWidth;
	private VBox container;
	private Button confirmImage;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public ImageGallery(String fPath) {
		setUpScreenResolution();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.scrollPane = new ScrollPane();
		this.terrainStage = new Stage();
		this.container = new VBox();
		container.setId("vbox");
		imagePane = new ListView<TerrainImage>();
		imagePane.setOrientation(Orientation.HORIZONTAL);
		imagePane.setId("background");
		imagePane.setMaxHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
		imagePane.setMinWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		this.filePath = fPath;
		this.confirmImage = new Button(myResources.getString("ApplyChanges"));
		confirmImage.setId("button");
		container.getChildren().addAll(imagePane, confirmImage);
		this.scene = new Scene(container);
		scene.getStylesheets().add("style.css");
		populatePane();
		terrainStage.setScene(scene);
		terrainStage.show();
		confirmImageHandler();
	}
	
	public ImageGallery(GridToolBar tBar, String fPath) {
		this(fPath);
		this.toolBar = tBar;
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
	}
	
	private void populatePane() {
		String path = myResources.getString("TerrainImageFilePath").substring(1);
		File file = new File(path);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			TerrainImage image  = new TerrainImage(fileList[i].toURI().toString(), this);
			image.setFitWidth(screenWidth*0.1);
			image.setFitHeight(screenWidth*0.1);
			imagePane.getItems().add(image);
		}
	}
	
	private void confirmImageHandler() {
		confirmImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					selectedImagePath = imagePane.getSelectionModel().getSelectedItem().getImagePath();
					toolBar.setSelectedImagePath(selectedImagePath);
					terrainStage.close();
					//System.out.println("HELLO1");
				} catch (Exception e1) {
					//TODO: ImageSplitter stuff
					//System.out.println("HELLO2");
					terrainStage.close();
				}
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
