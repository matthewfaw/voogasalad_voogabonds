package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

import authoring.controller.EnemyDataController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Christopher Lu
 * Creates the enemy pane option that allows user to add enemies. Preexisting/created enemies will showup in the pane as buttons that can be edited upon click.
 */

public class EnemyTab extends Tab {
	private static final int SIZE = 500;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private ResourceBundle myResources;
	private Tab enemyTab;
	private int screenWidth;
	private int screenHeight;
	private TextField myNameField;
	private TextField myTypeField;
	private TextField myHealthField;
	private TextField mySpeedField;
	private TextField mySpawnField;
	private TextField myEndField;
	private TextField myImageField;
	private TextField mySoundField;
	private Stage myEnemyWindow;
	private VBox myContent;
	private HashMap<String, FrontEndEnemy> myEnemyMap;
	private EnemyDataController myController;
	
	public EnemyTab(TabPane pane) {
		screenInfo();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		enemyTab = new Tab(myResources.getString("Enemies"));
		myEnemyMap = new HashMap<String, FrontEndEnemy>();
		myController = new EnemyDataController();
		enemyTabOptions(enemyTab);
		pane.getTabs().add(enemyTab);
	}
	
	private void enemyTabOptions(Tab enemyTab) {
		VBox enemyArea = new VBox(screenHeight*0.01);
		enemyArea.setMaxHeight(screenHeight*0.88);
		ScrollPane availableEnemies = new ScrollPane();
		myContent = new VBox();
		availableEnemies.setContent(myContent);
		availableEnemies.setPrefSize(screenWidth/5, screenHeight);
		HBox enemyButtons = new HBox(screenWidth*0.05);
		enemyButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button addEnemy = new Button(myResources.getString("AddEnemy"));
		addEnemy.setOnAction(addEnemyHandler());
		enemyButtons.getChildren().addAll(addEnemy);
		enemyArea.getChildren().addAll(availableEnemies, enemyButtons);
		enemyTab.setContent(enemyArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private EventHandler<ActionEvent> addEnemyHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				createEnemyWindow(myResources.getString("DefaultName"), myResources.getString("DefaultType"), 
						myResources.getString("DefaultHealth"), myResources.getString("DefaultSpeed"), 
						myResources.getString("DefaultSpawn"), myResources.getString("DefaultEnd"), 
						myResources.getString("DefaultImage"), myResources.getString("DefaultSound"));
			}
		};
		return handler;
	}
	

	private void createEnemyWindow(String nameVal, String typeVal, String healthVal, String speedVal, 
			String spawnVal, String endVal, String imageVal, String soundVal) {
		myEnemyWindow = new Stage();
		myEnemyWindow.initModality(Modality.APPLICATION_MODAL);
		VBox root = new VBox();
		setUpEnemyScreen(root, nameVal, typeVal, healthVal, speedVal, spawnVal, endVal, imageVal, soundVal);
		Scene scene = new Scene(root, SIZE, SIZE);
		myEnemyWindow.setTitle(myResources.getString("AddEnemy"));
		myEnemyWindow.setScene(scene);
		myEnemyWindow.show();
	}
	
	private void setUpEnemyScreen(VBox root, String nameVal, String typeVal, String healthVal, 
			String speedVal, String spawnVal, String endVal, String imageVal, String soundVal){
		myNameField = setUpBasicUserInput(root, "EnterName", nameVal);
		myTypeField = setUpBasicUserInput(root, "EnterType", typeVal);
		myHealthField = setUpBasicUserInput(root, "EnterHealth", healthVal);
		mySpeedField = setUpBasicUserInput(root, "EnterSpeed", speedVal);
		mySpawnField = setUpBasicUserInput(root, "EnterSpawn", spawnVal);
		myEndField = setUpBasicUserInput(root, "EnterEnd", endVal);
		setUpImage(root, imageVal);
		setUpSound(root, soundVal);
		setUpWeapon(root);
		setUpFinishButton(root);
	}
	
	private TextField setUpBasicUserInput(VBox root, String enterText, String defaultValue){
		Text text = new Text(myResources.getString(enterText));
		TextField textField = new TextField(defaultValue);
		root.getChildren().addAll(text, textField);
		return textField;
	}
	
	private void setUpImage(VBox root, String value) {
		myImageField = setUpBasicUserInput(root, "EnterImage", value);
		setUpBrowseButton(root, myImageField, "PNG", "*.png");
	}
	
	private void setUpSound(VBox root, String value) {
		if (value == null)
			mySoundField = setUpBasicUserInput(root, "EnterSound", myResources.getString("DefaultSound"));
		else
			mySoundField = setUpBasicUserInput(root, "EnterSound", value);
		setUpBrowseButton(root, mySoundField, "WAV", "*.wav");
	}
	
	private void setUpBrowseButton(VBox root, TextField field, String extensionName, String extension){
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

	private void setUpWeapon(VBox root) {
		Text weaponText = new Text(myResources.getString("SelectWeapon"));
		ComboBox<String> weaponBox = new ComboBox<String>();
		root.getChildren().addAll(weaponText, weaponBox);
	}
	
	private void setUpFinishButton(VBox root){
		Button finishButton = new Button(myResources.getString("Finish"));
		finishButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				String name = myNameField.getCharacters().toString();
				String type = myTypeField.getCharacters().toString();
				double health = 0;
				double speed = 0;
				try {
					health = Double.parseDouble(myHealthField.getCharacters().toString());
					speed = Double.parseDouble(mySpeedField.getCharacters().toString());
				} catch(Exception e) {
					showError(myResources.getString("BadDoubleInput"));
					return;
				}
				Point2D spawn = convertStringToPoint(mySpawnField.getCharacters().toString());
				Point2D end = convertStringToPoint(myEndField.getCharacters().toString());
				if (spawn == null || end == null){
					return;
				}
				String image = myImageField.getCharacters().toString();
				if (!(image.substring(image.length() - 4).equals(".png"))){
					showError(myResources.getString("NoImageInput"));
					return;
				}
				FrontEndEnemy enemy = new FrontEndEnemy(speed, spawn, end, name, health, type, image);
				String sound = mySoundField.getCharacters().toString();
				if (sound.substring(sound.length() - 4).equals(".wav")){
					enemy.setSound(sound);
				}
				removeButtonDuplicates(name);
				addButtonToDisplay(name);
				myEnemyMap.put(name, enemy);
				// TODO: set up weapon for enemy
				myController.createEnemyData(enemy);
				myEnemyWindow.close();
			}
		});
		root.getChildren().add(finishButton);
	}
	
	private Point2D convertStringToPoint(String pointStr){
    	String noParenth = pointStr.substring(1, pointStr.length() - 1);
		String[] temp = noParenth.split(",");
		double x = 0;
		double y = 0;
		try {
			x = Double.parseDouble(temp[0]);
			y = Double.parseDouble(temp[1].trim());
		} catch (Exception e){
			showError(myResources.getString("BadPointInput"));
			return null;
		}
		return new Point2D(x, y);
	}
	
	private void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	private void removeButtonDuplicates(String s) {
		for (int i = 0; i < myContent.getChildren().size(); i++) {
			Button button = (Button) (myContent.getChildren().get(i));
			if (button.getText().equals(s)) {
				myContent.getChildren().remove(i);
				i--;
			}
		}
	}
	
	private void addButtonToDisplay(String text) {
		Button button = new Button(text);
		button.setMinWidth(myContent.getMinWidth());
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				FrontEndEnemy enemy = myEnemyMap.get(text);
				createEnemyWindow(enemy.getName(), enemy.getType(), String.valueOf(enemy.getHealth()), 
						String.valueOf(enemy.getSpeed()), point2DToString(enemy.getSpawnPoint()), 
						point2DToString(enemy.getEndPoint()), enemy.getImage(), enemy.getSound());
			}
		});
		myContent.getChildren().add(button);
	}
	
	private String point2DToString(Point2D point){
		StringBuilder sb = new StringBuilder("(");
		sb.append(point.getX());
		sb.append(", ");
		sb.append(point.getY());
		sb.append(")");
		return sb.toString();
	}
}
