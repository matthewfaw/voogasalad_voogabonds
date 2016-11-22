package gamePlayerView;

import engine.IObservable;
import javafx.scene.layout.HBox;

public class Router {
	private GamePlayerScene  myGamePlayerScene;
	private CashBox myCash;
	private LivesBox myLives; 
	private WavesBox myWaves;
	//List<IEnemySources> myFronetendEnemySources;
	
	
	public Router(GamePlayerScene aGamePlayerScene)
	{
		myGamePlayerScene  = aGamePlayerScene;
		myCash=myGamePlayerScene.getCash();
		myLives=myGamePlayerScene.getLives();
		myWaves=myGamePlayerScene.getWaves();
		//myFrontendEnemySources = myGamePlayerScene.getEnemySources();
	}
	
	void distributeCash(IObservable aCash){
		myCash.giveObject(aCash);
	}
	void distributeLives(IObservable aLives){
		myLives.giveObject(aLives); //TODO set up link all the way to Statistics Row
	}
	void distributeWaves(IObservable aWaves){
		myCash.giveObject(aWaves); //TODO set up link all the way to statistics row
	}
	
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
