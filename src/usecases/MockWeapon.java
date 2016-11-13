package usecases;

import authoring_interfaces.*;

public class MockWeapon implements IWeapon {
    private String myName;
    private double myDamage;
    
    public MockWeapon(String name, double damage) {
        myName = name;
        myDamage = damage;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getEffectAmount () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getRange () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getStrategy () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getFireRate () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getAccuracy () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getRank () {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
