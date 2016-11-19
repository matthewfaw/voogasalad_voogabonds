package engine.model.distributors;

import engine.model.game_environment.MapMediator;
import engine.model.machine.Machine;
import engine.model.machine.MachineFactory;
import utility.Point;

/**
 * This class manages distributing machines to the map
 * given data to construct a machine
 * @author matthewfaw
 *
 */
//TODO: add interface for all distributors
public class MachineDistributor {
	private MapMediator myMapMediator;
	private MachineFactory myMachineFactory;
	
	public MachineDistributor(MapMediator aMapMediator)
	{
		myMapMediator = aMapMediator;
		myMachineFactory = new MachineFactory();
	}

	/**
	 * Distributes the entity to the map backend, if it can be placed there
	 */
	public boolean distribute(MachineData aMachineData, Point aLocation)
	{
		//TODO: possibly check if machine can be placed before constructing the object?
		//1: construct object 
		//2: determine if the object can be placed on the map
		//3: distribute object to the map, if it can be placed there
		Machine machine = myMachineFactory.constructMachine(aMachineData);
		return myMapMediator.attemptToPlaceEntity(aLocation, machine);
	}

}
