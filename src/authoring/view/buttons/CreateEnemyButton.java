package authoring.view.buttons;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class CreateEnemyButton {
	private static final int SIZE = 500;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private ResourceBundle myResources;
	
	public CreateEnemyButton(VBox root){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		Button enemyButton = new Button(myResources.getString("CreateEnemy"));
		createEnemyWindow(enemyButton);
		root.getChildren().add(enemyButton);
	}
	
	private void createEnemyWindow(Button enemyButton){
		enemyButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Stage enemyWindow = new Stage();
				enemyWindow.initModality(Modality.APPLICATION_MODAL);
				Group root = new Group();
				Scene scene = new Scene(root, SIZE, SIZE);
				enemyWindow.setTitle(myResources.getString("CreateEnemy"));
				enemyWindow.setScene(scene);
				enemyWindow.show();
			}
		});
	}

}
