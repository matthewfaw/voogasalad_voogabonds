package gamePlayerView.GUIPieces;

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
	
	public StatisticsRow(){
		myCashBox=new CashBox();
		myLivesBox=new LivesBox();
		myWavesBox =new WavesBox();
		myStatisticsRow= buildHBox();
		myStatisticsRow.setPrefHeight(110);
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
	    VBox PlayPauseBox= makeButtonSet("Play","Pause");
	    VBox TowerOptionBox= makeButtonSet("Upgrade","Sell");
	    VBox SpeedBox= makeButtonSet("Fast-Forward","Slow");
	    
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
	
	private VBox makeButtonSet(String s1,String s2) {
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		Button button1 = makeButton(s1);
	    Button button2 = makeButton(s2);
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
