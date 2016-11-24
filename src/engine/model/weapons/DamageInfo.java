package engine.model.weapons;

/**
 * A class to more easily pass around the information that results from a projectile exploding.
 * The class could be replaced by a struct with litle change in functionality.
 * @author Weston
 *
 */
public class DamageInfo {
	private int myDamage;
	private int myMoney;
	private int myKills;
	
	public DamageInfo(int damageDealt, int moneyGained, int unitsKilled){
		myDamage = damageDealt;
		myMoney = moneyGained;
		myKills = unitsKilled;
	}
	
	public DamageInfo() {
		this(0, 0, 0);
	}

	public int getDamage(){
		return myDamage;
	}
	public int getMoney(){
		return myMoney;
	}
	public int getKills(){
		return myKills;
	}
	
	public DamageInfo add(DamageInfo d) {
		return new DamageInfo(myDamage + d.myDamage, myMoney + d.myMoney, myKills + d.myKills);
	}
}
