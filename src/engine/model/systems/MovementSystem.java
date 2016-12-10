package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.MoveableComponent;
import engine.model.components.PhysicalComponent;
import engine.model.strategies.IPhysical;

public class MovementSystem implements IObserver<TimelineController>, ISystem {
	private List<MoveableComponent> myMoveableComponents;
	private TargetingSystem myTargeting;
	private PhysicalSystem myPhysical;
	private CollisionDetectionSystem myCollision;
	
	
	public MovementSystem (PhysicalSystem physical, CollisionDetectionSystem collision, TargetingSystem targeting) {
		myMoveableComponents = new ArrayList<MoveableComponent>();
		myPhysical = physical;
		myTargeting = targeting;
		myCollision = collision;
	}
	
	private void updateNextMoves() {
		for (MoveableComponent mc: myMoveableComponents) {
			mc.setGoal(myTargeting.getTarget(mc));
			PhysicalComponent p = myPhysical.get(mc);
			p.setPosition(mc.getMove(p));
			myCollision.checkCollision(p);
		}
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(MoveableComponent aComponent) {
		myMoveableComponents.add(aComponent);
	}
	public void detachComponent(MoveableComponent aComponent) {
		myMoveableComponents.remove(aComponent);
	}
	
	/********* Observer interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		updateNextMoves();
	}


}
