package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWForce;
import starwars.entities.actors.BenKenobi;
import starwars.entities.actors.Player;

public class Train extends SWAffordance implements SWActionInterface{

	
	public Train(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}
	
	
	/**
	 * @return: return duration 1
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	/**
	 * A String describing what this <code>Train</code> action will do, suitable for display on a user interface
	 * 
	 * @return String comprising "train " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		if (target instanceof SWActor) {
			if (target instanceof BenKenobi) {
				return this.target.getShortDescription() + " train player";
			}
		}
		return this.target.getShortDescription() + " cannot train player";
	}
	
	
	/**
	 * this method returns a boolean result to check whether are both actors standing at the same location
	 * @param a is Luke from SWActor class
	 * @return true if they are standing at the same location otherwise false
	 * @author Justin
	 * @author Linh
	 */
	@Override
	public boolean canDo(SWActor a) {
		SWEntityInterface target = this.getTarget();
		boolean targetIsActor = target instanceof SWActor;
		SWActor targetActor = null;
		
		if (targetIsActor) {
			targetActor = (SWActor) target;
		}
		
		if (targetActor instanceof BenKenobi) {
			if (SWAction.getEntitymanager().whereIs(a).equals(SWAction.getEntitymanager().whereIs(targetActor)) ) {
				return true;
			}
		}
		return false;
	}
	

	@Override
	public void act(SWActor a) {
		SWEntityInterface target = this.getTarget();
		boolean targetIsActor = target instanceof SWActor;
		SWActor targetActor = null;
		
		if (targetIsActor) {
			targetActor = (SWActor) target;
		}
		// check if actor a and target actor is in the same team
		if (targetIsActor && (a.getTeam() == targetActor.getTeam())) {
			// check if actor a and target actor is in the same location
			if (canDo(a)) {
				// check if actor a is human-controlled
				if (a.isHumanControlled()) {
					// print message
					a.say("\t" + a.getShortDescription() 
							   + " says: Train me master " 
							   + targetActor.getShortDescription());
					
					SWForce lukeForce = a.getForce();
					lukeForce.incrementForce();
					targetActor.say("\t" + targetActor.getShortDescription() 
										 + " says: You have been trained");
					
				} //else actor is not human controlled
				
			}// else actor and target actor is not in the same location
			
		}// else actor and target actor is in the same team
	}
	
}
