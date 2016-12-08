package gamePlayerView.ScenePanes;

import java.util.Collection;

import engine.controller.ApplicationController;
import gamePlayerView.Styles;
import gamePlayerView.GUIPieces.MachineInfoView.UpgradeOrSell;
import gamePlayerView.GUIPieces.MachineInfoView.MachineInfo;
import gamePlayerView.GUIPieces.MachineInfoView.TargetingButtons;
import gamePlayerView.GUIPieces.MachineInfoView.TowerStatisticsandOptions;
import gamePlayerView.interfaces.IGUIPiece;
import gamePlayerView.interfaces.IViewPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Guhan Muruganandam
 * 
 */

public class BottomPane implements IGUIPiece,IViewPane {
	private ApplicationController myAppController;
	private HBox myBottomPane=new HBox();
	private ApplicationController myApplicationController;
	
	public BottomPane(ApplicationController aAppController){
    	myAppController = aAppController;
    	setUpPane();
		myBottomPane.setPrefHeight(110);
		//myApplicationController=applicationController;
	}
	
	/**
	 * Creates the object that will actually be returned
	 */
	public void setUpPane() {
		myBottomPane.setPrefWidth(900);
		myBottomPane.setMaxHeight(100);
		myBottomPane.setPadding(new Insets(10, 10,10, 10));
		myBottomPane.setSpacing(10);
		myBottomPane.setStyle("-fx-background-color: #993384;");
	    TowerStatisticsandOptions myTowerOptions=new TowerStatisticsandOptions();
	    UpgradeOrSell myUpgradeandSell=new UpgradeOrSell();
	    //MachineInfo myMachineView=new MachineInfo();
	    myBottomPane.getChildren().addAll(/*myMachineView.getView(),*/myTowerOptions.getView(),myUpgradeandSell.getView());
	}
	
	public Node getView() {
		return myBottomPane;
	}

	@Override
	public void add(Collection<Node> collection) {
		for(Node n:collection){
			myBottomPane.getChildren().add(n);
		}
	}

	@Override
	public void clear() {
		myBottomPane.getChildren().clear();
	}
}
