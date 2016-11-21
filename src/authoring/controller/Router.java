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
