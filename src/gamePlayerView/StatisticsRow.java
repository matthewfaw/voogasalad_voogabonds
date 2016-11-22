package gamePlayerView;

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
	    hbox.setPadding(new Insets(10, 0,10, 0));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonPlay = makeButton("Play");
	    Button buttonPause = makeButton("Pause");
	    
	    VBox labelBox= produceLabels();
	    VBox textAreaBox= produceTextArea();
	    
	    VBox TowerOptionBox= makeTowerOptionBox();
	    VBox SpeedBox= makeSpeedBox();
	    
	    Label waveLabel = makeLabel("Wave: ");
	    TextArea waveoutput = new TextArea();
		waveoutput.setMaxWidth(5);
		waveoutput.setMaxHeight(2);
	    
	    hbox.getChildren().addAll(buttonPlay, buttonPause,myCashBox.getView(),myLivesBox.getView(),myWavesBox.getView(),TowerOptionBox,SpeedBox);

	    return hbox;
	}
	
	private Button makeButton(String string) {
		Button b= new Button(string);
		b.setPrefSize(100, 20);
		//b.setStyle(Styles.green);
		return b;
	}

	private VBox makeTowerOptionBox() {
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		Button buttonUpgrade = new Button("Upgrade");
	    buttonUpgrade.setPrefSize(100, 20);
	    Button buttonSell = new Button("Sell");
	    buttonSell.setPrefSize(100, 20);
	    vbox.getChildren().addAll(buttonUpgrade,buttonSell);
		return vbox;
	}
	private VBox makeSpeedBox() {
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		Button buttonForward = new Button("Fast Forward");
	    buttonForward.setPrefSize(100, 20);
	    Button buttonSlow = new Button("Slow");
	    buttonSlow.setPrefSize(100, 20);
	    vbox.getChildren().addAll(buttonForward,buttonSlow);
		return vbox;
	}
	
	private VBox produceTextArea(){
		VBox vbox= new VBox();
		TextArea cashoutput = new TextArea();
		cashoutput.setPrefSize(5, 5);
		TextArea livesoutput = new TextArea();
		livesoutput.setMaxWidth(5);
		livesoutput.setMaxHeight(2);
		//TextArea waveoutput = new TextArea();
		//waveoutput.setMaxWidth(5);
		//waveoutput.setMaxHeight(2);
		
		vbox.getChildren().addAll(cashoutput,livesoutput);
		return vbox;
	}
	
	private VBox produceLabels(){
		Label cashLabel= makeLabel("Cash: ");
		Label livesLabel= makeLabel("Lives: ");
		Label waveLabel = makeLabel("Wave: ");
		VBox labelBunch=new VBox();
		labelBunch.setSpacing(15);
		labelBunch.getChildren().addAll(cashLabel,livesLabel);
		return labelBunch;
	}
	
	private Label makeLabel(String text){
		Label l= new Label(text);
		l.setFont(new Font("Cambria",14));
		return l;
	}
	
	
	public Node getView() {
		return myStatisticsRow;
	}

	public CashBox getCash() {
		return myCashBox;
	}

}
