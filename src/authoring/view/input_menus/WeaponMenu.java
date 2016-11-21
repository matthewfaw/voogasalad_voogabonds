package authoring.view.input_menus;

import java.util.ResourceBundle;
import authoring.controller.TowerDataController;
import authoring.view.side_panel.TowerTab;
import authoring.view.side_panel.WeaponTab;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WeaponMenu {
    private static final int SIZE = 500;
    
    private ResourceBundle myResources;
    private WeaponTab myTab;
    //private WeaponDataController myController;
    private MenuHelper myHelper;
    private Stage myWeaponWindow;
    private TextField myNameField;
    private TextField myProjectileNameField;
    private TextField myRangeField;
    private TextField myRateField;
    
    public WeaponMenu(ResourceBundle resources, WeaponTab tab) {
        myResources = resources;
        myTab = tab;
        //myController = new TowerDataController();
        myHelper = new MenuHelper(myResources);
    }
    
    /**
     * @param nameVal
     * @param projectileVal
     * @param rangeVal
     * @param rateVal
     */
    public void createWeaponMenu(String nameVal, String projectileVal, String rangeVal, String rateVal) {
        myWeaponWindow = new Stage();
        myWeaponWindow.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        setUpTowerScreen(root, nameVal, projectileVal, rangeVal, rateVal);
        Scene scene = new Scene(root, SIZE, SIZE);
        myWeaponWindow.setTitle(myResources.getString("AddTower"));
        myWeaponWindow.setScene(scene);
        myWeaponWindow.show();
    }
    
    private void setUpTowerScreen(VBox root, String nameVal, String projectileVal, String rangeVal, String rateVal) {
        myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
        myProjectileNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterProjectile"), projectileVal);
        myRangeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterRange"), rangeVal);
        myRateField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterFireRate"), rateVal);
    }

}
