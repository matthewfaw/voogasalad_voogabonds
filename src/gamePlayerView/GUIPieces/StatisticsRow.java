package gamePlayerView.GUIPieces;

import engine.controller.ApplicationController;
import gamePlayerView.Styles;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Guhan Muruganandam
 * 
 */

public class StatisticsRow implements IGUIPiece {
	
	private HBox myStatisticsRow;
	private CashBox myCashBox;
	private LivesBox myLivesBox;
	private WavesBox myWavesBox;
	private ApplicationController myApplicationController;
	
	public StatisticsRow(ApplicationController applicationController){
		myCashBox=new CashBox();
		myLivesBox=new LivesBox();
		myWavesBox =new WavesBox();
		myStatisticsRow= buildHBox();
		myStatisticsRow.setPrefHeight(110);
		myApplicationController=applicationController;
	}
	
	/**
	 * Creates the object that will actually be returned
	 */
	private HBox buildHBox() {
		HBox hbox = new HBox();
		hbox.setPrefWidth(900);
		hbox.setMaxHeight(100);
	    hbox.setPadding(new Insets(10, 10,10, 10));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    HBox InfoBoxes =new HBox();
	    InfoBoxes.setSpacing(20);
	    InfoBoxes.getChildren().addAll(myCashBox.getView(),myLivesBox.getView(),myWavesBox.getView());
	    
	    Button playButton=makeButton("PLAY");
	    playButton.setOnAction(e->myApplicationController.onPlayButtonPressed());
	    Button pauseButton=makeButton("PAUSE");
	    pauseButton.setOnAction(e->myApplicationController.onPauseButtonPressed());
	    Button fastButton=makeButton("FAST FORWARD");
	    fastButton.setOnAction(e->myApplicationController.onFastButtonPressed());
	    Button slowButton=makeButton("SLOW");
	    slowButton.setOnAction(e->myApplicationController.onSlowButtonPressed());
	    //Will probably delete later
	    Button upgradeButton=makeButton("UPGRADE");
	    upgradeButton.setOnAction(e->myApplicationController.onUpgradeButtonPressed());
	    Button sellButton=makeButton("SELL");
	    sellButton.setOnAction(e->myApplicationController.onSellButtonPressed());
	    
	    VBox PlayPauseBox= makeButtonSet(playButton,pauseButton);
	    VBox TowerOptionBox= makeButtonSet(upgradeButton,sellButton);
	    VBox SpeedBox= makeButtonSet(fastButton,slowButton);
	    
	    hbox.getChildren().addAll(PlayPauseBox,InfoBoxes,TowerOptionBox,SpeedBox);

	    return hbox;
	}
	
	private Button makeButton(String string) {
		Button b= new Button(string);
		b.setPrefSize(100, 20);
		//b.setId(value);
		b.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%)");
		return b;
	}
	
	private VBox makeButtonSet(Button button1,Button button2) {
		VBox vbox = new VBox();
		vbox.setSpacing(10);
	    vbox.getChildren().addAll(button1,button2);
		return vbox;
	}
	
	public Node getView() {
		return myStatisticsRow;
	}

	public CashBox getCash() {
		return myCashBox;
	}
	
	public LivesBox getLives() {
		return myLivesBox;
	}
	
	public WavesBox getWaves() {
		return myWavesBox;
	}
}
