/*package gamePlayerView.GUIPieces.MachineInfoView;

import java.util.HashMap;
import java.util.List;

import authoring.model.TowerData;
import engine.controller.ApplicationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class MachineUpgradeOrSell {
	private HBox myDisplay;
	private ApplicationController myAppController;
	private HashMap towerMapping;
	
	public MachineUpgradeOrSell(TowerData tower,ApplicationController applicationController){
		myDisplay=makeDisplay(tower);
		myAppController=applicationController;
		towerMapping=new HashMap<ImageView,TowerData>();
	}

	private HBox makeDisplay(TowerData tower) {
		HBox upgradeOrSell=new HBox();
		Button SellButton=new Button("Sell");
		SellButton.setPrefSize(100, 20);
		SellButton.setOnAction(e->myAppController.onSellButtonPressed(tower));
		
		ListView upgrades=new ListView();
		List<ImageView> availableTowerUpgrades = tower.getUpgrades(); ///QUESTONABLE ASK ABOUT THIS MAP;
		//TODO: CLEAN
		ObservableList<ImageView> items =FXCollections.observableArrayList();
		towerMapping.clear();
		for(TowerData t : availableTowerUpgrades){
			ImageView towerPicture = new ImageView();
			String imagePath = t.getImagePath();
			//TODO: make this cleaner--hard coded now
			if (imagePath.substring(0, 4).equals("src/")) {
				imagePath = imagePath.substring(4);
			}
			Image towerImage = new Image(this.getClass().getClassLoader().getResourceAsStream(imagePath)); //THIS IS IFFY. COME BACK TO THIS
			towerPicture.setImage(towerImage);
			towerMapping.put(towerPicture,t);
			items.add(towerPicture);
		}
        upgrades.setItems(items);
        //setDragFunctionality(towerInfo);
        //setPopulateFunctionality(towerInfo,towerDataDisplay);
        upgrades.getChildren().clear();
        
        Label heading = new Label("TOWERS");
	    heading.setFont(new Font("Cambria",18));
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(towerInfo, "Towers"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	    upgrades.getChildren().addAll(heading,resourceTabs,towerDataDisplay);
		//return upgradeOrSell;
		
		
	}
}
*/