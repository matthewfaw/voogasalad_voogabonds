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
		if (myUpgrades.containsKey(s))
			return  myUpgrades.get(s);
		else
			//TODO: Throw Error, so we can have negative upgrade costs
			return -1;
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
		return 0;
	}
	@Override
	public void upgrade(String upgradeName, ITowerUpgradeStore towerUpgradeStore) {

		if (myUpgrades.containsKey(upgradeName)){
			getOwner().updateAvailableMoney(-myUpgrades.get(upgradeName));
			
			//All TODO:
			//Change my sell price and upgrade map
			//Call a superclass method that changes my Machine fields (weapon, health, etc.)
			
		} else {
			//TODO: Throw invalid upgrade error. 
		}
	}
	
	@Override
	public void sell() {
		getOwner().updateAvailableMoney(mySellPrice);
		die();
	}
	
}