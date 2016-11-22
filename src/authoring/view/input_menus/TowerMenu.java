package authoring.view.input_menus;

import java.util.ResourceBundle;
import authoring.controller.TowerDataController;
import authoring.model.TowerData;
import authoring.view.side_panel.TowerTab;
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

/**
 * @author Niklas Sjoquist
 *
 */
public class TowerMenu {
    private static final int SIZE = 500;
    
    private ResourceBundle myResources;
    private TowerTab myTab;
    private TowerDataController myController;
    private MenuHelper myHelper;
    private Stage myTowerWindow;
    private TextField myNameField;
    private TextField myTypeField; // X
    private TextField myHealthField;
    private TextField myBuyPriceField;
    private TextField mySellPriceField;
    private TextField mySizeField;
    private TextField myImageField;
    private TextField mySoundField; // X
    private ComboBox<String> myWeaponChoice;
    //private ComboBox<String> myMovementChoice;
    
    public TowerMenu(ResourceBundle resources, TowerTab tab) {
        myResources = resources;
        myTab = tab;
        myController = new TowerDataController();
        myHelper = new MenuHelper(myResources);
    }
    
    public void createTowerMenu(String nameVal, String typeVal, String healthVal, String buyVal, String sellVal, String sizeVal, String imageVal, String soundVal) {
        myTowerWindow = new Stage();
        myTowerWindow.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        setUpTowerScreen(root, nameVal, typeVal, healthVal, buyVal, sellVal, sizeVal, imageVal, soundVal);
        Scene scene = new Scene(root, SIZE, SIZE);
        myTowerWindow.setTitle(myResources.getString("AddTower"));
        myTowerWindow.setScene(scene);
        myTowerWindow.show();
    }
    
    private void setUpTowerScreen(VBox root, String nameVal, String typeVal, String healthVal, String buyVal, String sellVal, String sizeVal, String imageVal, String soundVal) {
        myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
        //myTypeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterType"), typeVal);
        myHealthField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterHealth"), healthVal);
        myBuyPriceField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterBuyPrice"), buyVal);
        mySellPriceField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSellPrice"), sellVal);
        mySizeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSize"), sizeVal);
        setUpImage(root, imageVal);
        //setUpSound(root, soundVal);
        myWeaponChoice = setUpWeapon(root);
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
    
    private ComboBox<String> setUpWeapon(VBox root) {
        Text weaponText = new Text(myResources.getString("SelectWeapon"));
        ComboBox<String> weaponBox = new ComboBox<String>();
        root.getChildren().addAll(weaponText, weaponBox);
        return weaponBox;
    }
    
    private void setUpFinishButton(VBox root){
        Button finishButton = new Button(myResources.getString("Finish"));
        finishButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event){
                        String name = myNameField.getCharacters().toString();
                        //String type = myTypeField.getCharacters().toString();
                        int health = 0;
                        int buyPrice = 0; int sellPrice = 0;
                        int size = 1;
                        try {
                                health = Integer.parseInt(myHealthField.getCharacters().toString());
                                buyPrice = Integer.parseInt(myBuyPriceField.getCharacters().toString());
                                sellPrice = Integer.parseInt(mySellPriceField.getCharacters().toString());
                                size = Integer.parseInt(mySizeField.getCharacters().toString());
                        } catch(Exception e) {
                                myHelper.showError(myResources.getString("BadDoubleInput"));
                                return;
                        }
                        String weapon = myWeaponChoice.getValue();
                        String image = myImageField.getCharacters().toString();
                        
                        TowerData tower = createTowerData(name, health, buyPrice, sellPrice, size, weapon, image);
                        if (tower == null) {
                            return; // keeps window open
                        }
                        
//                        String sound = mySoundField.getCharacters().toString();
//                        if (sound.substring(sound.length() - 4).equals(".wav")){
//                                tower.setSound(sound);
//                        }
                        
                        myTab.removeButtonDuplicates(name);
                        myTab.addButtonToDisplay(name);
                        myTab.addTower(name);
                        
                        //Commented so it will compile
                       // myController.createTowerData(name, tower);
                        myTowerWindow.close();
                }
        });
        root.getChildren().add(finishButton);
    }
    
    private TowerData createTowerData(String name, int health, int buyPrice, int sellPrice, int size, String weapon, String imagePath) {
        TowerData towerDat = new TowerData();
        
        try {
            towerDat.setName(name);
            towerDat.setMaxHealth(health);
            towerDat.setBuyPrice(buyPrice);
            towerDat.setSellPrice(sellPrice);
            towerDat.setCollisionRadius(size);
            towerDat.setWeaponName(weapon);
            towerDat.setImagePath(imagePath);
        } catch (Exception e) {
            myHelper.showError(e.getMessage());
            return null;
        }
        
        return towerDat;
    }
    
}
