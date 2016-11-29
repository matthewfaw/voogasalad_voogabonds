package gamePlayerView.gamePlayerView;

import java.util.List;

import authoring.model.map.MapData;
import engine.IObservable;
import engine.model.playerinfo.IViewablePlayer;
import engine.model.resourcestore.IViewableStore;
import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.ILivesAcceptor;
import gamePlayerView.interfaces.IWavesAcceptor;

public class Router {
	private GamePlayerScene  myGamePlayerScene;
	//TODO: Change these box objects to instead be acceptor interfaces
	private List<ICashAcceptor> myCash;
	private List<ILivesAcceptor> myLives; 
	private List<IWavesAcceptor> myWaves;
	//List<IEnemySources> myFrontendEnemySources;
	
	
	public Router(GamePlayerScene aGamePlayerScene)
	{
		myGamePlayerScene  = aGamePlayerScene;
		//myCash=myGamePlayerScene.getCash();
		//myLives=myGamePlayerScene.getLives();
		//myWaves=myGamePlayerScene.getWaves();
		//myFrontendEnemySources = myGamePlayerScene.getEnemySources();
	}
	
	public void distributeResourceStore(IViewableStore aResourceStore)
	{
		
	}
	
	public void distributePlayer(IViewablePlayer aPlayer)
	{
		//This is where you'll get player specific info such as money and lives
	}
	
	//TODO:
//	public void distributeGameState()
	
	public void distributeMapData(MapData aMapData)
	{
		//TODO: distribute to all interested frontend objects
	}
	
	
	/**
	 * An example to follow for setting up the router
	 * @param aResourceStore
	 */
	/*
	public void distributesResourceStore(IViewableResourceStore aResourceStore)
	{
		myGamePlayerScene.getResourceStoreAcceptors().forEach(acceptor -> acceptor.acceptResourceStore(aResourceStore));
	}
	*/
	
	
	/*
	 * void distributeEnemy(IViewableEnemy aEnemy)
	 * {
	 * 		for (IEnemySource enemySource: myFrontendEnemySources) {
	 * 			enemySource.giveEnemy(aEnemy)
	 * 		]
	 * }
	 * 
	 * 
	 * 
	 * over  in some IEnemySource object:
	 * 
	 * @Override
	 * void giveEnemy(IViewableEnemy aEnemy)
	 * {
	 * 		aEnemy.attach(this);
	 * }
	 * 
	 * @Override
	 * void update()
	 * {
	 * 		//redraw the enemy
	 * }
	 */
	
}
