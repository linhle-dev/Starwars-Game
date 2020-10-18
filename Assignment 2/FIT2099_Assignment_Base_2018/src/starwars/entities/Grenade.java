package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Leave;
import starwars.actions.Take;
import starwars.actions.Thrown;

public class Grenade extends SWEntity{

	/**
	 * Constructor for the Grenade. This Constructor will
	 * Set the short description of Grenade
	 * Set the Long description of Grenade
	 * Add take, leave and thrown affordance so that it canv be thrown, take and leave by the actor
	 */
	public Grenade (MessageRenderer m) {
		super(m);
		this.shortDescription = "A Grenade";
		this.longDescription = "A Grenade, Fire in the hole !!!!";
		
		this.addAffordance(new Take(this, m)); //add take affordance so swactor can take
		this.addAffordance(new Leave(this,m)); //add leave affordance so swactor can leave
		this.addAffordance(new Thrown(this, m)); //add thrown affordance so swactor can throw
	
		this.capabilities.add(Capability.WEAPON); // grenade is a weapon
	}
	
	/**
	 * the symbol of the grenade
	 */
	public String getSymbol() {
		return "G";
	}
	
	/**
	 * Grenade are indestructible, so doing damage to them has no effect
	 * @param damage - the amount of damage that would be inflicted on a non-mystical Entity
	 */
	@Override
	public void takeDamage(int damage) {
		return;
	}
	
}
