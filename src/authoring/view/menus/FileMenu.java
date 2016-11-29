package authoring.view.menus;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.*;

import authoring.model.serialization.GameStateSerializer;
import authoring.model.serialization.JSONSerializer;
import main.MainInitializer;
import main.MainMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
				try {
					Stage s = new Stage();
					MainMenu newInstance = new MainMenu(new MainInitializer(s), s);
					newInstance.init();
				} catch (IOException e) {
				}
			}
		});
	}
	
	private void performOpenProject(MenuItem openProject) {
		openProject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Deserialize TowerData, EnemyData, WaveData, RulesData, WeaponsData, MapData, LevelsData here.
			}
		});
	}
	
	private void performCloseProject(MenuItem closeProject) {
		closeProject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
	}
	
	private void performSaveProject(MenuItem saveProject) {
		saveProject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				//TODO: Serialize TowerData, EnemyData, WaveData, RulesData, WeaponsData, MapData, LevelsData here.
			}
		});
	}
	
	private void performSaveAs(MenuItem saveAs) {
		saveAs.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent e) {
					FileChooser newGameSave = new FileChooser();
					newGameSave.setTitle("Save As");
					newGameSave.setInitialDirectory(
					           new File(System.getProperty("user.home"))
					       ); 
					Stage stage = new Stage();
					File file = newGameSave.showSaveDialog(stage);
//					if (file != null) {
						GameStateSerializer gss = new GameStateSerializer();
						try {
							//change "exampleGame to user input"
							gss.saveGameState("exampleGame");
						} catch (Exception e1) {
							System.out.println("Unable to save current game state.");
						}
//					}
				}
		});
	}
	
}
