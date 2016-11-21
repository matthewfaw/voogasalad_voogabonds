package main;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
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
	private MainInitalizer initializer;
	private BorderPane pane;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private TabPane tabContainer;
	private NewGameTab newTab;
	private LoadGameTab loadTab;
	private int screenWidth;
	private int screenHeight;
	
	public MainMenu(MainInitalizer init, Stage s) {
		setUpScreenResolution();
		this.stage = s;
		this.initializer = init;
		this.pane = new BorderPane();
		this.scene = new Scene(pane, init.getScreenWidth()/2, init.getScreenHeight()/2);
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.tabContainer = new TabPane();
		tabContainer.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		this.newTab = new NewGameTab(tabContainer);
		this.loadTab = new LoadGameTab(tabContainer);
	}
	
	public Scene init() {
//		pane.getChildren().add(createBackgroundImage());
		pane.setPadding(new Insets(screenHeight*0.01, screenWidth*0.01, screenHeight*0.01, screenWidth*0.01));
		pane.setTop(createText(myResources.getString("ApplicationTitle")));
		pane.setCenter(tabContainer);
		Button b = createButton(myResources.getString("StartButton"));
		b.setPadding(new Insets(screenHeight*0.05, screenWidth*0.05, screenHeight*0.05, screenWidth*0.05));
		BorderPane.setAlignment(b, Pos.BASELINE_RIGHT);
		pane.setBottom(b);
		return scene;
	}
	
//	private ImageView createBackgroundImage() {
//		ImageView background = new ImageView(
//				new Image(getClass().getClassLoader().getResourceAsStream("resources/MainMenu.jpg")));
//		background.setX(0);
//		background.setY(0);
//		return background;
//	}
	
	private Text createText(String desiredText) {
		Text t = new Text(desiredText);
		t.setFont(Font.font("Verdana", 70));
		t.setFill(Color.BLACK);
		return t;
	}
	
	private Button createButton(String label) {
		Button button = new Button(label);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				initializer.initSim();
			}
		});
		return button;
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}