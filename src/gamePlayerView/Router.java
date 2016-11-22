package gamePlayerView;

import engine.IObservable;
import javafx.scene.layout.HBox;

public class Router {
	private GamePlayerScene  myGamePlayerScene;
	private CashBox myCash =new CashBox();
	//List<IEnemySources> myFronetendEnemySources;
	
	
	public Router(GamePlayerScene aGamePlayerScene)
	{
		myGamePlayerScene  = aGamePlayerScene;
		myCash=myGamePlayerScene.getCash();
		//myFrontendEnemySources = myGamePlayerScene.getEnemySources();
	}
	
	void distributeCash(IObservable aCash){
		myCash.giveObject(aCash);
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
