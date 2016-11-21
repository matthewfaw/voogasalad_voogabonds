package authoring.view.input_menus;

import java.util.ResourceBundle;

import authoring.controller.EnemyDataController;
import authoring.view.objects.FrontEndEnemy;
import authoring.view.side_panel.EnemyTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnemyMenu {
	private static final int SIZE = 500;
	
	private ResourceBundle myResources;private TextField myNameField;
	private TextField myTypeField;
	private TextField myHealthField;
	private TextField mySpeedField;
	private TextField mySpawnField;
	private TextField myEndField;
	private TextField myImageField;
	private TextField mySoundField;
	private Stage myEnemyWindow;
	private EnemyDataController myController;
	private EnemyTab myTab;
	private MenuHelper myHelper;
	
	public EnemyMenu(ResourceBundle resources, EnemyTab tab){
		myResources = resources;
		myTab = tab;
		myController = new EnemyDataController();
		myHelper = new MenuHelper(myResources);
	}
	
	public void createEnemyWindow(String nameVal, String typeVal, String healthVal, String speedVal, 
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
		myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
		myTypeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterType"), typeVal);
		myHealthField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterHealth"), healthVal);
		mySpeedField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSpeed"), speedVal);
		mySpawnField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSpawn"), spawnVal);
		myEndField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterEnd"), endVal);
		setUpImage(root, imageVal);
		setUpSound(root, soundVal);
		setUpWeapon(root);
		setUpFinishButton(root);
	}
	
	private void setUpImage(VBox root, String value) {
		myImageField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterImage"), value);
		myHelper.setUpBrowseButton(root, myImageField, "PNG", "*.png");
	}
	
	private void setUpSound(VBox root, String value) {
		if (value == null)
			mySoundField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSound"), 
					myResources.getString("DefaultSound"));
		else
			mySoundField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSound"), value);
		myHelper.setUpBrowseButton(root, mySoundField, "WAV", "*.wav");
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
					myTab.showError(myResources.getString("BadDoubleInput"));
					return;
				}
				Point2D spawn = convertStringToPoint(mySpawnField.getCharacters().toString());
				Point2D end = convertStringToPoint(myEndField.getCharacters().toString());
				if (spawn == null || end == null){
					return;
				}
				String image = myImageField.getCharacters().toString();
				if (!(image.substring(image.length() - 4).equals(".png"))){
					myTab.showError(myResources.getString("NoImageInput"));
					return;
				}
				FrontEndEnemy enemy = new FrontEndEnemy(speed, spawn, end, name, health, type, image);
				String sound = mySoundField.getCharacters().toString();
				if (sound.substring(sound.length() - 4).equals(".wav")){
					enemy.setSound(sound);
				}
				myTab.removeButtonDuplicates(name);
				myTab.addButtonToDisplay(name);
				myTab.getEnemyMap().put(name, enemy);
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
			myTab.showError(myResources.getString("BadPointInput"));
			return null;
		}
		return new Point2D(x, y);
	}
}
