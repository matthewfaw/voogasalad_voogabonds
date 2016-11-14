package usecases;

public class DefineEnemySpeed {
	private MockEnemy myEnemy;
	
	public void defineEnemySpeed(String name, double speed){
		myEnemy = new MockEnemy(name);
		myEnemy.setSpeed(speed);
	}

}
