package mainmenu.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
import authoring.controller.Router;
import authoring.model.serialization.GameStateSerializer;
import authoring.model.serialization.JSONSerializer;
import authoring.view.display.AuthorDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.MainInitializer;

/**
 * @author Christopher Lu
 * User is presented with this screen after selecting to build a new game. Here, the user will choose the save destination, name, and map dimension of the authoring environment.
 */

public class NewAuthoringScreen {
	
	private Scene scene;
	private Stage stage;
	private MainInitializer initializer;
	private BorderPane pane;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private int screenWidth;
	private int screenHeight;
	private int mapXDim;
	private int mapYDim;
	private String authoringName;
	private Router router;
	
	public NewAuthoringScreen(Router r) throws IOException {
		setUpScreenResolution();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.stage = new Stage();
		this.router = r;
		stage.setTitle(myResources.getString("SetUpNewAuthoringTitle"));
		this.pane = new BorderPane();
		this.scene = new Scene(pane);
		scene.getStylesheets().add("style.css");
		pane.setId("background");
		populatePane();
		stage.setScene(scene);
		stage.show();
	}
	
	private void populatePane() {
		VBox authoringOptions = new VBox(screenHeight*0.1);
		authoringOptions.setId("menu-vbox");
		Button chooseNameSave = new Button(myResources.getString("ChooseNameSave"));
		chooseNameSave.setId("button");
		saveHandler(chooseNameSave);
		HBox mapDimensionContainer = new HBox(screenWidth*0.05);
		TextField mapXField = new TextField();
		mapXField.setMinHeight(47);
		mapXField.setPromptText(myResources.getString("SetMapX"));
		TextField mapYField = new TextField();
		mapYField.setMinHeight(47);
		mapYField.setPromptText(myResources.getString("SetMapY"));
		Button startProject = new Button(myResources.getString("ConfirmAuthoringSetUp"));
		startProject.setId("button");
		startProjectHandler(startProject, mapXField, mapYField);
		mapDimensionContainer.getChildren().addAll(mapXField, mapYField, startProject);	
		authoringOptions.getChildren().addAll(chooseNameSave, mapDimensionContainer);
		authoringOptions.setPadding(new Insets(screenHeight*0.01, screenWidth*0.01, screenHeight*0.01, screenWidth*0.01));
		pane.setCenter(authoringOptions);
	}
	
	private void saveHandler(Button button) {
		button.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						FileChooser newGameSave = new FileChooser();
						Stage stage = new Stage();
						File file = newGameSave.showSaveDialog(stage);
						if (file != null) {
							GameStateSerializer GSS = new GameStateSerializer(router);
							try {
								GSS.saveGameState(file.getName());
								setAuthoringName(file.getName());
							} catch (Exception exception) {
								System.out.println("Cannot create new game.");
							}
						}
					}
				});
	}
	
	private void startProjectHandler(Button startProjectButton, TextField xSize, TextField ySize) {
		startProjectButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					MapDataContainer container = router.getMapDataContainer();
					container.setDimensions(Integer.parseInt(xSize.getText()), Integer.parseInt(ySize.getText()));
					initAuthoring(container);
				} catch(Exception e){
					MapDataContainer container = router.getMapDataContainer();
					container.setDimensions(40, 20);
					initAuthoring(container);
				}

			}
		});
	}
	
	private void initAuthoring(MapDataContainer container) {
	    AuthorDisplay authoring = new AuthorDisplay(pane, scene, router);
//	    this.AuthDisp = authoring;
		stage.setScene(scene);
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private void setMapXDim(int newXDim) {
		mapXDim = newXDim;
	}
	
	private void setMapYDim(int newYDim) {
		mapYDim = newYDim;
	}
	
	private void setAuthoringName(String newName) {
		authoringName = newName;
	}
	
	public int getMapXDim() {
		return mapXDim;
	}
	
	public int getMapYDim() {
		return mapYDim;
	}
	
	public String getAuthoringName() {
		return authoringName;
	}
	
	
}
