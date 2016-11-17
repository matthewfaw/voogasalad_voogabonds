package main;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author ChristopherLu
 * This class generates the main menu that, in the future, will allow the user to choose between working on a new or previously saved object.
 * TODO: Implement TabPane allowing users to choose between new project or old project. If working on new project, have text fields prompting for save location and name of file.
 * For previous projects, allow users to choose from list of previous projects by title, sorted by most recently opened.
 */

public class MainMenu {

	private Scene scene;
	private Stage stage;
	private StageController controller;
	private BorderPane pane;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public MainMenu(StageController c, Stage s) {
		this.stage = s;
		this.controller = c;
		this.pane = new BorderPane();
		this.scene = new Scene(pane, controller.getScreenWidth()/2, controller.getScreenHeight()/2);
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
	}
	
	public Scene init() {
		pane.getChildren().add(createBackgroundImage());
		pane.setCenter(createText(myResources.getString("ApplicationTitle")));

		Button b = createButton(myResources.getString("StartButton"));
		BorderPane.setAlignment(b, Pos.BASELINE_CENTER);
		pane.setBottom(b);
		return scene;
	}
	
	private ImageView createBackgroundImage() {
		ImageView background = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream("resources/MainMenu.jpg")));
		background.setX(0);
		background.setY(0);
		return background;
	}
	
	private Text createText(String desiredText) {
		Text t = new Text(desiredText);
		t.setFont(Font.font("Verdana", 70));
		t.setFill(Color.WHITE);
		return t;
	}
	
	private Button createButton(String label) {
		Button button = new Button(label);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				controller.initSim();
			}
		});
		return button;
	}
	
}
