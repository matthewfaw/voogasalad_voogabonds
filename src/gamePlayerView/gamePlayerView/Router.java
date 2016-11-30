package gamePlayerView.gamePlayerView;

import java.util.List;

import authoring.model.TowerData;
import authoring.model.map.MapData;
import engine.IObservable;
import engine.model.playerinfo.IViewablePlayer;
import engine.model.resourcestore.IViewableStore;
import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.ILivesAcceptor;
import gamePlayerView.interfaces.IResourceAcceptor;
import gamePlayerView.interfaces.IWavesAcceptor;

public class Router {
	private GamePlayerScene  myGamePlayerScene;
	//TODO: Change these box objects to instead be acceptor interfaces
	private List<ICashAcceptor> myCash;
	private List<ILivesAcceptor> myLives; 
	private List<IWavesAcceptor> myWaves;
	private List<IResourceAcceptor> myResources;
	//List<ISprites> mySprites;
	
	
	public Router(GamePlayerScene aGamePlayerScene)
	{
		myGamePlayerScene  = aGamePlayerScene;
		myCash=myGamePlayerScene.getCash();
		myLives=myGamePlayerScene.getLives();
		myWaves=myGamePlayerScene.getWaves();
		myResources=myGamePlayerScene.getResources();
		//mySprites = myGamePlayerScene.getSprites();
	}
	
	public void distributePlayer(IViewablePlayer aPlayer)
	{
		//This is where you'll get player specific info such as money and lives and Tower Data
		distributeCash(aPlayer);
		distributeLives(aPlayer);
		distributeResourceStore(aPlayer);
		
	}
	
	//TODO:
//	public void distributeGameState() //Will have wave stuff
	
	private void distributeResourceStore(IViewablePlayer aPlayer) {
		for(IResourceAcceptor r : myResources){
			r.acceptResources(aPlayer);
		}
	}

	private void distributeLives(IViewablePlayer aPlayer) {
		for(ILivesAcceptor l : myLives){
			l.acceptLives(aPlayer);
		}
	}

	private void distributeCash(IViewablePlayer aPlayer) {
		for(ICashAcceptor c : myCash){
			c.acceptCash(aPlayer);
		}
	}
	
	//public void distributeSprite(//Something){
	//	for(ISprite s : mySprites){
			//s.acceptSprite(//Something);
		//}		
	//}

	public void distributeMapData(MapData aMapData)
	{
		//TODO: distribute to all interested frontend objects
	}
	
	
	/**
	 * An example to follow for setting up the router
	 * @param aResourceStore
	 */
	/*
	public void distributeResourceStore(IViewableResourceStore aResourceStore)
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
