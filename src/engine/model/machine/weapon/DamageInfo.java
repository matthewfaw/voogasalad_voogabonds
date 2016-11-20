package engine.model.machine.weapon;

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
