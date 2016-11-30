package authoring.view.input_menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.TowerDataController;
import authoring.model.TowerData;
import authoring.view.side_panel.TowerTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Niklas Sjoquist
 *
 */
public class TowerMenu extends AbstractMenu {
    private static final int SIZE = 500;
    
    //private ResourceBundle myResources;
    //private TowerTab myTab;
    //private TowerDataController myController;
    //private MenuHelper myHelper;
    //private Stage myTowerWindow;
    private TextField myNameField;
    //private TextField myTypeField; // X
    private TextField myHealthField;
    private TextField myBuyPriceField;
    private TextField mySellPriceField;
    private TextField mySizeField;
    private TextField myImageField;
    //private TextField mySoundField; // X
    private ComboBox<String> myWeaponChoice;
    //private ComboBox<String> myMovementChoice;
    
    public TowerMenu(ResourceBundle resources, TowerTab tab, TowerDataController towerController) {
        super(resources, tab);
//        myResources = resources;
//        myTab = tab;
//        myController = towerController;
//        myHelper = new MenuHelper(myResources);
    }
    
//    public void createTowerMenu(String nameVal, String healthVal, String buyVal, String sellVal, String sizeVal, String imageVal, String weaponVal) {
//        myTowerWindow = new Stage();
//        myTowerWindow.initModality(Modality.APPLICATION_MODAL);
//        VBox root = new VBox();
//        setUpTowerScreen(root, nameVal, healthVal, buyVal, sellVal, sizeVal, imageVal, weaponVal);
//        Scene scene = new Scene(root, SIZE, SIZE);
//        myTowerWindow.setTitle(myResources.getString("AddTower"));
//        myTowerWindow.setScene(scene);
//        myTowerWindow.show();
//    }
//    
//    private void setUpTowerScreen(VBox root, String nameVal, String healthVal, String buyVal, String sellVal, String sizeVal, String imageVal, String weaponVal) {
//        myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
//        //myTypeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterType"), typeVal);
//        myHealthField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterHealth"), healthVal);
//        myBuyPriceField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterBuyPrice"), buyVal);
//        mySellPriceField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSellPrice"), sellVal);
//        mySizeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSize"), sizeVal);
//        setUpImage(root, imageVal);
//        //setUpSound(root, soundVal);
//        myWeaponChoice = setUpWeapon(root, weaponVal);
//        setUpFinishButton(root);
//    }
//    
//    private void setUpImage(VBox root, String value) {
//        myImageField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterImage"), value);
//        myHelper.setUpBrowseButton(root, myImageField, "PNG", "*.png");
//    }
//    
//    private void setUpSound(VBox root, String value) {
//        if (value == null)
//                mySoundField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSound"), 
//                                myResources.getString("DefaultSound"));
//        else
//                mySoundField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSound"), value);
//        myHelper.setUpBrowseButton(root, mySoundField, "WAV", "*.wav");
//    }
//    
//    private ComboBox<String> setUpWeapon(VBox root, String value) {
//        Text weaponText = new Text(myResources.getString("SelectWeapon"));
//        ObservableList<String> weaponChoices = FXCollections.observableArrayList("Machine Gun", "Bomb", "Arrow");
//        ComboBox<String> weaponBox = new ComboBox<String>(weaponChoices);
//        weaponBox.setValue(value);
//        root.getChildren().addAll(weaponText, weaponBox);
//        return weaponBox;
//    }
//    
//    private void setUpFinishButton(VBox root){
//        Button finishButton = new Button(myResources.getString("Finish"));
//        finishButton.setOnAction(new EventHandler<ActionEvent>() {
//                public void handle(ActionEvent event){
//                        String name = myNameField.getCharacters().toString();
//                        //String type = myTypeField.getCharacters().toString();
//                        int health = 0;
//                        int buyPrice = 0; int sellPrice = 0;
//                        int size = 1;
//                        try {
//                                health = Integer.parseInt(myHealthField.getCharacters().toString());
//                                buyPrice = Integer.parseInt(myBuyPriceField.getCharacters().toString());
//                                sellPrice = Integer.parseInt(mySellPriceField.getCharacters().toString());
//                                size = Integer.parseInt(mySizeField.getCharacters().toString());
//                        } catch(Exception e) {
//                                myHelper.showError(myResources.getString("BadIntInput"));
//                                return;
//                        }
//                        String weapon = myWeaponChoice.getValue();
//                        String image = myImageField.getCharacters().toString();
//                        
//                        TowerData tower = createTowerData(name, health, buyPrice, sellPrice, size, weapon, image);
//                        if (tower == null) {
//                            return; // keeps window open
//                        }
//                        
////                        String sound = mySoundField.getCharacters().toString();
////                        if (sound.substring(sound.length() - 4).equals(".wav")){
////                                tower.setSound(sound);
////                        }
//                        
//                        myTab.addTower(name);
//                        myController.createTowerData(tower);
//                        myTowerWindow.close();
//                }
//        });
//        root.getChildren().add(finishButton);
//    }
//    
//    private TowerData createTowerData(String name, int health, int buyPrice, int sellPrice, int size, String weapon, String imagePath) {
//        TowerData towerDat = new TowerData();
//        
//        try {
//            towerDat.setName(name);
//            towerDat.setMaxHealth(health);
//            towerDat.setBuyPrice(buyPrice);
//            towerDat.setSellPrice(sellPrice);
//            towerDat.setCollisionRadius(size);
//            towerDat.setWeaponName(weapon);
//            towerDat.setImagePath(imagePath);
//        } catch (Exception e) {
//            myHelper.showError(e.getMessage());
//            return null;
//        }
//        
//        return towerDat;
//    }
    
    @Override
    protected void setUpNewCreateScreen (VBox root) {
        setUpNewTowerScreen(root);
    }

    @Override
    protected void setUpNewUpdateScreen (VBox root, List<String> objectData) {
        setUpEditTowerScreen(root, objectData);
    }
    
    private void setUpNewTowerScreen(VBox root) {
        // Try to set initial text in TextFields & Weapon ComboBox choices
        if (!setupTextFields(root) || !setupCombo(root)) {
            return;
        }
        //// No MenuButtons in a Tower menu, but these can be added by following setupTextFields/setupCombos
        
        // Create finish button/action
        Button done = new Button(getResources().getString("Finish"));
        done.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TowerData tower = buildTowerDataFromInput();
                
                if (tower == null) {
                    return; // leave window open
                }
                
                String name = tower.getName();
                
                // add Tower (tab passes data to controller and updates view)
                try {
                    getTab().addObject(name, tower);
                } catch (Exception e) {
                    getHelper().showError(e.getMessage());
                    return;
                }
                getWindow().close();
            }
        });
        root.getChildren().add(done);
    }
    
    private void setUpEditTowerScreen(VBox root, List<String> objectData) {
        int last = objectData.size()-1;
        String weapon = objectData.get(last);
        
        if (!setupTextFields(root, objectData) || !setupCombo(root, weapon)) {
            return;
        }
        //// No MenuButtons currently
        
        // Create finish button/action
        Button done = new Button(getResources().getString("Finish"));
        done.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TowerData tower = buildTowerDataFromInput();
                
                if (tower == null) {
                    return; // leave window open
                }
                
                String name = tower.getName();
                
                // update Tower (tab passes data to controller and updates view)
                try {
                    getTab().updateObject(name, tower);
                } catch (Exception e) {
                    getHelper().showError(getResources().getString("EditTowerError"));
                    return; // leave window open
                }
                getWindow().close();
            }
        });
        root.getChildren().add(done);
    }
    
    private boolean setupTextFields(VBox root) {
        List<TextField> fields = this.getInputFields();
        try {
            myNameField = fields.get(0);
            myHealthField = fields.get(1);
            myBuyPriceField = fields.get(2);
            mySellPriceField = fields.get(3);
            mySizeField = fields.get(4);
            myImageField = fields.get(5);
        } catch (Exception e) {
            this.getHelper().showError("TextField inputs could not be generated.");
            return false;
        }
        
        myNameField = this.setUpTextInput(root, getResources().getString("EnterName"), getResources().getString("DefaultTowerName"));
        myHealthField = this.setUpTextInput(root, getResources().getString("EnterHealth"), getResources().getString("DefaultHealth"));
        myBuyPriceField = this.setUpTextInput(root, getResources().getString("EnterBuyPrice"), getResources().getString("DefaultBuyPrice"));
        mySellPriceField = this.setUpTextInput(root, getResources().getString("EnterSellPrice"), getResources().getString("DefaultSellPrice"));
        mySizeField = this.setUpTextInput(root, getResources().getString("EnterSize"), getResources().getString("DefaultSize"));
        myImageField = this.setUpBrowseButton(root, getResources().getString("EnterImage"), getResources().getString("DefaultImage"), "PNG", "*.png");
        
        return true;
    }
    
    private boolean setupTextFields(VBox root, List<String> objectData) {
        try {
            myNameField = this.setUpTextInput(root, getResources().getString("EnterName"), objectData.get(0));
            myHealthField = this.setUpTextInput(root, getResources().getString("EnterHealth"), objectData.get(1));
            myBuyPriceField = this.setUpTextInput(root, getResources().getString("EnterBuyPrice"), objectData.get(2));
            mySellPriceField = this.setUpTextInput(root, getResources().getString("EnterSellPrice"), objectData.get(3));
            mySizeField = this.setUpTextInput(root, getResources().getString("EnterSize"), objectData.get(4));
            myImageField = this.setUpBrowseButton(root, getResources().getString("EnterImage"), objectData.get(5), "PNG", "*.png");
            return true;
        } catch (Exception e) {
            getHelper().showError(getResources().getString("DataRetrieveError"));
            return false;
        }
    }
    
    private boolean setupCombo(VBox root) {
        List<ComboBox<String>> combos = this.getComboBoxes();
        
        try {
            myWeaponChoice = combos.get(0);
        } catch (Exception e) {
            this.getHelper().showError("Weapon ComboBox could not be generated");
            return false;
        }

        // Fake initial values
        ObservableList<String> weaponChoices = FXCollections.observableArrayList("Machine Gun", "Bomb", "Arrow");
        
        String weapon = getResources().getString("SelectWeapon");
        myWeaponChoice = this.setUpComboBox(root, weapon, weaponChoices, weapon);
        return true;
    }
    
    private boolean setupCombo(VBox root, String weaponValue) {
        if (weaponValue == null) {
            return false;
        }
        
        // Fake initial values
        ObservableList<String> weaponChoices = FXCollections.observableArrayList("Machine Gun", "Bomb", "Arrow");
        
        String weapon = getResources().getString("SelectWeapon");
        myWeaponChoice = this.setUpComboBox(root, weapon, weaponChoices, weaponValue);
        
        return true;
    }
    
    private TowerData buildTowerDataFromInput() {
        // Parse number input
        
        Integer health = null, buyPrice = null, sellPrice = null, size = null;
        
        try {
            health = Integer.parseInt(myHealthField.getCharacters().toString());
            buyPrice = Integer.parseInt(myBuyPriceField.getCharacters().toString());
            sellPrice = Integer.parseInt(mySellPriceField.getCharacters().toString());
            size = Integer.parseInt(mySizeField.getCharacters().toString());
        } catch (Exception e) {
            return null;
        }
        
        if (!parseNumberInput(health,buyPrice,sellPrice,size)) {
            // If number parsing fails, show error and return
            getHelper().showError(getResources().getString("BadIntInput"));
            return null;
        }
        
        // Parse text input fields
        
        String name = myNameField.getCharacters().toString();
        String image = myImageField.getCharacters().toString();
        String weapon = myWeaponChoice.getValue();
        
        // Package Tower Data
        
        // Error check input
        Integer[] nums = {health,buyPrice,sellPrice,size};
        for (int i=0; i<nums.length; i++) {
            nums[i] = correctNullInteger(nums[i]); // make null Integers = -1
        }
        health = nums[0]; buyPrice = nums[1]; sellPrice = nums[2]; size = nums[3];
        
        System.out.println(image);
        
        TowerData tower = createTowerData(name, health.intValue(), buyPrice.intValue(), sellPrice.intValue(), size.intValue(), image, weapon);
        if (tower == null) {
            return null;
        }
        return tower;
    }
    
    /**
     * Parses numbers and returns false if failed or numbers are null.
     */
    private boolean parseNumberInput(Integer health, Integer buyPrice, Integer sellPrice, Integer size) {
        try {
            health = Integer.parseInt(myHealthField.getCharacters().toString());
            buyPrice = Integer.parseInt(myBuyPriceField.getCharacters().toString());
            sellPrice = Integer.parseInt(mySellPriceField.getCharacters().toString());
            size = Integer.parseInt(mySizeField.getCharacters().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private int correctNullInteger(Integer value) {
        if (value == null) {
            System.out.println("Integer was null.");
        }
        return value == null ? -1 : value.intValue();
    }
    
    private TowerData createTowerData(String name, int health, int buyPrice, int sellPrice, int size, String imagePath, String weapon) {
      TowerData towerDat = new TowerData();
      
      try {
          towerDat.setName(name);
          towerDat.setMaxHealth(health);
          towerDat.setBuyPrice(buyPrice);
          towerDat.setSellPrice(sellPrice);
          towerDat.setCollisionRadius(size);
          towerDat.setImagePath(imagePath);
          towerDat.setWeaponName(weapon);
      } catch (Exception e) {
          this.getHelper().showError(e.getMessage());
          return null;
      }
      
      return towerDat;
  }

    @Override
    protected String getTitle () {
        return getResources().getString("AddTower");
    }

    /* 
     * TextField array:
     * index  |  TextField
     * ===================
     *    0   |   name
     *    1   |   health
     *    2   |   buy
     *    3   |   sell
     *    4   |   size
     *    5   |   image
     */
    @Override
    protected List<TextField> defineTextFields () {
        List<TextField> textFields = new ArrayList<>();
        
        textFields.add(myNameField);
        textFields.add(myHealthField);
        textFields.add(myBuyPriceField);
        textFields.add(mySellPriceField);
        textFields.add(mySizeField);
        textFields.add(myImageField);
        
        return textFields;
    }

    /* 
     * One combo box for weapon choice
     */
    @Override
    protected List<ComboBox<String>> defineComboBoxes () {
        List<ComboBox<String>> comboBoxes = new ArrayList<>();
        comboBoxes.add(myWeaponChoice);
        return comboBoxes;
    }

    @Override
    protected List<MenuButton> defineMenuButtons () {
        // No check boxes
        return null;
    }

    @Override
    protected void checkForErrors (MenuHelper helper) {
        // Currently no need, can generate a Tower whenever
        
    }
    
}
