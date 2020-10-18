package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;

public class Leave extends SWAffordance {
	
	/**
	 * Constructor for the <code>Leave</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being taken
	 * @param m the message renderer to display messages
	 */
	
	public Leave (SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}
	
	
	/**
	 * Returns if or not this <code>Leave</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> is carrying any item already.
	 *  
	 * @author 	Justin (04/05/2018)
	 * @author 	Linh Le(5/05/2017)
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>SWActor</code> is can take this item, false otherwise
	 * @see		{@link starwars.SWActor#getItemCarried()}
	 */
	
	@Override
	public boolean canDo(SWActor a) {
		return a.getItemCarried()!=null;
		//the actor has to have something so canDo method can be true allowing the actor to perform leave act
	}

	/**
	 * Perform the <code>Leave</code> action by setting the item left by the <code>SWActor</code> to the target (
	 * the <code>SWActor a</code>'s item left would be the target of this <code>Take</code>).
	 * <p>
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	Linh Le (5/05/2018)
	 * @author 	Justin (4/05/2018)
	 * @param 	b the <code>SWActor</code> that is taking the target
	 * @see 	{@link #theTarget}
	 * @see		{@link starwars.SWActor#isDead()}
	 */
	
	@Override
	public void act(SWActor b) {
		if(target instanceof SWEntityInterface) 
		{
			SWEntityInterface theItem = (SWEntityInterface) target;
			b.setItemCarried(null); //the carried item is nothing
			SWAction.getEntitymanager().setLocation(theItem, SWAction.getEntitymanager().whereIs(b));
			
			target.addAffordance(new Take(theItem, messageRenderer));
		}
		
		
	}
	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author Linh (5/05/2018)
	 * @author Justin (4/05/2018)
	 * @return String comprising "leave " and the short description of the target of this <code>Leave</code>
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Leave " + target.getShortDescription();
	}
	
}

