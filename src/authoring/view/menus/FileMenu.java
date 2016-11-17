package authoring.view.menus;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Christopher Lu
 * Creates the menus under the File Button. These include: New, Open, Close, Save, and Save As.
 */

public class FileMenu extends Menu {
	
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Menu file;
	
	public FileMenu(MenuBar bar) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.file = new Menu(myResources.getString("FileMenuLabel"));
		fileOptions(file);
		bar.getMenus().add(file);
	}
	
	private void fileOptions(Menu menu) {
		MenuItem newProject = new MenuItem(myResources.getString("NewFileLabel"));
		performNewProject(newProject);
		MenuItem openProject =  new MenuItem(myResources.getString("OpenFileLabel"));
		performOpenProject(openProject);
		MenuItem closeProject = new MenuItem(myResources.getString("CloseFileLabel"));
		performCloseProject(closeProject);
		MenuItem saveProject = new MenuItem(myResources.getString("SaveFileLabel"));
		performSaveProject(saveProject);
		MenuItem saveAs = new MenuItem(myResources.getString("SaveAsLabel"));
		performSaveAs(saveAs);
		file.getItems().addAll(newProject, openProject, closeProject, saveProject, saveAs);
	}
	
	private void performNewProject(MenuItem newProject) {
		newProject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that opens up a new project upon clicking New button.
			}
		});
	}
	
	private void performOpenProject(MenuItem openProject) {
		openProject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that opens up a pre-existing project upon clicking Open button.
			}
		});
	}
	
	private void performCloseProject(MenuItem closeProject) {
		closeProject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that closes project upon clicking Close button.
			}
		});
	}
	
	private void performSaveProject(MenuItem saveProject) {
		saveProject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that saves current project upon clicking Save button.
			}
		});
	}
	
	private void performSaveAs(MenuItem saveAs) {
		saveAs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that saves project with specified name upon clicking Close button. Use a fileChooser.
			}
		});
	}
	
}
