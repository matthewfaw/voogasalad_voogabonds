package usecases;

/**
 * This class shows the use case of defining weapon damage. 
 * The user would input a value for the damage, 
 * then the frontend would create a MockWeapon object, 
 * which would be sent to the backend through the controller.
 * 
 * @author Niklas Sjoquist
 * 
 */
public class DefineWeaponDamage {

    public DefineWeaponDamage(double damage) {
        MockWeapon weapon = new MockWeapon("Gun");
        weapon.setDamage(damage);
    }
    
}
