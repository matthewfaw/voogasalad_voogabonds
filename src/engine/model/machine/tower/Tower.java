package engine.model.machine.tower;

import engine.model.machine.IPurchasable;
import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;
import engine.model.machine.weapon.DamageInfo;
import engine.model.machine.weapon.Weapon;
import utility.Damage;

import java.util.List;

import engine.model.game_environment.terrain.Terrain;

public class Tower extends Machine implements IPurchasable{
	private String myName;
	private Weapon myWeapon;
	private int myMaxHealth;
	private List<Terrain> myPossibleTerrains;
	
	public Tower(String name, int maxhealth){
		myName = name;
		myMaxHealth = maxhealth;
	}
	public void setPossibleTerrains(List<Terrain> possibleterrains){
		myPossibleTerrains = possibleterrains;
	}
	@Override
	public DamageInfo takeDamage(Damage toDeal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// this information should be stored in the upgradestore, controller just need to query it
		/*
		public IMoney getUpgradeCost() {
			return upgradeCost;
		}
		
		
		public IMoney getSellPrice() {
			return sellPrice;
		}
		*/
		
		/**
		 * Upgrades this tower to the
		 * @param newTower
		 * Replaces all fields of this tower with newTower
		 */
		/*
		 //OWEN: when the user upgrade, the upgradestore will take care of it. 
		 // tower just needs to offer an interface for the UpgradeStore to update the parameters 
		public void upgrade(ITowerUpgradeStore towerUpgradeStore) {
			
		}
		*/
}
