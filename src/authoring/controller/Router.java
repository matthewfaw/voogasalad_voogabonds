package authoring.controller;
import authoring.view.side_panel.*;
import authoring.view.tabs.WaveTab;

public class Router {
	private EnemyDataContainer edc = new EnemyDataContainer();
	private MapDataContainer mdc = new MapDataContainer();
	private PlayerDataContainer pldc = new PlayerDataContainer();
	private ProjectileDataContainer prdc = new ProjectileDataContainer();
	private TowerDataContainer tdc = new TowerDataContainer();
	private WaveDataContainer wadc = new WaveDataContainer();
	private WeaponDataContainer wedc = new WeaponDataContainer();
	private LevelDataContainer ldc = new LevelDataContainer();
	
	public void link(TowerTab tTab, EnemyTab eTab, WaveTab wTab, GameTab gTab){
		/**
		 * CONNECTIONS TO BE CREATED:
		 * MAP WILL NEED TO LISTEN TO: TERRAIN (SetChangeListener)
		 */
		
		//Listeners for WaveLevelTab
		edc.attach(wTab); //Enemy data listener
		mdc.attach(wTab); //Spawn point listener
		
		//Listeners for EnemyTab
		mdc.attach(eTab); //Terrain listener
		wedc.attach(eTab); //Weapon listener

                //TowerTab
		wedc.attach(tTab); //Weapon listener
		mdc.attach(tTab); //Terrain listener
		
		//GameTab
		
	}
	
	public EnemyDataContainer getEnemyDataController(){
		return edc;
	}
	
	public MapDataContainer getMapDataController(){
		return mdc;
	}
	
	public PlayerDataContainer getPlayerDataController(){
		return pldc;
	}
	
	public ProjectileDataContainer getProjectileDataController(){
		return prdc;
	}
	
	public TowerDataContainer getTowerDataController(){
		return tdc;
	}
	
	public WaveDataContainer getWaveDataController(){
		return wadc;
	}
	
	public WeaponDataContainer getWeaponDataController(){
		return wedc;
	}
	
	public LevelDataContainer getLevelDataController(){
		return ldc;
	}
}
