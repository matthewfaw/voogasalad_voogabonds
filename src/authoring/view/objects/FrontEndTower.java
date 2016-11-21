package authoring.view.objects;

import authoring.authoring_interfaces.IMachine;
import authoring.authoring_interfaces.ITower;
import authoring.authoring_interfaces.IWeapon;

public class FrontEndTower implements IMachine, ITower {

    private String myName;
    private String myType;
    private double myHealth;
    private double myCost;
    private double mySize;
    private IWeapon myWeapon;
    private String myImage;
    private String mySound;
    
    public FrontEndTower(String name, String type, double health, double cost, double size, String image) {
        myName = name;
        myType = type;
        myHealth = health;
        myCost = cost;
        mySize = size;
        myImage = image;
    }
    
    public void setSound(String sound) {
        mySound = sound;
    }
    
    @Override
    public double getCost () {
        return myCost;
    }

    @Override
    public double getSellPrice () {
        // TODO: Calculate sell price
        return myCost/2.0;
    }

    @Override
    public double getRank () {
        // TODO: Calculate rank
        return 0;
    }

    @Override
    public double getSize () {
        return mySize;
    }

    @Override
    public double getUpgradeCost () {
        // TODO: Calculate upgrade cost
        return 0;
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    public double getHealth () {
        return myHealth;
    }

    @Override
    public IWeapon getWeapon () {
        return myWeapon;
    }

    @Override
    public String getType () {
        return myType;
    }

    @Override
    public String getImage () {
        return myImage;
    }

    @Override
    public String getSound () {
        return mySound;
    }

}
