package starwars.entities.actors;

import java.util.List;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWForce;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Travel;

public class Gate extends SWActor{

	public Gate(String name, MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 1000, m, world);
		this.setShortDescription("Gate out");
		this.setLongDescription("Exit Crawler World");
		
		// add affordance travel into gate
		this.addAffordance(new Travel(this, m, world));
	}

	@Override
	public SWForce getForce() {
		return null;
	}

	@Override
	public void act() {
		
		// get the location of the gate
		SWLocation loc = this.world.getEntityManager().whereIs(this);
		// get the entities in the gate's location
		List <SWEntityInterface> entities = SWWorld.getEntitymanager().contents(loc);
		// check for the force ability of </code>SWActor</code>
		for (SWEntityInterface e: entities) {
			// check if e is SWActor and not the player
			if(e instanceof SWActor && !(e instanceof Player)) {
				SWForce temp = ((SWActor) e).getForce();
				// check if the SWActor has the force
				if (temp != null ) {
					Travel actorTravel = new Travel(this, messageRenderer, world);
					scheduler.schedule(actorTravel, (SWActor) e, 1);
				}
			}
		}
		
	}
	
	/**
	 * Gate are indestructible, so doing damage to them has no effect
	 * @param damage - the amount of damage that would be inflicted on a non-mystical Entity
	 */
	@Override
	public void takeDamage(int damage) {
		return;
	}
	
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}
	
	@Override
	public String getShortDescription() {
		return "Gate to exit";
	}

}
