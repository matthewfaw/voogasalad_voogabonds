package mainmenu.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainInitializer;

/**
 * @author Christopher Lu
 * This class creates the load authoring environment screen when the user wants to open a preexisting authoring environment to work on.
 */

public class LoadAuthoringScreen {

	private Scene scene;
	private Stage stage;
	private MainInitializer initializer;
	private BorderPane pane;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private int screenWidth;
	private int screenHeight;
	private ObservableList<MenuTableItem> data =
			FXCollections.observableArrayList(
					);
	
	public LoadAuthoringScreen() throws IOException {
		setUpScreenResolution();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.stage = new Stage();
		this.initializer = new MainInitializer(stage);
		stage.setTitle(myResources.getString("LoadExistingAuthoringTitle"));
		this.pane = new BorderPane();
		this.scene = new Scene(pane);
		populatePane();
		stage.setScene(scene);
		stage.show();
	}
	
	private void populatePane() {
		HBox optionArea = new HBox(screenWidth*0.1);
		TableView<MenuTableItem> chooseProjectTable = new TableView<MenuTableItem>();
		TableColumn firstCol = new TableColumn(myResources.getString("ProjectTitle"));
		TableColumn secondCol = new TableColumn(myResources.getString("LastModified"));
		chooseProjectTable.getColumns().addAll(firstCol, secondCol);
		String relativePath = new File("").getAbsolutePath();
		String finalPath = relativePath.concat(myResources.getString("ExistingAuthoringFiles"));
		File directory = new File(finalPath);
		for (File f : directory.listFiles()) {
			Date date = new Date(f.lastModified());
			MenuTableItem item = new MenuTableItem(f.getName(), date);
			data.add(new MenuTableItem(f.getName(), date));
		}
		firstCol.setCellValueFactory(
				new PropertyValueFactory<MenuTableItem, String>("projectName"));
		secondCol.setCellValueFactory(
				new PropertyValueFactory<MenuTableItem, Long>("modifiedDate"));
		chooseProjectTable.setItems(data);
		chooseProjectTable.getColumns().addAll(firstCol, secondCol);
		VBox boxRight = new VBox(screenHeight*0.1);
		TextField showSelectedTitle = new TextField();
		showSelectedTitle.setPromptText(myResources.getString("ShowSelectedProject"));
		showSelectedTitle.setEditable(false);
		Button startAuthoring = new Button(myResources.getString("ConfirmAuthoringSetUp"));
		boxRight.getChildren().addAll(showSelectedTitle, startAuthoring);
		optionArea.getChildren().addAll(chooseProjectTable, boxRight);
		pane.setCenter(optionArea);
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
