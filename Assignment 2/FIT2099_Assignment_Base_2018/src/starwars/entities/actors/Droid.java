package starwars.entities.actors;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWForce;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.actions.Take;
import starwars.entities.actors.behaviors.Follow;

public class Droid extends SWActor implements SWEntityInterface{
	
	// an instance of actor in droid class
	private SWActor owner = null;
	
	// an instance of follow in droid class
	protected Follow droidFollow = null;
	
	// name of droid
	private String name;
	
	// damage taken when in bad land
	public final int badLandDamage = 10;
	
	/**
	 * Creates a droid, a droid will have an owner, droid will stay in the same location if
	 * it is in the same location as owner, if owner is in the neighboring location, droid will move
	 * to that location
	 * if owner cannot be found, droid will pick a random direction and keep moving at that direction
	 * until its owner is found
	 * if the droid can no longer move in the same direction, it will pick a new location and move in
	 * that direction
	 * if droid has no owner, droid will not move
	 * droid will lose health when they move to Badlands
	 * when they have no health, it will not move
	 * 
	 * @param hitpoints 
	 * @param name
	 * @param m
	 * @param world
	 * @param newOwner
	 */
	
	public Droid(int hitpoints, String name, MessageRenderer m, SWWorld world, SWActor newOwner){
		super(Team.GOOD, 50, m ,world);// i think droid should belongs to team neutral
		this.name = name;
		this.owner = newOwner;
	}
	
   
	
   /**
    * boolean method to check the droid has a owner or not
    * @return
    * @author Justin
    * @author Linh Le
    */
	public boolean hasOwner() {
		if(this.owner!=null)
		{
			return true;
		}
		return false;
	}
    
   /**
    * This method carry out the actions of the droid
    * check if droid is inside the badland, if yes take droid's health
    * check if droid is dead, if yes droid will not move
    * check if droid has an owner, if not print droid doesn't have owner
    * check if Follow object has been instantiated, if not make follow object for droid
    * get direction of the droid and make the droid move in that direction
    * 
    * @author Justin
    * @author Linh Le
    */
    @Override
	public void act() {
    	
    	// get location of droid
		SWLocation droidLocation = this.world.getEntityManager().whereIs(this);
		
		// if droid is inside the badland, droid will lose hp
		if (droidLocation.getSymbol() == 'b') {
			this.takeDamage(badLandDamage);
		}
    	
    	// if dead (hp <= 0) becomes immobile
    	if (isDead()) {
			return;
		}
		say(describeLocation());
        
		// check if droid has an owner
		if (this.owner == null) {
			System.out.println(this.getShortDescription() + " doesn't have an owner");
			return;
		}
		
		// if object Follow is null, then create a new object Follow for droid
		if (droidFollow == null) {
			droidFollow = new Follow(owner, (SWActor) this, world);
		}
		
		// get direction of droid
		Direction direction = droidFollow.getDirection();
		
		// move to the direction
		if (direction != null) {
			Move move = new Move(direction, messageRenderer, world);
			scheduler.schedule(move, this, 1);
		}
		 
	}
    
    @Override
    public String getShortDescription() {
    	return name + " Droid";
    }
    
    @Override
	public String getLongDescription() {
		return this.getShortDescription();
	}
    
    private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

	}
    
    
 	@Override
 	public SWForce getForce() {
 		// TODO Auto-generated method stub
 		return null;
 	}

}


