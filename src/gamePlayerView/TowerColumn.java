package gamePlayerView;

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
 * @author Guhan Muruganandam
 * 
 */

public class TowerColumn implements IGUIPiece {
	
	//private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	private VBox myTowerColumn;
	private ListView<Button> towerInfo;
	private Button towerToBeDragged;
	
	public TowerColumn(){
		myTowerColumn= buildVBox();
	}
	
	/**
	 * Builds object that will actually be returned
	 */
	private VBox buildVBox() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(200);
		vbox.setPrefHeight(700);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.setStyle("-fx-background-color: #778899;");
	    
	    Label l = new Label("TOWERS");
	    l.setFont(new Font("Cambria",18));
	    
	    towerInfo= new ListView<Button>();
	    ObservableList<Button> items =FXCollections.observableArrayList();
	    Image imagecow = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/cow.png"));
            Image imagecookie = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/cookie.png"));
            Image imageboss = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/boss.png"));
            
            items.add(makeTowerButton("Basic", new ImageView(imagecow)));
            items.add(makeTowerButton("Sniper",new ImageView(imagecookie)));
            items.add(makeTowerButton("Bomber",new ImageView(imageboss)));
            towerInfo.setItems(items);
	    
	    towerInfo.setOnDragDetected(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent event) {
                    /* drag was detected, start a drag-and-drop gesture*/
                    /* allow any transfer mode */
                    Dragboard db = towerInfo.startDragAndDrop(TransferMode.MOVE);
                    /* Put a string on a dragboard */
                    ClipboardContent content = new ClipboardContent();
                    towerToBeDragged = towerInfo.getSelectionModel().getSelectedItem();
                    content.putString(towerToBeDragged.toString());
                    System.out.println(towerToBeDragged);
                    System.out.println("drag start");
                    db.setContent(content);
                    event.consume();
                }  
            });
	    
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
	    //resourceTabs.getTabs().add(buildTab(resourcebuttonInfo,"Buttondemo"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	    
	    vbox.getChildren().addAll(l,resourceTabs);
	    
	    return vbox;
	}
	

	private Button makeTowerButton(String string, ImageView imageView) {
		Button b =new Button(string,imageView);
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent me) {
	        	Tooltip t= new Tooltip("Towers");
		        Tooltip.install(b, t);
	        }    
	    });
		
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
