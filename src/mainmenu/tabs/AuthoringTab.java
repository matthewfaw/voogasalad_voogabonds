package mainmenu.tabs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class AuthoringTab extends Tab {
	
	private TabPane root;
	private Tab authorTab;
	private int screenWidth;
	private int screenHeight;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private VBox authorOptions;

	public AuthoringTab(TabPane mainMenuTab) {
		setUpScreenResolution();
		this.root = mainMenuTab;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.authorTab = new Tab(myResources.getString("EnterAuthorMode"));
		this.authorOptions = new VBox(screenHeight*0.1);
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
				handleNewProject();
			}
		});
		Button loadProject = new Button(myResources.getString("AuthorOldProject"));
		loadProject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				handleOldProject();
			}
		});
		authorOptions.getChildren().addAll(newProject, loadProject);
	}
	
	private void handleNewProject() {
		
	}
	
	private void handleOldProject() {
		
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
