package gamePlayerView;

import java.util.List;

import engine.IObservable;
import gamePlayerView.GUIPieces.CashBox;
import gamePlayerView.GUIPieces.LivesBox;
import gamePlayerView.GUIPieces.WavesBox;
import javafx.scene.layout.HBox;

public class Router {
	private GamePlayerScene  myGamePlayerScene;
	//TODO: Change these box objects to instead be acceptor interfaces
	//private List<LivesBox> myLives; 
	//private List<WavesBox> myWaves;
	//List<IEnemySources> myFronetendEnemySources;
	
	
	public Router(GamePlayerScene aGamePlayerScene)
	{
		myGamePlayerScene  = aGamePlayerScene;
		//myCash=myGamePlayerScene.getCash();
		//myLives=myGamePlayerScene.getLives();
		//myWaves=myGamePlayerScene.getWaves();
		//myFrontendEnemySources = myGamePlayerScene.getEnemySources();
	}
	
	public void distributeCash(IObservable aCash){
		//myCash.giveObject(aCash);
	}
	public void distributeLives(IObservable aLives){
		//myLives.giveObject(aLives); //TODO set up link all the way to Statistics Row
	}
	public void distributeWaves(IObservable aWaves){
		//myWaves.giveObject(aWaves); //TODO set up link all the way to statistics row
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
