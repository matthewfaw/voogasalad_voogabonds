package mainmenu.tabs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import main.MainInitializer;
import mainmenu.screens.LoadAuthoringScreen;
import mainmenu.screens.NewAuthoringScreen;
import utility.ErrorBox;

public class AuthoringTab extends Tab {
	
	private TabPane root;
	private Tab authorTab;
	private int screenWidth;
	private int screenHeight;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private VBox authorOptions;
	private MainInitializer initializer;

	public AuthoringTab(TabPane mainMenuTab, MainInitializer init) {
		setUpScreenResolution();
		this.root = mainMenuTab;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.authorTab = new Tab(myResources.getString("EnterAuthorMode"));
		this.authorOptions = new VBox(screenHeight*0.1);
		this.initializer = init;
		populateTab();
		root.getTabs().add(authorTab);
	}
	
	private void populateTab() {
		populateBox();
		authorTab.setContent(authorOptions);
	}
	
	private void populateBox() {
		Button newProject = new Button(myResources.getString("AuthorNewProject"));
		newProject.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					handleNewProject();
				} catch (IOException e1) {
					ErrorBox.displayError(myResources.getString("NewAuthoringError"));
				}
			}
		});
		Button loadProject = new Button(myResources.getString("AuthorOldProject"));
		loadProject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					handleOldProject();
				} catch (IOException e1) {
					ErrorBox.displayError(myResources.getString("LoadAuthoringError"));
				}
			}
		});
		authorOptions.getChildren().addAll(newProject, loadProject);
	}
	
	private void handleNewProject() throws IOException {
		NewAuthoringScreen newScreen = new NewAuthoringScreen();
	}
	
	private void handleOldProject() throws IOException {
		LoadAuthoringScreen loadScreen = new LoadAuthoringScreen();
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
