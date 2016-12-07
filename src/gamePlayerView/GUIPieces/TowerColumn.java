
package gamePlayerView.GUIPieces;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import authoring.model.TowerData;
import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.IGUIPiece;
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
import javafx.scene.input.DataFormat;
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
	private ImageView towerToBeDragged;
	private Map<ImageView,TowerData> towerSettings= new HashMap<ImageView,TowerData>();
	private TextArea towerDataDisplay;
	private ListView<ImageView> towerInfo;
	//private VBox myColumn;

	
	public TowerColumn(){
		myTowerColumn= buildVBox();
	}
	
	public TowerData getTowerData(String aTowerName)
	{
		for (TowerData data: towerSettings.values()) {
			if (data.getName().equals(aTowerName)) {
				return data;
			}
		}
		return null;
	}
	
	/**
	 * Builds object that will actually be returned
	 */
	private VBox buildVBox() {
	    VBox vbox=new VBox();
	    towerDataDisplay= new TextArea();
	    towerInfo=new ListView<ImageView>(); 
	    //populatetowerInfo(availableTowers,towerDataDisplay);
	    
	    Label heading = new Label("TOWERS");
	    heading.setFont(new Font("Cambria",18));
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(towerInfo, "Towers"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	    
	    vbox.getChildren().addAll(heading,resourceTabs,towerDataDisplay);
	    
	    return vbox;
	}
	/*
	 * Creates ListView for the selected towerData
	 */
	private void populateTowerInfo(IViewablePlayer aPlayer) {
		List<TowerData> availableTowers = aPlayer.getAvailableTowers();
		List<TowerData> affordableTowers = aPlayer.getAffordableTowers();
		
		ObservableList<ImageView> items =FXCollections.observableArrayList();
		towerSettings.clear();
		for(TowerData t : affordableTowers){
			ImageView towerPicture = new ImageView();
			String imagePath = t.getImagePath();
			//TODO: make this cleaner--hard coded now
			if (imagePath.substring(0, 4).equals("src/")) {
				imagePath = imagePath.substring(4);
			}
			Image towerImage = new Image(this.getClass().getClassLoader().getResourceAsStream(imagePath)); //THIS IS IFFY. COME BACK TO THIS
			towerPicture.setImage(towerImage);
			towerSettings.put(towerPicture,t);
			items.add(towerPicture);
		}
        towerInfo.setItems(items);
        setDragFunctionality(towerInfo);
        setPopulateFunctionality(towerInfo,towerDataDisplay);
        myTowerColumn.getChildren().clear();
        
        Label heading = new Label("TOWERS");
	    heading.setFont(new Font("Cambria",18));
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(towerInfo, "Towers"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
	    myTowerColumn.getChildren().addAll(heading,resourceTabs,towerDataDisplay);
	}
	/*
	 * Sets Mouse Click event for List View
	 */
	private void setPopulateFunctionality(ListView<ImageView> towerSet, TextArea towerDataDisplay) {
		towerSet.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                ImageView towerChosen = towerSet.getSelectionModel().getSelectedItem();
                TowerData tower=towerSettings.get(towerChosen);
                PopulateTowerDataDisplay(tower,towerDataDisplay);
            }  
        });
	}
	/*
	 * Allows Text Area to display attributes based on the Tower selected in the ListView
	 */
	private void PopulateTowerDataDisplay(TowerData tower,TextArea towerDataDisplay) {
		towerDataDisplay.clear();
		towerDataDisplay.setEditable(false);
		String namelabel=new String("TOWER NAME: ");
		String name= new String(tower.getName());
		String newline=new String("\n");
		String costlabel=new String("COST: ");
		String cost=new String(Integer.toString(tower.getBuyPrice()));
		String weaponlabel=new String("WEAPON NAME: ");
		String weapon= new String(tower.getWeaponName());
		
		towerDataDisplay.setText(namelabel+name+newline+costlabel+cost+newline+weaponlabel+weapon);
	}
	
	private void setDragFunctionality(ListView<ImageView> towerSet) {
		towerSet.setOnDragDetected(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Dragboard db = towerSet.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                towerToBeDragged = towerSet.getSelectionModel().getSelectedItem();
                content.putImage(towerToBeDragged.getImage());
                content.putString(towerSettings.get(towerToBeDragged).getName());
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
	
	public void acceptResources(IObservable<IViewablePlayer> aPlayer) {
		aPlayer.attach(this);
//		update(aPlayer);
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		populateTowerInfo(aChangedObject);
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