package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import engine.model.components.AbstractComponent;
import engine.model.components.IViewableHealth;
import engine.model.entities.IEntity;
import engine.model.systems.BountySystem;
import engine.model.systems.HealthSystem;
import engine.model.weapons.DamageInfo;
import gamePlayerView.gamePlayerView.Router;
import utility.Damage;
import utility.Point;

/**
 * The purpose of this class is to manage the health information
 * relevant to an entity
 * manages how an entity should take damage
 * @author matthewfaw
 * @author Weston
<<<<<<< HEAD
 * @author owenchung (edit)
 *
||||||| merged common ancestors
 * @author owenchung (edits)
 *
=======
 * @author owenchung (edits)
 * @author alanguo (edits)
>>>>>>> health
 */
public class HealthComponent extends AbstractComponent implements IViewableHealth{
	@Hide
	private static double DEFAULT_HEALTH = 0.0;
	@Hide
	private BountySystem myBounty;
	//private HealthSystem myHealthSystem;
	private Double myCurrHealth;
	private Double myMaxHealth;
	
	@Hide
	private List<IObserver<IViewableHealth>> myObservers;
	
	@Hide
	private Router myRouter;
	
	public HealthComponent(/*HealthSystem healthSystem, */BountySystem bounty,  ComponentData componentdata, Router router) {
		myRouter = router;
		myBounty = bounty;
		myCurrHealth = Double.parseDouble(componentdata.getFields().get("myCurrHealth"));
		myMaxHealth = Double.parseDouble(componentdata.getFields().get("myMaxHealth"));
		
		myObservers = new ArrayList<IObserver<IViewableHealth>>();
		
//		healthSystem.attachComponent(this);
		
		myCurrHealth = DEFAULT_HEALTH;
		myMaxHealth = DEFAULT_HEALTH;
		
		// TODO: distribute through the router
//		myRouter.distributeViewableHealthComponent(this);
	}
		
	public int getCurrentHealth() {
		return myCurrHealth.intValue();
	}
	
	public void setCurrentHealth(double newHealthValue) {
		myCurrHealth = newHealthValue;
		//notifyObservers();
	}
	
	public DamageInfo takeDamage(Damage dmg) {
		double newCurrHealth = myCurrHealth - dmg.getDamage();
		
		if (newCurrHealth < 0) {
			setCurrentHealth(0);
		}
		if (newCurrHealth > myMaxHealth) {
			setCurrentHealth(myMaxHealth);
		}
		
		int died = myCurrHealth <= 0 ? 1 : 0;
		int bounty = myBounty.collectBounty(this);
		
		return new DamageInfo(dmg.getDamage(), died, bounty);		
	}
	
	public void setMaxHealth(double m) {
		myMaxHealth = m;
		
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
	}
	
	/********* Observable Interface **********/

	@Override
	public void attach(IObserver<IViewableHealth> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewableHealth> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

	@Override
	public double getMaxHealth() {
		return myMaxHealth;
	}

	@Override
	public double getCurrHealth() {
		return myCurrHealth;
	}

	
}
