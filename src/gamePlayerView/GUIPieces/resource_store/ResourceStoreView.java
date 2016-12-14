package gamePlayerView.GUIPieces.resource_store;

import java.util.HashMap;
import java.util.Map;

import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.GUIPieces.TowerColumn;
import gamePlayerView.interfaces.IGUIPiece;
import gamePlayerView.interfaces.IResourceAcceptor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ResourceStoreView extends VBox implements IGUIPiece, IResourceAcceptor, IObserver<IViewablePlayer>{
	
	Draggables myDraggables;
	ResourceInfo myResourceInfo;
	TowerColumn myTowerColumn;
	private Map<ImageView,EntityData> myImageToDataMap;
	
	public ResourceStoreView () {
		myImageToDataMap = new HashMap<ImageView,EntityData>();
		myDraggables = new Draggables(myImageToDataMap);
		myResourceInfo = new ResourceInfo(myImageToDataMap);
		myDraggables.attach(myResourceInfo);
		constructNode();
	}

	private void constructNode() {
		getChildren().add(myDraggables.getNode());
		getChildren().add(myResourceInfo.getNode());
	}

	@Override
	public Node getNode() {
		return this;
	}

	/**
	 * Updates player info given the changed viewable player.
	 */
	@Override
	public void update(IViewablePlayer aChangedObject) {
		myDraggables.update(aChangedObject);
	}

	@Override
	public void remove(IViewablePlayer aRemovedObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptResources(IObservable<IViewablePlayer> aPlayer) {
		aPlayer.attach(this);
	}

}
