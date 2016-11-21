package gamePlayerView;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//import resources.AppResources;
import javafx.scene.text.Text;


/**
 * @author Guhan Muruganandam & Grayson Wise
 * 
 */

public class TowerColumn implements IGUIPiece {
	
	//private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	private VBox myTowerColumn;
	private ListView<ImageView> towerInfo;
	private ImageView towerToBeDragged;
	private TextArea towerDataDisplay= new TextArea();

	
	public TowerColumn(){
		myTowerColumn= buildVBox();
	}
	
	/**
	 * Builds object that will actually be returned
	 */
	private VBox buildVBox() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(200);
		vbox.setPrefHeight(600);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.setStyle("-fx-background-color: #778899;");
	    
	    Label l = new Label("TOWERS");
	    l.setFont(new Font("Cambria",18));
	    
	    towerInfo= new ListView<ImageView>();
	    
	    ObservableList<ImageView> items =FXCollections.observableArrayList();
	    ImageView imgV1 = new ImageView();
	    Image imagecow = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/cow.png"));
	    imgV1.setImage(imagecow);
	    items.add(imgV1);
	    ImageView imgV2 = new ImageView();
        Image imagecookie = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/cookie.png"));
        imgV2.setImage(imagecookie);
        items.add(imgV2);
        ImageView imgV3 = new ImageView();
        Image imageboss = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/boss.png"));
        imgV3.setImage(imageboss);
        items.add(imgV3);
            
        towerInfo.setItems(items);
        setDragFunctionality();
        setPopulateFunctionality();
	    
	    ListView<String> resourceInfo=new ListView<String>();
	    ObservableList<String> otheritems =FXCollections.observableArrayList("Extra Gold", "Other crap", "Fe", "Fly");
	    resourceInfo.setItems(otheritems);
	    resourceInfo.setTooltip(new Tooltip("Tooltip for Button"));
	    resourceInfo.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent me) {
	            //TODO:
	        }
	    });
	    
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(towerInfo, "Towers"));
	    resourceTabs.getTabs().add(buildTab(resourceInfo, "Resources"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	    
	    vbox.getChildren().addAll(l,resourceTabs,towerDataDisplay);
	    
	    return vbox;
	}

	private void setPopulateFunctionality() {
		towerInfo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                ImageView towerChosen = towerInfo.getSelectionModel().getSelectedItem();
                PopulateTowerDataDisplay("BOOM");
            }  
        });
	}

	private void setDragFunctionality() {
		towerInfo.setOnDragDetected(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Dragboard db = towerInfo.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                towerToBeDragged = towerInfo.getSelectionModel().getSelectedItem();
                content.putImage(towerToBeDragged.getImage());
                db.setContent(content);
                event.consume();
            }  
        });
	}

	private void PopulateTowerDataDisplay(String s) {
		String info= new String(s);
		towerDataDisplay.setText(info);
	}

	private Button makeTowerImage(String string, ImageView imageView) {
		Button b =new Button(string,imageView);
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent me) {
	        	Tooltip t= new Tooltip("Towers");
		        Tooltip.install(b, t);
	        }    
	    });
		
		b.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );");
		b.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent me) {
	        	//TODO:
	        }    
	    });
		
		return b;
	}

	private Tab buildTab(Node list, String title) {
		//System.out.println("Hishihs");
		Tab tab= new Tab();
		tab.setText(title);
		tab.setContent(list);
		return tab;
	}

	public Node getView() {
		return myTowerColumn;
	}
}
