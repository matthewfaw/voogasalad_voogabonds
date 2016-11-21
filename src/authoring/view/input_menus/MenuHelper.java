package authoring.view.input_menus;

import java.io.File;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	
	public void setUpBrowseButton(VBox root, TextField field, String extensionName, String extension){
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
	}
	
	public void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }

}
