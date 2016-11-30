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
    private static final int WIDTH = 400, HEIGHT = 500;
    
    private TextField myNameField;
    private TextField myHealthField;
    private TextField myBuyPriceField;
    private TextField mySellPriceField;
    private TextField mySizeField;
    private TextField myImageField;
    private ComboBox<String> myWeaponChoice;
    //private ComboBox<String> myMovementChoice;
    
    public TowerMenu(ResourceBundle resources, TowerTab tab, TowerDataController towerController) {
        super(resources, tab);
    }
    
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
        Button done = buildDoneButton(true);
        root.getChildren().add(done);
    }
    
    private void setUpEditTowerScreen(VBox root, List<String> objectData) {
        String weapon = objectData.get(objectData.size()-1);
        if (!setupTextFields(root, objectData) || !setupCombo(root, weapon)) {
            return;
        }
        //// No MenuButtons currently
        
        // Create finish button/action
        Button done = buildDoneButton(false);
        root.getChildren().add(done);
    }
    
    /**
     * Builds a Done button which adds a new tower when isNewObject is true, and updates an existing tower when isNewObject is false
     * 
     * @param isNewObject
     * @return reference to created button
     */
    private Button buildDoneButton(boolean isNewObject) {
        Button done = new Button(getResources().getString("Finish"));
        done.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TowerData tower = buildTowerDataFromInputFields();
                
                if (tower == null) {
                    return; // leave window open
                }
                
                String name = tower.getName();
                
                // add/update Tower (tab passes data to controller and updates view)
                try {
                    if (isNewObject) {
                        getTab().addObject(name, tower);
                    } else {
                        getTab().updateObject(name, tower);
                    }
                } catch (Exception e) {
                    getHelper().showError(getResources().getString(e.getMessage()));
                    return; // leave window open
                }
                getWindow().close();
            }
        });
        return done;
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
    
    /**
     * Expects objectData to be structured as follows:
     * 
     * 
     * 
     * @param root
     * @param objectData - expected to contain the following data:
         * index  |  TextField
         * ===================
         *    0   |   name
         *    1   |   health
         *    2   |   buy
         *    3   |   sell
         *    4   |   size
         *    5   |   image
     * @return success/failure
     */
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
    
    private TowerData buildTowerDataFromInputFields() {
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
    
    /**
     * Converts null Integers to -1.
     * 
     * @param value
     * @return value, or -1 if value is null
     */
    private int correctNullInteger(Integer value) {
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
        return;
    }

    @Override
    protected int defineWidth () {
        return WIDTH;
    }

    @Override
    protected int defineHeight () {
        return HEIGHT;
    }
    
}
