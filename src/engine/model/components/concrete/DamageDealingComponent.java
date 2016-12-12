package engine.model.components.concrete;

import java.util.ArrayList;

import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.IComponent;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewableDamageDealer;
import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.IPhysical;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.weapons.DamageInfo;
import utility.Damage;

/**
 * The purpose of this class is to manage the information relevant to 
 * dealing damage to another entity
 * 
 * @author matthewfaw
 *
 */
public class DamageDealingComponent extends AbstractComponent implements IViewableDamageDealer {
	private int myDamage;
	private double myDamageArc;
	private double myDamageRadius;
	private IDamageStrategy myDamageStrategy;
	@Hide
	private List<IObserver<IViewable>> myObservers;

	@Hide
	private HealthSystem myHealthSystem;
	
	@Hide
	private PhysicalSystem myPhysicalSystem;
	
	public DamageDealingComponent(
			DamageDealingSystem damageDealingSystem,
			HealthSystem healthSysytem,
			PhysicalSystem physicalSystem,
			ComponentData data
			) {
		
		myObservers = new ArrayList<IObserver<IViewable>>();
		myHealthSystem = healthSysytem;
		myPhysicalSystem = physicalSystem;
		
		myDamage = Integer.parseInt(data.getFields().get("myDamage"));
		myDamageArc = Double.parseDouble(data.getFields().get("myDamageArc"));
		myDamageRadius = Double.parseDouble(data.getFields().get("myDamageRadius"));
		myDamageStrategy = damageDealingSystem.newStrategy(data.getFields().get("myDamageStrategy"));
		
		damageDealingSystem.attachComponent(this);
	}
	
	/**
	 * gets the amount of damage this entity inflicts on its target
	 * @return the damage value
	 */
	public Damage getTargetDamage()
	{
		return myDamageStrategy.getTargetDamage(myDamage);
	}
	
	public Damage getDamageTo(IPhysical dealer, IPhysical taker)
	{
		return myDamageStrategy.getAoEDamage(dealer, taker, myDamage);
	}
	
	/**
	 * gets the radius of effect of this entity
	 * @return the radius
	 */
	@Override
	public double getDamageRadius()
	{
		return myDamageRadius;
	}

	public DamageInfo explode(IComponent target) {
		DamageInfo result = new DamageInfo();
		
		//Deal damage to target
		result.add(myHealthSystem.dealDamageTo(target, getTargetDamage()));
		
		//Deal damage to anyone in blast radius
		PhysicalComponent myPhysical = myPhysicalSystem.get(this);
		if (myPhysicalSystem.get(this) != null) {
			List<PhysicalComponent> inBlast = myPhysicalSystem.withinRange(myPhysical.getPosition(), myDamageRadius, myPhysical.getHeading(), myDamageArc);
			for (PhysicalComponent p: inBlast){
				result.add(myHealthSystem.dealDamageTo(p, getDamageTo(myPhysical, p)));
			}
		}
		return result;
	}

	@Override
	public void distributeInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDamage() {
		return myDamage;
	}

	@Override
	public double getDamageArc() {
		return myDamageArc;
	}

	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

}
