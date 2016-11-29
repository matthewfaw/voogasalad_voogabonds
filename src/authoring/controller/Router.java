package authoring.controller;
import authoring.view.side_panel.*;

public class Router {
	private EnemyDataController edc = new EnemyDataController();
	private MapDataController mdc = new MapDataController();
	private PlayerDataController pldc = new PlayerDataController();
	private ProjectileDataController prdc = new ProjectileDataController();
	private TowerDataController tdc = new TowerDataController();
	private WaveDataController wadc = new WaveDataController();
	private WeaponDataController wedc = new WeaponDataController();
	
	public void link(TowerTab tTab, EnemyTab eTab, WaveLevelTab wTab, GameTab gTab){
		/**
		 * CONNECTIONS TO BE CREATED:
		 * MAP WILL NEED TO LISTEN TO: TERRAIN (SetChangeListener)
		 */
		
		//Listeners for WaveLevelTab
		edc.attach(wTab);
		mdc.attach(wTab);
//		mdc.addSpawnPointListener(wTab.createSpawnPointListener());
		
		//Listeners for EnemyTab
//		wedc.addControllerListener(eTab.createWeaponListener());
//		mdc.addTerrainListener(eTab.createTerrainListener());

        //TowerTab
//        wedc.addControllerListener(tTab.createWeaponListener());
//        mdc.addTerrainListener(tTab.createTerrainListener());
		
		//GameTab

	}
	
	public EnemyDataController getEnemyDataController(){
		return edc;
	}
	
	public MapDataController getMapDataController(){
		return mdc;
	}
	
	public PlayerDataController getPlayerDataController(){
		return pldc;
	}
	
	public ProjectileDataController getProjectileDataController(){
		return prdc;
	}
	
	public TowerDataController getTowerDataController(){
		return tdc;
	}
	
	public WaveDataController getWaveDataController(){
		return wadc;
	}
	
	public WeaponDataController getWeaponDataController(){
		return wedc;
	}
}
