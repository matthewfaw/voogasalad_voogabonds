package engine.model.components.concrete;

import java.util.List;

import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.components.IComponent;
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
public class DamageDealingComponent extends AbstractComponent {
	private IDamageStrategy myDamageStrategy;
	private int myDamage;
	private double myDamageRadius;
	private double myDamageArc;
	

	@Hide
	private HealthSystem myHealthSystem;
	@Hide
	private PhysicalSystem myPhysicalSystem;
	
	public DamageDealingComponent(
			DamageDealingSystem damageDealingSystem,
			HealthSystem healthSysytem,
			PhysicalSystem physicalSystem
			) {
		myHealthSystem = healthSysytem;
		myPhysicalSystem = physicalSystem;
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
			for (PhysicalComponent p: inBlast)
				result.add(myHealthSystem.dealDamageTo(p, getDamageTo(myPhysical, p)));
		}
		return result;
	}

}
