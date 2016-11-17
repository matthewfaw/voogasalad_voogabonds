package engine.model.machine;

import engine.model.playerinfo.IModifiablePlayerInfo;
import utility.Point;

public abstract class Machine implements IViewableMachine {

	private IModifiablePlayerInfo myModifiablePlayerInfo;
	private IHealth health;
	
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHeading() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IHealth getHealth() {
		return health;
	}
	
	public IModifiablePlayerInfo getModifiablePlayerInfo() {
		return myModifiablePlayerInfo;
	}

}
