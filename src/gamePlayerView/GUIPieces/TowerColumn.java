
package gamePlayerView.GUIPieces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoring.model.TowerData;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.IResourceAcceptor;
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
public class TowerColumn implements IResourceAcceptor,IObserver<IViewablePlayer>, IGUIPiece {
	
	//private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	private VBox myTowerColumn;
	private ListView<ImageView> towerInfo;
	private ImageView towerToBeDragged;
	private TextArea towerDataDisplay= new TextArea();
	private Map<ImageView,TowerData> towerSettings= new HashMap<ImageView,TowerData>();

	
	public TowerColumn(IViewablePlayer aPlayer){
		myTowerColumn= buildVBox(aPlayer);
	}
	
	/**
	 * Builds object that will actually be returned
	 * @param aPlayer 
	 */
	private VBox buildVBox(IViewablePlayer aPlayer) {
		
		List<TowerData> availableTowers = aPlayer.getAvailableTowers();
		List<TowerData> affordableTowers = aPlayer.getAffordableTowers();
		
		VBox vbox = new VBox();
		vbox.setPrefWidth(200);
		vbox.setPrefHeight(600);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    //TODO:Export CSS to other part
	    vbox.setStyle("-fx-background-color: #778899;");
	    
	    Label l = new Label("TOWERS");
	    l.setFont(new Font("Cambria",18));
	    
	    towerInfo= populatetowerInfo(availableTowers);
	    setDragFunctionality();
        setPopulateFunctionality();
	    
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(towerInfo, "Towers"));
	    //resourceTabs.getTabs().add(buildTab(resourceInfo, "Resources"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	    
	    vbox.getChildren().addAll(l,resourceTabs,towerDataDisplay);
	    
	    return vbox;
	}

	private ListView<ImageView> populatetowerInfo(List<TowerData> towers) {
		ObservableList<ImageView> items =FXCollections.observableArrayList();
		ListView<ImageView> towerSet= new ListView<ImageView>();
		towerSettings.clear();
		for(TowerData t : towers){
			ImageView towerPicture = new ImageView();
			Image towerImage = new Image(this.getClass().getClassLoader().getResourceAsStream(/*t.getImagePath())*/ "resources/cow.png")); //THIS IS IFFY. COME BACK TO THIS
			towerPicture.setImage(towerImage);
			//towerSettings.put(towerPicture,t);
			items.add(towerPicture);
		}
        towerSet.setItems(items);
		return towerSet;
	}

	private void setPopulateFunctionality() {
		towerInfo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                ImageView towerChosen = towerInfo.getSelectionModel().getSelectedItem();
                //TowerData tower=towerSettings.get(towerChosen);
                //PopulateTowerDataDisplay(tower);
            }  
        });
	}
	
	private void PopulateTowerDataDisplay(TowerData tower) {
		String info= new String(tower.getName());
		towerDataDisplay.setText(info);
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

	private Tab buildTab(Node list, String title) {
		Tab tab= new Tab();
		tab.setText(title);
		tab.setContent(list);
		return tab;
	}

	public Node getView() {
		return myTowerColumn;
	}
	
	public void acceptResources(IViewablePlayer aPlayer) {
		aPlayer.attach(this);
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		myTowerColumn=buildVBox(aChangedObject);
	}
	
	/*
	 * 
	 * @Override // for the IResourceStoreAcceptor interface 
	 * void acceptResourceStore(IViewableResourceStore aResourceStore)
	 * {
	 * 		aResourceStore.attach(this);
	 * }
	 * 
	 * @Override // for the IObserver interface
	 * void update(...)
	 * {
	 * 		refresh the list view
	 * }
	 * 
	 * 
	 * 
	 */
	
	//MIGHT NEED LATER
	/*public IResourceAcceptor getResources() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	/*private Button makeTowerImage(String string, ImageView imageView) {
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
}*/
}