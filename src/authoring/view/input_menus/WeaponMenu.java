package authoring.view.input_menus;

import java.util.ResourceBundle;
import authoring.controller.TowerDataController;
import authoring.controller.WeaponDataController;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.view.side_panel.TowerTab;
import authoring.model.WeaponData;
import authoring.view.side_panel.WeaponTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WeaponMenu {
    private static final int SIZE = 500;
    
    private ResourceBundle myResources;
    private WeaponTab myTab;
    private MenuHelper myHelper;
    private Stage myWeaponWindow;
    private TextField myNameField;
    private TextField myProjectileNameField;
    private TextField myRangeField;
    private TextField myRateField;
    private boolean myIsDefault;
    
    public WeaponMenu(ResourceBundle resources, WeaponTab tab) {
        myResources = resources;
        myTab = tab;
        myHelper = new MenuHelper(myResources);
    }
    
    /**
     * @param nameVal
     * @param projectileVal
     * @param rangeVal
     * @param rateVal
     */
    public void createWeaponMenu(String nameVal, String projectileVal, String rangeVal, String rateVal, boolean isDefault) {
        myIsDefault = isDefault;
    	myWeaponWindow = new Stage();
        myWeaponWindow.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        setUpWeaponScreen(root, nameVal, projectileVal, rangeVal, rateVal);
        Scene scene = new Scene(root, SIZE, SIZE);
        myWeaponWindow.setTitle(myResources.getString("AddWeapon"));
        myWeaponWindow.setScene(scene);
        myWeaponWindow.show();
    }
    
    private void setUpWeaponScreen(VBox root, String nameVal, String projectileVal, String rangeVal, String rateVal) {
        myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
        myProjectileNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterProjectile"), projectileVal);
        myRangeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterRange"), rangeVal);
        myRateField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterFireRate"), rateVal);

        setUpFinishButton(root, nameVal);
    }

	private void setUpFinishButton(VBox root, String originalName) {
		Button finishButton = new Button(myResources.getString("Finish"));
		finishButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				String name = myNameField.getCharacters().toString();
				String projectile = myProjectileNameField.getCharacters().toString();
				int range = 0;
				int rate = 0;
				try {
					range = Integer.parseInt(myRangeField.getCharacters().toString());
					rate = Integer.parseInt(myRateField.getCharacters().toString());
				} catch (Exception e){
					myHelper.showError(myResources.getString("BadIntInput"));
					return;
				}
				WeaponData weapon = createWeaponData(name, projectile, range, rate);
				if (weapon == null){
					return;
				}
				myTab.removeButtonDuplicates(name);
				myTab.addButtonToDisplay(name);
				if (myIsDefault){
					myTab.getController().createWeaponData(weapon);
				}
				else {
					myTab.getController().updateWeaponData(originalName, weapon);
				}
				myWeaponWindow.close();
			}
		});
		root.getChildren().add(finishButton);
	}
	
	private WeaponData createWeaponData(String name, String projectile, int range, int rate) {
		WeaponData weapon = new WeaponData();
		try {
			weapon.setName(name);
			weapon.setProjectileName(projectile);
			weapon.setRange(range);
			weapon.setFireRate(rate);
		} catch(Exception e) {
			myHelper.showError(e.getMessage());
			return null;
		}
		return weapon;
	}

}
