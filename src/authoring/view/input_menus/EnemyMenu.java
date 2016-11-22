package authoring.view.input_menus;

import java.util.ResourceBundle;

import authoring.model.EnemyData;
import authoring.view.side_panel.EnemyTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnemyMenu {
	private static final int SIZE = 350;
	
	private ResourceBundle myResources;
	private TextField myNameField;
	private TextField myHealthField;
	private TextField mySpeedField;
	private TextField myImageField;
	private TextField myCollisionRadiusField;
	private TextField myKillRewardField;
	private ComboBox<String> myWeaponBox;
	private Stage myEnemyWindow;
	private EnemyTab myTab;
	private MenuHelper myHelper;
	private boolean myIsDefault;
	
	public EnemyMenu(ResourceBundle resources, EnemyTab tab){
		myResources = resources;
		myTab = tab;
		myHelper = new MenuHelper(myResources);
	}
	
	public void createEnemyWindow(String nameVal, String healthVal, String speedVal, 
			String collisionRadiusVal, String killRewardVal, String imageVal, boolean isDefault) {
		myIsDefault = isDefault;
		myEnemyWindow = new Stage();
		myEnemyWindow.initModality(Modality.APPLICATION_MODAL);
		VBox root = new VBox();
		setUpEnemyScreen(root, nameVal, healthVal, speedVal, collisionRadiusVal, killRewardVal, imageVal);
		Scene scene = new Scene(root, SIZE, SIZE);
		myEnemyWindow.setTitle(myResources.getString("AddEnemy"));
		myEnemyWindow.setScene(scene);
		myEnemyWindow.show();
	}
	
	private void setUpEnemyScreen(VBox root, String nameVal, String healthVal, String speedVal, 
			String collisionRadiusVal, String killRewardVal, String imageVal){
		myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
		myHealthField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterHealth"), healthVal);
		mySpeedField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSpeed"), speedVal);
		myCollisionRadiusField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterCollisionRadius"), 
				collisionRadiusVal);
		myKillRewardField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterKillReward"), 
				killRewardVal);
		setUpImage(root, imageVal);
		setUpWeapon(root);
		setUpFinishButton(root, nameVal);
	}
	
	private void setUpImage(VBox root, String value) {
		myImageField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterImage"), value);
		myHelper.setUpBrowseButton(root, myImageField, "PNG", "*.png");
	}

	private void setUpWeapon(VBox root) {
		Text weaponText = new Text(myResources.getString("SelectWeapon"));
		myWeaponBox = new ComboBox<String>();
		ObservableList<String> list = FXCollections.observableList(myTab.getWeapons());
		myWeaponBox.setItems(list);
		root.getChildren().addAll(weaponText, myWeaponBox);
	}
	
	private void setUpFinishButton(VBox root, String originalName){
		Button finishButton = new Button(myResources.getString("Finish"));
		finishButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				String name = myNameField.getCharacters().toString();
				int health = 0;
				int speed = 0;
				int collisionRadius = 0;
				int killReward = 0;
				try {
					health = Integer.parseInt(myHealthField.getCharacters().toString());
					speed = Integer.parseInt(mySpeedField.getCharacters().toString());
					collisionRadius = Integer.parseInt(myCollisionRadiusField.getCharacters().toString());
					killReward = Integer.parseInt(myKillRewardField.getCharacters().toString());
				} catch(Exception e) {
					myHelper.showError(myResources.getString("BadIntInput"));
					return;
				}
				String image = myImageField.getCharacters().toString();
				String weapon = myWeaponBox.getValue();
				myTab.removeButtonDuplicates(name);
				myTab.addButtonToDisplay(name);
				myTab.getEnemies().add(name);
				EnemyData enemy = createEnemyData(name, health, speed, collisionRadius, killReward, image,
						weapon);
				if (enemy == null){
					return;
				}
				if (myIsDefault)
					myTab.getController().createEnemyData(enemy);
				else
					myTab.getController().updateEnemyData(originalName, enemy);
				myEnemyWindow.close();
			}

			private EnemyData createEnemyData(String name, int health, int speed, int collisionRadius, 
					int killReward, String image, String weapon) {
				EnemyData enemy = new EnemyData();
				try {
					enemy.setName(name);
					enemy.setMaxHealth(health);
					enemy.setSpeed(speed);
					enemy.setCollisionRadius(collisionRadius);
					enemy.setKillReward(killReward);
					enemy.setImagePath(image);
					if (weapon != null)
						enemy.setWeaponName(weapon);
				} catch(Exception e){
					myHelper.showError(e.getMessage());
					return null;
				}
				return enemy;
			}
		});
		root.getChildren().add(finishButton);
	}
}
