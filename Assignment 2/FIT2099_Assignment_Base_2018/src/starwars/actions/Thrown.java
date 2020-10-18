package starwars.actions;

import java.util.List;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.matter.Entity;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.entities.Grenade;

public class Thrown extends SWAffordance {
	
	public Thrown (SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}

	@Override
	public boolean canDo(SWActor a) {
		// TODO Auto-generated method stub
		return a.getItemCarried() instanceof Grenade;
	}

	@Override
	public int getDuration() {
		return 1;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Throw " + this.target.getShortDescription();
	}

	@Override
	public void act(SWActor a) {
		target.removeAffordance(this); //remove affordance for the throw
		a.setItemCarried(null); //the actor no longer carry grenade after thrown
		Entity player = (Entity) a; // the player will not take damange
		
		//get the current actor location first
		SWLocation actorLocation = (SWLocation)SWAction.getEntitymanager().whereIs(a);
		// 20 damage at the actor's location
		damageByGrenade(actorLocation, 20, player);
		
		// 10 damage around the actor
		for(Grid.CompassBearing d1 : Grid.CompassBearing.values()){
			if (SWWorld.getEntitymanager().seesExit(a, d1)){
				SWLocation explosionInner = (SWLocation)actorLocation.getNeighbour(d1);
				damageByGrenade(explosionInner, 10, player);
			}
		}
		
		// loops through each direction i around the actor (explosionInner) then get to the
		// direction i of each of explosionInner (explosionInnerStep1)
		// and the next one (explosionInnerStep2) to take damage of entities in those locations
		
		int directionNum = Grid.CompassBearing.values().length; //number of directions
		for(int i = 0; i < directionNum; i++) {
			Grid.CompassBearing d = Grid.CompassBearing.values()[i];
			// get to the directions around actor d1
			SWLocation explosionInner = (SWLocation)actorLocation.getNeighbour(d);
			// get to the directions around d1 (second step)
			SWLocation explosionOuterStep1 = (SWLocation)explosionInner.getNeighbour(d);
			// take Damage
			damageByGrenade(explosionOuterStep1, 5, player);
			// take the next direction 
			Grid.CompassBearing d2 = Grid.CompassBearing.values()[(i+1) % directionNum];
			SWLocation explosionOuterStep2 = (SWLocation)explosionInner.getNeighbour(d2);
			damageByGrenade(explosionOuterStep2, 5, player);
			
		}
	
	}
	
	/**
	 * damaging all entity at </code>SWLocation</code>
	 * 
	 * @param loc: the location of the actor
	 * @param damage: the amount of damage entity will take
	 * @param unAffectedActor: the actor that is unaffected by grenade
	 */
	public void damageByGrenade(SWLocation loc, int damage, Entity unAffectedActor ) {
		List<SWEntityInterface> entityList = SWAction.getEntitymanager().contents(loc);
		if (entityList == null) {
			return;
		}
		for (SWEntityInterface e: entityList){
			if (e != unAffectedActor && e.getHitpoints() > 0) {
				e.takeDamage(damage);
			}
		}
	}
	

	
}
