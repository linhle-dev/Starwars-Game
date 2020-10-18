package starwars.entities.actors;

import java.util.List;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWForce;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.actions.Take;
import starwars.actions.Travel;
import starwars.entities.actors.behaviors.Patrol;

public class Sandcrawler extends SWActor{
	
	private String name;
	private Patrol path;
	private boolean flag = false; //giving boolean value to movement of Sandcrawler
	
	public Sandcrawler(String name, MessageRenderer m, SWWorld world, Direction[] moves) {
		super(Team.GOOD, 1000, m, world);
		this.setShortDescription("Jawa Sandcrawler");
		this.setLongDescription("Enter into Sandcrawler");
		this.name = name;
		path = new Patrol(moves);
		
		// add affordance travel into Sandcrawler
		this.addAffordance(new Travel(this, m, world));
	}

	@Override
	public SWForce getForce() {
		return null;
	}

	@Override
	public void act() {
		// Movement of Sandcrawler
		if(flag) {
			Direction newDirection = path.getNext();
			say(getShortDescription() + " moves " + newDirection);
			Move myMove = new Move(newDirection, messageRenderer, world);
			scheduler.schedule(myMove, this, 1);
			flag = false;
			
		}else {
			flag = true;
		}
		
		// Sandcrawler taking the droid inside it's world
		//get location of sandcrawler
		SWLocation loc = this.world.getEntityManager().whereIs(this);
		//get the entities in the sandcrawler location
		List <SWEntityInterface> entities = SWWorld.getEntitymanager().contents(loc);
		for (SWEntityInterface e: entities) {
			// if the entity is a droid
			if (e instanceof Droid) {
				Travel droidTravel = new Travel(this, messageRenderer, world);
				scheduler.schedule(droidTravel, (SWActor) e, 1);
			}
		}
		
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
	
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}
	
	@Override
	public String getShortDescription() {
		return "Sandcrawler " + name;
	}
}
