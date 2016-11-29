package engine.model.machine.tower;
import engine.model.machine.Machine;
import java.util.Collection;
import java.util.Map;
public class Tower extends Machine implements ITower {
	private Map<String, Integer> myUpgrades;
	private int mySellPrice;
	
	public Tower(){
	}
	public Tower(String name, int maxHealth) {
		super(name, maxHealth);
	}
	@Override
	public int getUpgradeCost(String s) {
		return myUpgrades.containsKey(s) ? myUpgrades.get(s) : -1;
	}
	
	@Override
	public Collection<String> getUpgrades() {
		return myUpgrades.keySet();
	}
	@Override
	public int getSellPrice() {
		return mySellPrice;
	}
	@Override
	protected int die() {
		//TODO: Delete self
		unregister();
		return 0;
	}
	@Override
	public void upgrade(String upgradeName, ITowerUpgradeStore towerUpgradeStore) {
		//All TODO:
		//Subtract money from player's wallet
		//Change my sell price and upgrade map
		//Call a superclass method that changes my Machine fields (weapon, health, etc
		
	}
}