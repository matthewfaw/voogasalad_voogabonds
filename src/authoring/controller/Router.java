package authoring.controller;
import authoring.view.side_panel.*;
import authoring.view.tabs.WaveTab;
import authoring.view.tabs.EntityTab;
import authoring.view.tabs.LevelTab;

public class Router {
	private MapDataContainer mdc = new MapDataContainer();
	private EntityDataContainer edc = new EntityDataContainer();
	private PlayerDataContainer pldc = new PlayerDataContainer();
	private LevelDataContainer ldc = new LevelDataContainer();
	private WaveDataContainer wadc = new WaveDataContainer();
	
	public void link(EntityTab e, LevelTab l, WaveTab w){
		//Listeners for EntityTab
		mdc.attach(e); //Entity listener
		
		//Listeners for WaveTab
		mdc.attach(w); //Terrain listener
		edc.attach(w); //Entity listener

		//Listeners for levelTab
		wadc.attach(l);
		
	}
	
	
	public MapDataContainer getMapDataContainer(){
		return mdc;
	}
	
	public PlayerDataContainer getPlayerDataContainer(){
		return pldc;
	}
	
	public WaveDataContainer getWaveDataContainer(){
		return wadc;
	}
	
	public LevelDataContainer getLevelDataContainer(){
		return ldc;
	}
	
	public EntityDataContainer getEntityDataContainer(){
		return edc;
	}
}
