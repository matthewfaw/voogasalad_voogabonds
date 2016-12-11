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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainmenu.tabs.AuthoringTab;
import mainmenu.tabs.LoadGameTab;
import mainmenu.tabs.NewGameTab;
import mainmenu.tabs.PlayerTab;
/**
 * @author ChristopherLu
 * This class generates the main menu that, in the future, will allow the user to choose between working on a new or previously saved object.
 * TODO: Implement TabPane allowing users to choose between new project or old project. If working on new project, have text fields prompting for save location and name of file.
 * For previous projects, allow users to choose from list of previous projects by title, sorted by most recently opened.
 */
public class MainMenu {
	private Scene scene;
	private Stage stage;
	private MainInitializer initializer;
	private BorderPane pane;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private TabPane tabContainer;
	private PlayerTab playTab;
	private AuthoringTab authorTab;
	private NewGameTab newTab;
	private LoadGameTab loadTab;
	private int screenWidth;
	private int screenHeight;
	
	public MainMenu(MainInitializer init, Stage s) {
		setUpScreenResolution();
		this.stage = s;
		this.initializer = init;
		this.pane = new BorderPane();
		pane.setId("menu-background");
		this.scene = new Scene(pane, init.getScreenWidth()/2, init.getScreenHeight()/2);
		scene.getStylesheets().add("style.css");
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.tabContainer = new TabPane();
		tabContainer.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabContainer.setId("background");
		this.playTab = new PlayerTab(tabContainer, initializer);
		this.authorTab = new AuthoringTab(tabContainer, initializer);
		for (Tab tab: tabContainer.getTabs()){
			tab.setId("tab");
		}
//		this.newTab = new NewGameTab(tabContainer);
//		this.loadTab = new LoadGameTab(tabContainer);
		this.initializer.setTitle(myResources.getString("MainMenuTitle"));
	}
	
	public Scene init() {
		pane.setPadding(new Insets(screenHeight*0.01, screenWidth*0.01, screenHeight*0.01, screenWidth*0.01));
		pane.setTop(createText(myResources.getString("ApplicationTitle")));
		pane.setCenter(tabContainer);
//		Button b = createButton(myResources.getString("StartButton"));
//		b.setPadding(new Insets(screenHeight*0.05, screenWidth*0.05, screenHeight*0.05, screenWidth*0.05));
//		BorderPane.setAlignment(b, Pos.BASELINE_RIGHT);
//		pane.setBottom(b);
		return scene;
	}
	
	private Text createText(String desiredText) {
		Text t = new Text(desiredText);
		t.setFont(Font.font("Verdana", 70));
		return t;
	}
	
//	private Button createButton(String label) {
//		Button button = new Button(label);
//		button.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(final ActionEvent e) {
//				initializer.initAuthoring();
//			}
//		});
//		return button;
//	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}