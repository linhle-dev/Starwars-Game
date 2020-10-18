package starwars.entities.actors.behaviors;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import java.util.ArrayList;


public class Follow {
	
	//
	private Direction droidDirection;
	
	private SWActor owner;
	
	private SWActor droid;
	
	private SWWorld world;

	/**
	 * this constructor creates a follow object
	 * @param newOwner owner of object droid
	 * @param newDroid object droid
	 * @param newWorld the world that we are in
	 */
	public Follow(SWActor newOwner, SWActor newDroid, SWWorld newWorld) {
		owner = newOwner;
		droid = newDroid;
		world = newWorld;
		droidDirection = createRandomDirection(droid);
	
	}
	
	/**
	 * 
	 * @return null if  : owner is in the same location of the droid
	 * else: checks the 8 directions around droid to see if owner is there, print the prompt
	 * and return the direction d if the owner is in that location
	 * 					  
	 * checks the direction of droid still has space to go, if they do not have any space to go, pick
	 * a new direction
	 * 
	 * otherwise return the initial random direction of the droid created in the constructor
	 */
	public Direction getDirection() {
		// get direction of owner and droid
		SWLocation ownerLocation  = world.getEntityManager().whereIs(owner);
		SWLocation droidLocation  = world.getEntityManager().whereIs(droid);
		
		// check if the droid is in the same location as the owner
		if (ownerLocation == droidLocation){
			return null;
		}
		else {
			for(Grid.CompassBearing d : Grid.CompassBearing.values()){
				SWLocation ownerIsNeighbor = (SWLocation) droidLocation.getNeighbour(d);
				if (ownerIsNeighbor == ownerLocation){
					droid.say(droid.getShortDescription() + " moving to " + d);
					return d;
				}
			}
			
			// checks that the current direction of droid has still have space to go
			if (!SWWorld.getEntitymanager().seesExit(droid, droidDirection)) {
				// if there is no where to go in current direction, make a new random direction
				droidDirection = this.createRandomDirection(droid);
			}
		}
		return droidDirection;
	}
	
	/**
	 * this method creates a random direction for any object of type SWActor
	 * @param a an object of type SWActor
	 * @return returns a random direction for that object of type SWActor
	 */
	public Direction createRandomDirection(SWActor a) {
		ArrayList<Direction> possibledirections = new ArrayList<Direction>();

		// build a list of available directions
		for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
			if (SWWorld.getEntitymanager().seesExit(a, d)) {
				possibledirections.add(d);
			}
		}

		Direction heading = possibledirections.get((int) (Math.floor(Math.random() * possibledirections.size())));
		return heading;
	}
	
}
	
