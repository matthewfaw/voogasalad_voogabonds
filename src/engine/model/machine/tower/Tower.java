package engine.model.machine.tower;

import engine.model.machine.IPurchasable;
import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;
import engine.model.weapons.DamageInfo;
import engine.model.weapons.Weapon;
import utility.Damage;

import java.util.List;

import engine.model.game_environment.terrain.Terrain;

public class Tower extends Machine implements IPurchasable{
	private String myName;
	private Weapon myWeapon;
	//matthewfaw: is this necessary? doesn't machine already keep track of this?
	private int myMaxHealth;
	private List<Terrain> myPossibleTerrains;
	
	public Tower(String name, int maxhealth){
		super(maxhealth);
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
}
