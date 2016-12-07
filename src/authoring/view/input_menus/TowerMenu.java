package authoring.view.input_menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.TowerDataContainer;
import authoring.model.IReadableData;
import authoring.model.TowerData;
import authoring.view.side_panel.TowerTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    private MenuButton myPossibleTerrains;
    private Button myFileBrowser;
    
    public TowerMenu(ResourceBundle resources, TowerTab tab, TowerDataContainer towerController) {
        super(resources, tab);
    }
    
    @Override
    protected void setUpNewCreateScreen (VBox root) {
        setUpNewTowerScreen(root);
    }

    @Override
    protected void setUpNewUpdateScreen (VBox root, IReadableData objectData) {
        setUpEditTowerScreen(root, objectData);
    }
    
    private void setUpNewTowerScreen(VBox root) {
        // Try to set initial text in TextFields & Weapon ComboBox choices
        if (!setupTextFields(root) || !setupCombo(root) || !setupMenuButton(root)) {
            this.showError("Could not set up new menu");
            return;
        }
        
        // Create finish button/action
        Button done = buildDoneButton(true);
        root.getChildren().add(done);
    }
    
    private void setUpEditTowerScreen(VBox root, IReadableData objectData) {
        TowerData td = (TowerData) objectData;
        String weapon = td.getWeaponName();
        List<String> terrains = td.getTraversableTerrain();
        if (!setupTextFields(root, objectData) || !setupCombo(root, weapon) || !setupMenuButton(root, terrains)) {
            this.showError("Could not set up edit menu");
            return;
        }
        
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
                System.out.println(isNewObject);
                try {
                    if (isNewObject) {
                        getTab().addObject(name, tower);
                    } else {
                        getTab().updateObject(name, tower);
                    }
                } catch (Exception e) {
                    showError(getResources().getString(e.getMessage()));
                    return; // leave window open
                }
                getWindow().close();
            }
        });
        return done;
    }
    
    private void setToDefaultFields() {
        myNameField = this.setUpTextInput(getResources().getString("DefaultTowerName"));
        myHealthField = this.setUpTextInput(getResources().getString("DefaultHealth"));
        myBuyPriceField = this.setUpTextInput(getResources().getString("DefaultBuyPrice"));
        mySellPriceField = this.setUpTextInput(getResources().getString("DefaultSellPrice"));
        mySizeField = this.setUpTextInput(getResources().getString("DefaultSize"));
        myImageField = this.setUpTextInput(getResources().getString("DefaultImage"));
    }
    
    private boolean setupTextFields(VBox root) {
        setToDefaultFields();
        
        // Create text labels
        Text nameLbl = this.setUpLabel(getResources().getString("EnterName"));
        Text healthLbl = this.setUpLabel(getResources().getString("EnterHealth"));
        Text buyLbl = this.setUpLabel(getResources().getString("EnterBuyPrice"));
        Text sellLbl = this.setUpLabel(getResources().getString("EnterSellPrice"));
        Text sizeLbl = this.setUpLabel(getResources().getString("EnterSize"));
        Text imageLbl = this.setUpLabel(getResources().getString("EnterImage"));
        
        // Create button for file browser
        myFileBrowser = this.setUpBrowseButton(myImageField, "PNG", "*.png");
        
        root.getChildren().addAll(nameLbl, myNameField,
                                  healthLbl, myHealthField,
                                  buyLbl, myBuyPriceField,
                                  sellLbl, mySellPriceField,
                                  sizeLbl, mySizeField,
                                  imageLbl, myImageField, myFileBrowser);
        
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
    private boolean setupTextFields(VBox root, IReadableData objectData) {
        TowerData td = (TowerData) objectData;
        try {
            myNameField = this.setUpTextInput(td.getName());
            myHealthField = this.setUpTextInput(td.getMaxHealth()+"");
            myBuyPriceField = this.setUpTextInput(td.getBuyPrice()+"");
            mySellPriceField = this.setUpTextInput(td.getSellPrice()+"");
            mySizeField = this.setUpTextInput("1"); // Default size = 1
            myImageField = this.setUpTextInput(td.getImagePath());
        } catch (Exception e) {
            return false;
        }
        
        // Create text labels
        Text nameLbl = this.setUpLabel(getResources().getString("EnterName"));
        Text healthLbl = this.setUpLabel(getResources().getString("EnterHealth"));
        Text buyLbl = this.setUpLabel(getResources().getString("EnterBuyPrice"));
        Text sellLbl = this.setUpLabel(getResources().getString("EnterSellPrice"));
        Text sizeLbl = this.setUpLabel(getResources().getString("EnterSize"));
        Text imageLbl = this.setUpLabel(getResources().getString("EnterImage"));

        // Create button for file browser
        myFileBrowser = this.setUpBrowseButton(myImageField, "PNG", "*.png");
        
        root.getChildren().addAll(nameLbl, myNameField, 
                                  healthLbl, myHealthField, 
                                  buyLbl, myBuyPriceField, 
                                  sellLbl, mySellPriceField, 
                                  sizeLbl, mySizeField, 
                                  imageLbl, myImageField, myFileBrowser);
        
        return true;
    }
    
    private boolean setupCombo(VBox root) {
        List<ComboBox<String>> combos = this.getComboBoxes();
        try {
            myWeaponChoice = combos.get(0);
        } catch (Exception e) {
            return false;
        }
        
        // Set up label
        Text weaponLbl = this.setUpLabel(getResources().getString("SelectWeapon"));
        root.getChildren().addAll(weaponLbl, myWeaponChoice);
        return true;
    }
    
    private boolean setupCombo(VBox root, String weaponValue) {
        if (weaponValue == null) {
            return false;
        }
        List<ComboBox<String>> combos = this.getComboBoxes();
        try {
            myWeaponChoice = combos.get(0);
        } catch (Exception e) {
            return false;
        }
        
        // Set up label
        Text weaponLbl = this.setUpLabel(getResources().getString("SelectWeapon"));
        // Set given value
        myWeaponChoice.setValue(weaponValue);
        root.getChildren().addAll(weaponLbl, myWeaponChoice);
        return true;
    }
    
    private boolean setupMenuButton(VBox root) {
        List<MenuButton> menuBtns = this.getMenuButtons();
        try {
            myPossibleTerrains = menuBtns.get(0);
        } catch (Exception e) {
            return false;
        }
        
        // Set up label 
        Text terrainLbl = this.setUpLabel(getResources().getString("SelectTerrains"));
        
        // Populate check box options
        myPossibleTerrains = new MenuButton(getResources().getString("SelectTerrains"));
        for (String terrain: ((TowerTab)getTab()).getTerrains()){
                CheckBox checkBox = new CheckBox(terrain);
                CustomMenuItem custom = new CustomMenuItem(checkBox);
                myPossibleTerrains.getItems().add(custom);
        }
        
        root.getChildren().addAll(terrainLbl, myPossibleTerrains);
        
        return true;
    }
    
    private boolean setupMenuButton(VBox root, List<String> checkedTerrains) {
        List<MenuButton> menuBtns = this.getMenuButtons();
        try {
            myPossibleTerrains = menuBtns.get(0);
        } catch (Exception e) {
            return false;
        }
        
        // Set up label
        Text terrainLbl = this.setUpLabel(getResources().getString("SelectTerrains"));
        
        // Populate check box options and set checked values
        myPossibleTerrains = new MenuButton(getResources().getString("SelectTerrains"));
        for (String terrain: ((TowerTab)getTab()).getTerrains()){
                CheckBox checkBox = new CheckBox(terrain);
                if (checkedTerrains != null)
                	checkBox.setSelected(checkedTerrains.contains(terrain));
                CustomMenuItem custom = new CustomMenuItem(checkBox);
                myPossibleTerrains.getItems().add(custom);
        }
        
        root.getChildren().addAll(terrainLbl, myPossibleTerrains);
        
        return true;
    }
    
    private TowerData buildTowerDataFromInputFields() {
        // Parse number input
        Number health = null, buyPrice = null, sellPrice = null, size = null;
        
        try {
            health = Double.parseDouble(myHealthField.getCharacters().toString());
            buyPrice = Integer.parseInt(myBuyPriceField.getCharacters().toString());
            sellPrice = Integer.parseInt(mySellPriceField.getCharacters().toString());
            size = Integer.parseInt(mySizeField.getCharacters().toString());
        } catch (Exception e) {
            this.showError(e.getMessage());
            return null;
        }
        
        if (!parseNumberInput(health,buyPrice,sellPrice,size)) {
            // If number parsing fails, show error and return
            this.showError(getResources().getString("BadIntInput"));
            return null;
        }
        
        // Parse text input fields
        
        String name = myNameField.getCharacters().toString();
        String image = myImageField.getCharacters().toString();
        String weapon = myWeaponChoice.getValue();
        
        // Get checked terrains
        List<String> terrains = new ArrayList<String>();
        for (MenuItem item: myPossibleTerrains.getItems()){
                CustomMenuItem custom = (CustomMenuItem) item;
                CheckBox check = (CheckBox) custom.getContent();
                if (check.isSelected()){
                    System.out.println("Is Selected");
                        terrains.add(check.getText());
                }
        }
        if (terrains.size() == 0){
                showError(getResources().getString("NoTerrainInput"));
                return null;
        }
        
        // Package Tower Data
        
        // Error check input
        Number[] nums = {health,buyPrice,sellPrice,size};
        for (int i=0; i<nums.length; i++) {
            nums[i] = correctNullInteger(nums[i]); // make null Integers = -1
        }
        health = nums[0]; buyPrice = nums[1]; sellPrice = nums[2]; size = nums[3];
        
        TowerData tower = createTowerData(name, health.doubleValue(), buyPrice.intValue(), sellPrice.intValue(), size.intValue(), image, weapon, terrains);
        if (tower == null) {
            return null;
        }
        return tower;
    }
    
    /**
     * Parses numbers and returns false if failed or numbers are null.
     */
    private boolean parseNumberInput(Number health, Number buyPrice, Number sellPrice, Number size) {
        try {
            health = Double.parseDouble(myHealthField.getCharacters().toString());
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
    private int correctNullInteger(Number value) {
        return value == null ? -1 : value.intValue();
    }
    
    private TowerData createTowerData(String name, double health, int buyPrice, int sellPrice, int size, String imagePath, String weapon, List<String> terrains) {
      TowerData towerDat = new TowerData();
      try {
          towerDat.setName(name);
          towerDat.setMaxHealth(health);
          towerDat.setBuyPrice(buyPrice);
          towerDat.setSellPrice(sellPrice);
          towerDat.setCollisionRadius(size);
          towerDat.setImagePath(imagePath);
          towerDat.setWeaponName(weapon);
          towerDat.setTraversableTerrain(terrains);
      } catch (Exception e) {
          this.showError("Could not create Tower Data");
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
        // Weapons
        
        ObservableList<String> weaponChoices = ((TowerTab)getTab()).getWeapons();
        String value = getResources().getString("SelectWeapon");
        myWeaponChoice = this.setUpComboBox(weaponChoices, value);
        
        List<ComboBox<String>> comboBoxes = new ArrayList<>();
        comboBoxes.add(myWeaponChoice);
        return comboBoxes;
    }

    @Override
    protected List<MenuButton> defineMenuButtons () {
        // Terrain
        List<MenuButton> menuBtns = new ArrayList<>();
        menuBtns.add(myPossibleTerrains);
        return menuBtns;
    }

    @Override
    protected List<Button> defineBrowserButtons () {
        // Image browser
        List<Button> browsers = new ArrayList<>();
        browsers.add(myFileBrowser);
        return browsers;
    }

    @Override
    protected void checkForErrors () {
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
