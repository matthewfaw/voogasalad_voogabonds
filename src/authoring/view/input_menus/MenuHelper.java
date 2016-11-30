package authoring.view.input_menus;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuHelper {
	
	private ResourceBundle myResources;
	
	public MenuHelper(ResourceBundle resources){
		myResources = resources;
	}
	
	public TextField setUpBasicUserInput(VBox root, String enterText, String defaultValue){
		Text text = new Text(enterText);
		TextField textField = new TextField(defaultValue);
		root.getChildren().addAll(text, textField);
		return textField;
	}
	
	public ComboBox<String> setUpComboBoxUserInput(VBox root, String enterText, String defaultValue, 
			ArrayList<String> items){
		Text text = new Text(enterText);
		ComboBox<String> box = new ComboBox<String>();
		ObservableList<String> list = FXCollections.observableList(items);
		box.setItems(list);
		if (defaultValue != null)
			box.setValue(defaultValue);
		root.getChildren().addAll(text, box);
		return box;
	}
	
	public TextField setUpBrowseButton(VBox root, TextField field, String extensionName, String extension){
		Button browseButton = new Button(myResources.getString("Browse"));
		browseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extensionName, extension));
				File file = fileChooser.showOpenDialog(new Stage());
				if (file != null){
					field.setText(file.getPath());
				}
			}
		});
		root.getChildren().add(browseButton);
		return field;
	}

	public void showError (String message) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle(myResources.getString("ErrorTitle"));
	    alert.setContentText(message);
	    alert.showAndWait();
	}

}
