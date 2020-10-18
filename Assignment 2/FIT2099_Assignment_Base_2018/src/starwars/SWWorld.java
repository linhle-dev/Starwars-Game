package starwars;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.space.World;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import worlds.CrawlerWorld;
import worlds.RegularWorld;

/**
 * Class representing a world in the Star Wars universe. 
 * 
 * @author ram
 */
/*
 * Change log
 * 2017-02-02:  Render method was removed from Middle Earth
 * 				Displaying the Grid is now handled by the TextInterface rather 
 * 				than by the Grid or MiddleWorld classes (asel)
 */
public class SWWorld extends World {
	
	/** instance space that player will be at when traveling between 2 worlds */
	private SWSpace currentWorld;
	
	/** instance of the regular world */
	private RegularWorld regularWorld;
	
	/** instance of the world inside sandcrawler */
	private CrawlerWorld crawlerWorld;
	
	
	/**The entity manager of the world which keeps track of <code>SWEntities</code> and their <code>SWLocation</code>s*/
	private static final EntityManager<SWEntityInterface, SWLocation> entityManager = new EntityManager<SWEntityInterface, SWLocation>();
	
	/**
	 * Constructor of <code>SWWorld</code>
	 */
	public SWWorld() {
		
	}

	/** 
	 * Returns the height of the <code>Grid</code>. Useful to the Views when rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int height() { return currentWorld.getGrid().getHeight(); }
	
	/** 
	 * Returns the width of the <code>Grid</code>. Useful to the Views when rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int width() { return currentWorld.getGrid().getWidth(); }
	
	/**
	 * Set up the world, s. This will initialize the <code>SWLocationMaker</code>
	 * and the grid. Set the current world as the regularWorld
	 * 
	 * @author 	Linh
	 * @author Justin
	 * @param 	iface a MessageRenderer to be passed onto newly-created entities
	 */
	public void initializeWorld(MessageRenderer iface) {
		
		crawlerWorld = new CrawlerWorld(this);
		crawlerWorld.initializeSpace(iface);
		
		regularWorld = new RegularWorld(this);
		regularWorld.initializeSpace(iface);
		
		currentWorld = regularWorld;
	}
		
		

	/*
	 * Render method was removed from here
	 */
	
	/**
	 * Determine whether a given <code>SWActor a</code> can move in a given direction
	 * <code>whichDirection</code>.
	 * 
	 * @author 	ram
	 * @param 	a the <code>SWActor</code> being queried.
	 * @param 	whichDirection the <code>Direction</code> if which they want to move
	 * @return 	true if the actor can see an exit in <code>whichDirection</code>, false otherwise.
	 */
	public boolean canMove(SWActor a, Direction whichDirection) {
		SWLocation where = (SWLocation)entityManager.whereIs(a); // requires a cast for no reason I can discern
		return where.hasExit(whichDirection);
	}
	
	/**
	 * Accessor for the grid.
	 * 
	 * @author ram
	 * @return the grid
	 */
	public SWGrid getGrid() {
		return currentWorld.getGrid();
	}
	
	/**
	 * Check the current state of the world player is in
	 * @return True if currentWorld is crawlerWorld
	 */
	public boolean isCrawlerWorld() {
		return currentWorld == crawlerWorld;
	}
	
	/**
	 * Check the current state of the world player is in
	 * @return True if currentWorld is regularWorld
	 */
	public boolean isRegularWorld() {
		return currentWorld == regularWorld;
	}

	/**
	 * set the position for <code>SWActor</code> when switching spaces
	 * 
	 */
	public void switchWorld(SWActor a) {
		if (isRegularWorld()) {
			entityManager.setLocation(a, crawlerWorld.getEntryPoint());
		}else {
			entityManager.setLocation(a, regularWorld.getEntryPoint());
		}
	}
	
	/**
	 * swap the state of currentWorld for <code>SWActor</code> when entering new space
	 * @author Linh
	 * @author Justin
	 *
	 */
	public void swapWorld() {
		if(isRegularWorld()) {
			currentWorld = crawlerWorld;
		}else {
			currentWorld = regularWorld;
		}
	}
	
	
	/**
	 * Move an actor in a direction.
	 * 
	 * @author ram
	 * @param a the actor to move
	 * @param whichDirection the direction in which to move the actor
	 */
	public void moveEntity(SWActor a, Direction whichDirection) {
		
		//get the neighboring location in whichDirection
		Location loc = entityManager.whereIs(a).getNeighbour(whichDirection);
		
		// Base class unavoidably stores superclass references, so do a checked downcast here
		if (loc instanceof SWLocation)
			//perform the move action by setting the new location to the the neighboring location
			entityManager.setLocation(a, (SWLocation) entityManager.whereIs(a).getNeighbour(whichDirection));
	}

	/**
	 * Returns the <code>Location</code> of a <code>SWEntity</code> in this grid, null if not found.
	 * Wrapper for <code>entityManager.whereIs()</code>.
	 * 
	 * @author 	ram
	 * @param 	e the entity to find
	 * @return 	the <code>Location</code> of that entity, or null if it's not in this grid
	 */
	public Location find(SWEntityInterface e) {
		return entityManager.whereIs(e); //cast and return a SWLocation?
	}

	/**
	 * This is only here for compliance with the abstract base class's interface and is not supposed to be
	 * called.
	 */

	@SuppressWarnings("unchecked")
	public EntityManager<SWEntityInterface, SWLocation> getEntityManager() {
		return SWWorld.getEntitymanager();
	}

	/**
	 * Returns the <code>EntityManager</code> which keeps track of the <code>SWEntities</code> and
	 * <code>SWLocations</code> in <code>SWWorld</code>.
	 * 
	 * @return 	the <code>EntityManager</code> of this <code>SWWorld</code>
	 * @see 	{@link #entityManager}
	 */
	public static EntityManager<SWEntityInterface, SWLocation> getEntitymanager() {
		return entityManager;
	}
}
