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
	ListView<String> towerInfo=new ListView<String>();
	private String towerToBeDragged;
	
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
	    
	    //Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("cow.png"));
	    ////Image image = new Image(
	    ////getClass().getResourceAsStream("warn.png")
	    ////);
	    //Tooltip myTooltip =new Tooltip();
	    //myTooltip.setGraphic(new ImageView(image));
	    	     
	    
	    ObservableList<String> items =FXCollections.observableArrayList("Monkey", "AOE", "Freeze", "Fly");
	    towerInfo.setItems(items);
	    
	    towerInfo.setOnDragDetected(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent event) {
	                /* drag was detected, start a drag-and-drop gesture*/
	                /* allow any transfer mode */
	                Dragboard db = towerInfo.startDragAndDrop(TransferMode.MOVE);
	                /* Put a string on a dragboard */
	                ClipboardContent content = new ClipboardContent();
	                towerToBeDragged = towerInfo.getSelectionModel().getSelectedItem();
	                content.putString(towerToBeDragged);
	                //System.out.println(towerToBeDragged);
	                db.setContent(content);
	                event.consume();
	            }  
	        });

	    towerInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                        //towerToBeDragged = towerInfo.getSelectionModel().getSelectedItem();
                        //System.out.println(towerToBeDragged);   
                    }
                });
	    
	    ListView<String> resourceInfo=new ListView<String>();
	    ObservableList<String> otheritems =FXCollections.observableArrayList("Bonus", "styf", "Fe", "Fly");
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
	    
	    vbox.getChildren().addAll(l,resourceTabs);
	    
	    return vbox;
	}
	
	public ListView<String> getList(){
	    return towerInfo;
	}
	

	private Tab buildTab(Node list, String title) {
		Tab tab= new Tab();
		tab.setText(title);
		tab.setContent(list);
		return tab;
	}

	public Node getView() {
		return myTowerColumn;
	}

    public Text getTowerToBeDragged() {
        Text source = new Text(towerToBeDragged);
        System.out.println(towerToBeDragged);
        System.out.println(source.getText());
        return source;
    }
}
