package mainmenu.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
import authoring.controller.Router;
import authoring.model.serialization.GameStateDeserializer;
import authoring.model.serialization.GameStateLoader;
import authoring.model.serialization.JSONDeserializer;
import authoring.view.display.AuthorDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainInitializer;
import utility.ErrorBox;


/**
 * @author Christopher Lu
 * This class creates the load authoring environment screen when the user wants to open a preexisting authoring environment to work on.
 */

public class LoadAuthoringScreen {

	private Scene scene;
	private Stage stage;
	private BorderPane pane;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private int screenWidth;
	private int screenHeight;
	private TextField showSelectedTitle = new TextField();
	private ObservableList<MenuTableItem> data =
			FXCollections.observableArrayList(
					);
	private GameStateDeserializer GSD;
	private String selectedGame;
	private Router router;
	
	public LoadAuthoringScreen(Router r) throws IOException {
		setUpScreenResolution();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.stage = new Stage();
		this.router = r;
		stage.setTitle(myResources.getString("LoadExistingAuthoringTitle"));
		this.pane = new BorderPane();
		this.scene = new Scene(pane);
		scene.getStylesheets().add("style.css");
		pane.setId("background");
		populatePane();
		stage.setScene(scene);
		stage.show();

	}
	
	private void populatePane() {
		HBox optionArea = new HBox();
		TableView<MenuTableItem> chooseProjectTable = new TableView<MenuTableItem>();
		TableColumn<MenuTableItem, String> firstCol = new TableColumn<MenuTableItem, String>(myResources.getString("ProjectTitle"));
		TableColumn<MenuTableItem, Date> secondCol = new TableColumn<MenuTableItem, Date>(myResources.getString("LastModified"));
		String relativePath = new File("").getAbsolutePath();
		String finalPath = relativePath.concat(myResources.getString("ExistingAuthoringFiles"));
		File directory = new File(finalPath);
		for (File f : directory.listFiles()) {
			Date date = new Date(f.lastModified());
			MenuTableItem item = new MenuTableItem(f.getName(), date);
			data.add(item);
		}
		firstCol.setCellValueFactory(
				new PropertyValueFactory<MenuTableItem, String>("projectName"));
		secondCol.setCellValueFactory(
				new PropertyValueFactory<MenuTableItem, Date>("modifiedDate"));
		chooseProjectTable.setItems(data);
		chooseProjectTable.getColumns().addAll(firstCol, secondCol);
		VBox boxRight = new VBox(screenHeight*0.1);
		boxRight.setId("vbox");
		showSelectedTitle.setPromptText(myResources.getString("ShowSelectedProject"));
		showSelectedTitle.setEditable(false);
		Button startAuthoring = new Button(myResources.getString("ConfirmAuthoringSetUp"));
		startAuthoring.setId("button");
		boxRight.getChildren().addAll(showSelectedTitle, startAuthoring);
		optionArea.getChildren().addAll(chooseProjectTable, boxRight);
		pane.setCenter(optionArea);
		chooseProjectTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	GSD = new GameStateDeserializer();
		        	showSelectedTitle.setText(chooseProjectTable.getSelectionModel().getSelectedItem().getProjectName());
		        	selectedGame = chooseProjectTable.getSelectionModel().getSelectedItem().getProjectName();
		    		System.out.println("Selected Game is: " + selectedGame);
		    		startHandler(startAuthoring, selectedGame);
		        	try {
						GSD.loadGameState("src/SerializedFiles/"+chooseProjectTable.getSelectionModel().getSelectedItem().getProjectName().toString());
					} catch (Exception e) {
						//TODO: add error checking to more appropriate place in package
					}
		        }
		    }
		});
	}
	
	private void startHandler(Button startButton, String gameTitle) {
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				JSONDeserializer des = new JSONDeserializer();
				MapDataContainer container;
				GameStateLoader loader = new GameStateLoader();
				loader.loadMapData(router, gameTitle);
				loader.loadEntityData(router, gameTitle);
				
				initAuthoring();
			}
		});
	}
	
	public void initAuthoring() {
    AuthorDisplay authoring = new AuthorDisplay(pane, scene, router);
//    this.AuthDisp = authoring;
	stage.setScene(scene);
}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
		
	
}
