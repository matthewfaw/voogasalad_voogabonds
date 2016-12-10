package authoring.view.side_panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.IDataContainer;
import authoring.controller.TowerDataContainer;
import authoring.model.IReadableData;
import authoring.controller.Container;
import authoring.controller.MapDataContainer;
import authoring.controller.TowerDataContainer;
import authoring.controller.WeaponDataContainer;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.view.input_menus.AbstractMenu;
import authoring.view.input_menus.TowerMenu;
import engine.IObservable;
import engine.IObserver;
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

@Deprecated
/**
 * @author Christopher Lu
 * @modifier Niklas Sjoquist
 * Implements the tab that allows user to add, delete, or edit preexisting towers.
 */

public class TowerTab extends AbstractInfoTab implements IObserver<Container> {

    //private TowerMenu myMenu;
    //private TowerDataController myTowerController;
    private ObservableList<String> myTowers, myWeapons, myTerrains;
    private ListView<String> towers;

    public TowerTab(TabPane pane, TowerDataContainer controller) {
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
    public ObservableList<String> getTowers() {
        return myTowers;
    }
    
    public ObservableList<String> getTerrains() {
        return myTerrains;
    }
    
    public ObservableList<String> getWeapons() {
        return myWeapons;
    }

    @Override
    protected String getTitle () {
        return getResources().getString("Towers");
    }

    @Override
    protected AbstractMenu initMenu (ResourceBundle resources, IDataContainer controller) {
        return new TowerMenu(resources, this, (TowerDataContainer)controller);
    }

    @Override
    protected List<ObservableList<String>> defineObservableLists () {
        myTowers = FXCollections.observableArrayList();
        myWeapons = FXCollections.observableArrayList();
        myTerrains = FXCollections.observableArrayList();

        return Arrays.asList(myTowers, myWeapons, myTerrains);
    }

    @Override
    protected IReadableData generateDefaultData () {
        TowerData defaultTower = new TowerData();
        try {
            defaultTower.setName(getResources().getString("DefaultTowerName"));
            defaultTower.setMaxHealth(Double.parseDouble(getResources().getString("DefaultBuyPrice")));
            defaultTower.setBuyPrice(Integer.parseInt(getResources().getString("DefaultBuyPrice")));
            defaultTower.setSellPrice(Integer.parseInt(getResources().getString("DefaultSellPrice")));
            defaultTower.setImagePath(getResources().getString("DefaultImage"));
            defaultTower.setWeaponName(getResources().getString("DefaultWeapon"));
            return defaultTower;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addObject (String name, IReadableData data) {
        this.addTower(name);
        try {
            getController().createObjectData(data);
        } catch (Exception e) {
            getMenu().showError(e.getMessage());
        }
    }

    @Override
    public void updateObject (String oldName, IReadableData data) {
        // TODO: handle more errors
        try {
            getController().updateObjectData(oldName, data);
        } catch (Exception e) {
            getMenu().showError(e.getMessage());
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
        TowerData td = ((TowerDataContainer)getController()).getTowerData(tower);
        getMenu().createObjectMenu(td);
    }

    /**
     * IObservable method
     */
    @Override
    public void update(Container c){
            if (c instanceof WeaponDataContainer){
                    myWeapons.clear();
                    for (String weaponName: ((WeaponDataContainer) c).getWeaponDataMap().keySet()){
                            myWeapons.add(weaponName);
                    }
            }else if (c instanceof MapDataContainer){
                    myTerrains.clear();
                    for (String terrainName: ((MapDataContainer) c).getValidTerrainMap().keySet()){
                            myTerrains.add(terrainName);
                    }
            }
    }
    
}
