package authoring.view.input_menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.model.EnemyData;
import authoring.view.side_panel.EnemyTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Deprecated
public class EnemyMenu {
	private static final int SIZE = 400;
	
	private ResourceBundle myResources;
	private TextField myNameField;
	private TextField myHealthField;
	private TextField mySpeedField;
	private TextField myImageField;
	private TextField myCollisionRadiusField;
	private TextField myKillRewardField;
	private ComboBox<String> myWeaponBox;
	private MenuButton myTerrainButton;
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
			String collisionRadiusVal, String killRewardVal, String imageVal, String weaponVal, 
			List<String> terrainVals, boolean isDefault) {
		if (myTab.getTerrains().size() == 0){
			myHelper.showError(myResources.getString("NoTerrainsCreated"));
			return;
		}
		myIsDefault = isDefault;
		myEnemyWindow = new Stage();
		myEnemyWindow.initModality(Modality.APPLICATION_MODAL);
		VBox root = new VBox();
		setUpEnemyScreen(root, nameVal, healthVal, speedVal, collisionRadiusVal, killRewardVal, imageVal,
				weaponVal, terrainVals);
		Scene scene = new Scene(root, SIZE, SIZE);
		myEnemyWindow.setTitle(myResources.getString("AddEnemy"));
		myEnemyWindow.setScene(scene);
		myEnemyWindow.show();
	}
	
	private void setUpEnemyScreen(VBox root, String nameVal, String healthVal, String speedVal, 
			String collisionRadiusVal, String killRewardVal, String imageVal, String weaponVal,
			List<String> terrainVals){
		myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
		myHealthField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterHealth"), healthVal);
		mySpeedField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSpeed"), speedVal);
		myCollisionRadiusField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterCollisionRadius"), 
				collisionRadiusVal);
		myKillRewardField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterKillReward"), 
				killRewardVal);
		setUpImage(root, imageVal);
		setUpTerrains(root, terrainVals);
		myWeaponBox = myHelper.setUpComboBoxUserInput(root, myResources.getString("SelectWeapon"), 
				weaponVal, myTab.getWeapons());
		setUpFinishButton(root, nameVal);
	}
	
	private void setUpImage(VBox root, String value) {
		myImageField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterImage"), value);
		myHelper.setUpBrowseButton(root, myImageField, "PNG", "*.png");
	}
	
	private void setUpTerrains(VBox root, List<String> values){
		myTerrainButton = new MenuButton(myResources.getString("SelectTerrains"));
		for (String terrain: myTab.getTerrains()){
			CheckBox checkBox = new CheckBox(terrain);
			checkBox.setSelected(values.contains(terrain));
			CustomMenuItem custom = new CustomMenuItem(checkBox);
			myTerrainButton.getItems().add(custom);
		}
		root.getChildren().add(myTerrainButton);
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
				List<String> terrainList = new ArrayList<String>();
				for (MenuItem item: myTerrainButton.getItems()){
					CustomMenuItem custom = (CustomMenuItem) item;
					CheckBox check = (CheckBox) custom.getContent();
					if (check.isSelected()){
						terrainList.add(check.getText());
					}
				}
				if (terrainList.size() == 0){
					myHelper.showError(myResources.getString("NoTerrainInput"));
					return;
				}
				myTab.removeButtonDuplicates(name);
				myTab.addButtonToDisplay(name);
				EnemyData enemy = createEnemyData(name, health, speed, collisionRadius, killReward, image,
						weapon, terrainList);
				if (enemy == null){
					return;
				}
				if (myIsDefault)
					myTab.getController().createEnemyData(enemy);
				else
					myTab.getController().updateEnemyData(originalName, enemy);
				myEnemyWindow.close();
			}
		});
		root.getChildren().add(finishButton);
	}
	
	private EnemyData createEnemyData(String name, int health, int speed, int collisionRadius, 
			int killReward, String image, String weapon, List<String> terrainList) {
		EnemyData enemy = new EnemyData();
		try {
			enemy.setName(name);
			enemy.setMaxHealth(health);
			enemy.setMoveSpeed(speed);
			enemy.setCollisionRadius(collisionRadius);
			enemy.setKillReward(killReward);
			enemy.setImagePath(image);
			enemy.setTerrainList(terrainList);
			if (weapon != null)
				enemy.setWeaponName(weapon);
		} catch(Exception e){
			myHelper.showError(e.getMessage());
			return null;
		}
		return enemy;
	}
}
