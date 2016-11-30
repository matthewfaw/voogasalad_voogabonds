package authoring.view.side_panel;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.IDataController;
import authoring.controller.TowerDataController;
import authoring.model.IReadableData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.view.input_menus.AbstractMenu;
import authoring.view.input_menus.TowerMenu;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * @author Christopher Lu
 * @modifier Niklas Sjoquist
 * Implements the tab that allows user to add, delete, or edit preexisting towers.
 */

public class TowerTab extends AbstractInfoTab {

    //private TowerMenu myMenu;
    //private TowerDataController myTowerController;
    private ObservableList<String> myTowers, myWeapons, myTerrains;
    private ListView<String> towers;

    public TowerTab(TabPane pane, TowerDataController controller) {
        super(pane, controller);
    }

    /**
     * Adds a new tower to the tab's list view
     * 
     * @param name of tower
     */
    public void addTower(String name) {
        myTowers.add(name);
    }

    /**
     * @return list of the names of the current towers
     */
    public List<String> getTowers() {
        return myTowers;
    }

    @Override
    protected String getTitle () {
        return getResources().getString("Towers");
    }

    @Override
    protected AbstractMenu initMenu (ResourceBundle resources, IDataController controller) {
        return new TowerMenu(resources, this, (TowerDataController)controller);
    }

    @Override
    protected List<ObservableList<String>> defineObservableLists () {
        myTowers = FXCollections.observableArrayList();
        myWeapons = FXCollections.observableArrayList();
        myTerrains = FXCollections.observableArrayList("Land","Water");

        return Arrays.asList(myTowers, myWeapons, myTerrains);
    }

    /**
     * Extracts the necessary data from the TowerData object and returns it as a list of strings.
     * 
     * @return List of Strings defined as follows:
     * index    |   data
     * =====================
     *   0      |   name
     *   1      |   health
     *   2      |   buyPrice
     *   3      |   sellPrice
     *   4      |   imagePath
     *   5      |   weapon
     */
    private List<String> extractTowerData(TowerData td) {
        // TODO: currently always has size = 1
        String sizeVal = "1"; 
        String nameVal, imageVal, healthVal, buyVal, sellVal, weaponVal;
        // TODO: cleanup using modifyNulls?
        try {
            nameVal = td.getName();
        } catch (NullPointerException e) {
            nameVal = "null";
        }
        try {
            imageVal = td.getImagePath();
        } catch (NullPointerException e) {
            imageVal = "null";
        }
        try {
            healthVal = td.getMaxHealth()+"";
        } catch (NullPointerException e) {
            healthVal = "null";
        }
        try {
            buyVal = td.getBuyPrice()+"";
        } catch (NullPointerException e) {
            buyVal = "null";
        }
        try {
            sellVal = td.getSellPrice()+"";
        } catch (NullPointerException e) {
            sellVal = "null";
        }
        try {
            weaponVal = td.getWeaponName();
        } catch (NullPointerException e) {
            weaponVal = "null";
        }

        List<String> inputDefaults = Arrays.asList(nameVal, healthVal, buyVal, sellVal, sizeVal, imageVal, weaponVal);
        return inputDefaults;
    }

    @Override
    protected List<String> generateInputDefaults () {
        return Arrays.asList(getResources().getString("DefaultTowerName"), 
                             getResources().getString("DefaultHealth"), getResources().getString("DefaultBuyPrice"), 
                             getResources().getString("DefaultSellPrice"), getResources().getString("DefaultSize"), 
                             getResources().getString("DefaultImage"), getResources().getString("DefaultWeapon"));
    }

    @Override
    public void addObject (String name, IReadableData data) {
        this.addTower(name);
        getController().createObjectData(data);
    }

    @Override
    public void updateObject (String oldName, IReadableData data) {
        // TODO: handle more errors
        try {
            getController().updateObjectData(oldName, data);
        } catch (Exception e) {
            getMenu().getHelper().showError(e.getMessage());
        }
    }

    // Further Helpers

    /**
     * Checks for null strings in the array and changes them to "null"
     */
    private String[] modifyNulls(String[] arr) {
        // Converts the array into a stream and applies the map function to each element in arr
        // Simply maps each string in arr to itself, or "null" if the element is null
        return Arrays.stream(arr).map(s -> nullToString(s)).toArray(String[]::new);
    }
    private String nullToString(String s) {
        return (s == null) ? "null" : s;
    }

    @Override
    protected void setContent (VBox content) {
        towers = initListView();
        content.getChildren().add(towers);
    }

    private ListView<String> initListView() {
        // Initialize ListView of towers ObservableList
        ListView<String> lv = new ListView<String>(myTowers);
        lv.setOrientation(Orientation.VERTICAL);
        lv.setPrefSize(getWidth()/5, getHeight());
        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 && lv.getSelectionModel().getSelectedItem()!=null) {
                    editTower(lv.getSelectionModel().getSelectedItem());
                }
            }
        });

        return lv;
    }

    private void editTower(String tower) {
        TowerData td = ((TowerDataController)getController()).getTowerData(tower);
        List<String> towerData = extractTowerData(td);
        getMenu().createObjectMenu(false, towerData);
    }

    public MapChangeListener<String, WeaponData> createWeaponListener(){
        MapChangeListener<String, WeaponData> listener = new MapChangeListener<String, WeaponData>() {
            @Override
            public void onChanged(MapChangeListener.Change<? extends String, ? extends WeaponData> change) {
                if (change.wasAdded()){
                    myWeapons.add(change.getKey());
                }
                else if (change.wasRemoved()){
                    myWeapons.remove(change.getKey());
                }
            }
        };
        return listener;
    }

    public SetChangeListener<String> createTerrainListener(){
        SetChangeListener<String> listener = new SetChangeListener<String>() {
            @Override
            public void onChanged(SetChangeListener.Change<? extends String> change) {
                if (change.wasAdded()){
                    myTerrains.add(change.getElementAdded());
                }
                else if (change.wasRemoved()){
                    myTerrains.remove(change.getElementRemoved());
                }
            }
        };
        return listener;
    }

}
