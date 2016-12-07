package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import authoring.controller.WeaponDataContainer;
import authoring.model.EnemyData;
import authoring.model.WeaponData;
import authoring.view.input_menus.WeaponMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeaponTab extends Tab {
    
    private ResourceBundle myResources;
    private String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private Tab weaponTab;
    private int screenWidth;
    private int screenHeight;
    private WeaponMenu myMenu;
    private WeaponDataContainer myController;
    private VBox myContent;
    

    public WeaponTab(TabPane pane, WeaponDataContainer controller) {
        screenInfo();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
        weaponTab = new Tab(myResources.getString("Weapons"));
        myMenu = new WeaponMenu(myResources, this);
        myController = controller;
        weaponTabOptions(weaponTab);
        pane.getTabs().add(weaponTab);
    }
    
    private void weaponTabOptions(Tab weaponTab) {
        VBox weaponArea = new VBox(screenHeight*0.01);
        weaponArea.setMaxHeight(screenHeight*0.88);
        ScrollPane currentWeapons = new ScrollPane();
        myContent = new VBox();
        currentWeapons.setContent(myContent);
        currentWeapons.setPrefSize(screenWidth/5, screenHeight);
        HBox weaponButtons = new HBox(screenWidth*0.05);
        weaponButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
        Button addWeapon = new Button(myResources.getString("AddWeapon"));
        addWeapon.setOnAction(addWeaponHandler());
        weaponButtons.getChildren().add(addWeapon);
        weaponArea.getChildren().addAll(currentWeapons, weaponButtons);
        weaponTab.setContent(weaponArea);
    }

    private EventHandler<ActionEvent> addWeaponHandler(){
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event){
                        // TODO: update create window method
                        myMenu.createWeaponMenu(myResources.getString("DefaultWeapon"), myResources.getString("DefaultProjectile"),
                                                myResources.getString("DefaultRange"), myResources.getString("DefaultRate"), true);
                }
        };
        return handler;
    }

    private void screenInfo() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }
    
    public WeaponDataContainer getController(){
    	return myController;
    }
    
    public void removeButtonDuplicates(String s) {
		for (int i = 0; i < myContent.getChildren().size(); i++) {
			Button button = (Button) (myContent.getChildren().get(i));
			if (button.getText().equals(s)) {
				myContent.getChildren().remove(i);
				i--;
			}
		}
	}
	
	public void addButtonToDisplay(String text) {
		Button button = new Button(text);
		button.setMinWidth(myContent.getMinWidth());
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				WeaponData weapon = myController.getWeaponData(text);
				myMenu.createWeaponMenu(weapon.getName(), weapon.getProjectileName(), 
						String.valueOf(weapon.getRange()), String.valueOf(weapon.getFireRate()), false);
			}
		});
		myContent.getChildren().add(button);
	}
	
	
    
}
